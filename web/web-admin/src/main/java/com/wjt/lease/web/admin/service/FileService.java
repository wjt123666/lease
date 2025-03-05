package com.wjt.lease.web.admin.service;

import com.wjt.lease.common.result.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 上传文件
     * @param file 上传的文件
     * @return 上传成功返回文件路径
     */
    Result<String> upload(MultipartFile file);
}
