package com.example.smartttexam.Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Python脚本调用工具
 * 用法: PythonRunner.run("generateQuestion.py", "json参数")
 */
public class PythonRunner {

    // Python路径：优先读系统属性，没有则用默认的python命令
    private static final String PYTHON_PATH = System.getProperty("python.path", "python");

    // Python脚本所在的资源目录（相对于user.dir，IDE运行目录即smarttt-exam模块根目录）
    private static final String SCRIPT_DIR = "src/main/resources/python";

    /**
     * 运行Python脚本
     *
     * @param scriptName 脚本文件名，如 "generateQuestion.py"
     * @param args       命令行参数，传给sys.argv
     * @return PythonResult 包含退出码、标准输出、错误输出
     */
    public static PythonResult run(String scriptName, String... args) {
        File tempFile = null;
        try {
            // 1. 构建命令：将参数写入临时文件避免Windows命令行引号问题
            List<String> command = new ArrayList<>();
            command.add(PYTHON_PATH);
            command.add(SCRIPT_DIR + "/" + scriptName);
            if (args != null && args.length > 0) {
                // 把第一个参数(JSON)写入临时文件，其余参数直接追加到命令
                tempFile = File.createTempFile("kwacmd_", ".json");
                try (BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(tempFile), StandardCharsets.UTF_8))) {
                    writer.write(args[0]);
                }
                command.add(tempFile.getAbsolutePath());
                // 其余参数（如outputId）直接传
                for (int i = 1; i < args.length; i++) {
                    if (args[i] != null) {
                        command.add(args[i]);
                    }
                }
            }

            System.out.println("[PythonRunner] 执行命令: " + String.join(" ", command));

            // 2. 创建进程
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.directory(new File(System.getProperty("user.dir")));
            pb.redirectErrorStream(false);  // stdout和stderr分开读
            pb.environment().put("PYTHONUNBUFFERED", "1");  // Python输出不缓冲

            Process process = pb.start();

            // 3. 读取标准输出（开线程，避免阻塞）
            StringBuilder stdOut = new StringBuilder();
            Thread stdThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("[Python] " + line);
                        stdOut.append(line).append("\n");
                    }
                } catch (IOException e) {
                    System.out.println("[PythonRunner] 读取stdout出错: " + e.getMessage());
                }
            });

            // 4. 读取错误输出
            StringBuilder errOut = new StringBuilder();
            Thread errThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("[Python Error] " + line);
                        errOut.append(line).append("\n");
                    }
                } catch (IOException e) {
                    System.out.println("[PythonRunner] 读取stderr出错: " + e.getMessage());
                }
            });

            stdThread.start();
            errThread.start();

            // 5. 等待进程结束
            int exitCode = process.waitFor();
            stdThread.join();
            errThread.join();

            return new PythonResult(exitCode, stdOut.toString().trim(), errOut.toString().trim());

        } catch (Exception e) {
            return new PythonResult(-1, "", "Python脚本调用异常: " + e.getMessage());
        }
    }

    /**
     * Python执行结果
     */
    public static class PythonResult {
        /** 退出码，0=成功 */
        private int exitCode;
        /** 标准输出 */
        private String stdOutput;
        /** 错误输出 */
        private String errOutput;

        public PythonResult(int exitCode, String stdOutput, String errOutput) {
            this.exitCode = exitCode;
            this.stdOutput = stdOutput;
            this.errOutput = errOutput;
        }

        public boolean isSuccess() {
            return exitCode == 0;
        }

        public int getExitCode() { return exitCode; }
        public String getStdOutput() { return stdOutput; }
        public String getErrOutput() { return errOutput; }
    }
}
