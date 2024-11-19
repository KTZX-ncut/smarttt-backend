package com.example.smartttcourse;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.NioUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Slf4j
class SmartttCourseApplicationTests {

    private static Double start;
    private static Double end;

    private static String url = "http://127.0.0.1:8082/api/coursemangt/classroommangt/academiccalendar/download/1858751718578302977.pdf";


    @BeforeEach
    public void before() throws Exception {
        start = Double.valueOf(System.currentTimeMillis());
    }

    @AfterEach
    public void after() throws Exception {
        end = Double.valueOf(System.currentTimeMillis());
        log.info("方法执行时间：{}", (end - start) / 1000);
    }

    @Test
    @DisplayName("单个文件测试")
    public void testDownLoadPartFile() throws Exception {
        HttpResponse response = HttpRequest.get(url).execute();
        InputStream is = response.bodyStream();
        String fileName = response.header("File-Name");
        File file = new File("/Users/lunyasen/Desktop/tmp/" + fileName);
        FileOutputStream os = new FileOutputStream(file);
        IoUtil.copy(is, os, IoUtil.DEFAULT_BUFFER_SIZE);
        os.flush();
        os.close();
        is.close();
    }

    @DisplayName("文件分片测试")
    @Nested
    class MultiPartFileTest {
        private ExecutorService exPool = Executors.newFixedThreadPool(10);
        private String filePath = "/Users/lunyasen/Desktop/tmp/";
        private Long blockSize = 1024L * 1024L;
        private Long blockNum;

        private String fileName;

        private Long fileSize;

        @BeforeEach
        @DisplayName("文件分块下载探测")
        public void exploreFile() throws Exception {
            HashMap<String, String> headers = new HashMap<>();
            headers.put(HttpHeaders.RANGE, "bytes=0-10");
            HttpResponse response = HttpRequest.get(url).addHeaders(headers).execute();
            fileSize = Long.valueOf(response.header("File-Size"));
            blockNum = (fileSize / blockSize);
            fileName = URLDecoder.decode(response.header("File-Name"),"utf-8");
            log.info("fileName:{}", fileName);
            log.info("blockNum:{}", blockNum);
        }

        @Test
        @DisplayName("文件分块下载")
        public void downLoadPartFile() throws Exception {
            for (int i = 0; i <= blockNum; i++) {
                exPool.submit(new DownloadTask(i));
            }
            // 合并文件
            mergeFile();
        }

        class DownloadTask implements Runnable {

            public int num; // 正在下载的哪一个块数

            public DownloadTask(int num) {
                this.num = num;
            }

            public void download(int num) {
                try {
                    File file = new File(filePath + num + "-" + fileName);
                    HashMap<String, String> headers = new HashMap<>();
                    String rangeInfo = new StringBuffer("bytes=").append(num * blockSize).append("-").append((num + 1) * blockSize - 1).toString();
                    headers.put(HttpHeaders.RANGE, rangeInfo);
                    HttpResponse response = HttpRequest.get(url).addHeaders(headers).execute();
                    InputStream is = response.bodyStream();
                    FileOutputStream os = new FileOutputStream(file);
                    IoUtil.copy(is, os, blockSize.intValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void run() {
                download(num);
            }
        }

        // 文件的合并
        private void mergeFile() {
            try {
                File file = new File(filePath + fileName);
                for (int i = 0; i <= blockNum; i++) {
                    File tempFile = new File(filePath + i + "-" + fileName);
                    // 如果这个文件没有下载或者正在下载中，就持续等待
                    while (!tempFile.exists() || (i != blockNum && tempFile.length() < blockSize) || (i == blockNum && tempFile.length() < (fileSize - (i * blockSize)))) {

                        Thread.sleep(100);
                    }
                    //FileOutputStream fos = new FileOutputStream(file);
                    FileInputStream fis = new FileInputStream(tempFile);
                    byte[] bytes = IoUtil.readBytes(fis, true);
                    FileUtil.writeBytes(bytes, file, 0, bytes.length, true);
                    tempFile.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
