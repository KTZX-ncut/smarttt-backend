package com.example.smartttcommon.utils;

import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lunSir
 * @create 2024-10-22 15:00
 */
@Component
public class MinioUtil {
    @Resource
    private MinioClient minioClient;

    /**
     * 判断桶是否存在
     */
    @SneakyThrows
    public Boolean existBucket(String bucketName){
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    /**
     * 创建一个桶
     */
    @SneakyThrows
    public void buildBucket(String bucketName){
        if (!this.existBucket(bucketName)){
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName).build());
        }
    }

    /**
     * 列出所有桶
     */
    @SneakyThrows
    public List<String> getAllBuckets(){
        List<Bucket> bucketList = minioClient.listBuckets();
        return bucketList.stream().map(b -> b.name()).collect(Collectors.toList());
    }

    /**
     * 删除指定桶
     */
    @SneakyThrows
    public void deleteBucket(String bucketName){
        if (this.existBucket(bucketName)){
            minioClient.removeBucket(RemoveBucketArgs.builder()
                            .bucket(bucketName)
                            .build());
        }
    }


    /**
     * 删除一个文件
     */
    @SneakyThrows
    public void deleteObject(String bucketName,String objectName){
        minioClient.removeObject(RemoveObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                .build());
    }

    /**
     * 上传文件
     */
    @SneakyThrows
    public void upload(InputStream inputStream, String bucketName, String objectName){
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(inputStream, -1, 5242889L)
                .build());
    }

    /**
     * 下载文件
     */
    @SneakyThrows
    public InputStream download(String bucketName, String objectName){
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName).build());
    }
    @SneakyThrows
    public String getUrl(String bucketName, String objectName){
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .method(Method.GET)
                .build());
    }


}
