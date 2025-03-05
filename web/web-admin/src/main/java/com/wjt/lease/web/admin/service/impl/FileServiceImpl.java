package com.wjt.lease.web.admin.service.impl;

import cn.hutool.core.util.IdUtil;
import com.wjt.lease.common.minio.MinioProperties;
import com.wjt.lease.common.result.Result;
import com.wjt.lease.common.result.ResultCodeEnum;
import com.wjt.lease.web.admin.service.FileService;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.SetBucketPolicyArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private MinioClient minioClient;
    @Autowired
    private MinioProperties minioProperties;

    /**
     * 上传文件
     * @param file 上传的文件
     * @return 上传成功返回文件路径
     */
    @Override
    public Result<String> upload(MultipartFile file){
        try {
            // 设置bucket访问权限
            minioClient.setBucketPolicy(
                    SetBucketPolicyArgs
                            .builder()
                            .bucket(minioProperties.getBucketName()) // 设置桶名
                            .config(createBucketPolicyConfig(minioProperties.getBucketName())) // 设置访问权限
                            .build());

            // 文件名 20240304/uuid.jpg
            String originalFilename = file.getOriginalFilename();
            String fileName = new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" +
                    IdUtil.fastSimpleUUID() +
                    originalFilename.substring(originalFilename.lastIndexOf("."));

            // 上传文件
            minioClient.putObject(
                    PutObjectArgs
                            .builder()
                            .bucket(minioProperties.getBucketName()) // 设置桶名
                            .object(fileName) // 设置文件名
                            .stream(file.getInputStream(), file.getSize(), -1) // 设置文件流
                            .contentType(file.getContentType()) // 设置文件类型
                            .build()
            );

            // 返回文件路径
            return Result.ok(String.join("/", minioProperties.getEndpoint(), minioProperties.getBucketName(), fileName));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }

    }

    private String createBucketPolicyConfig(String bucketName) {

        return """
            {
              "Statement" : [ {
                "Action" : "s3:GetObject",
                "Effect" : "Allow",
                "Principal" : "*",
                "Resource" : "arn:aws:s3:::%s/*"
              } ],
              "Version" : "2012-10-17"
            }
            """.formatted(bucketName);
    }
}
