package com.atguigu.gmall.product.controller;// 直接赋值粘贴，删除CSDN的权限转载中文

import cn.hutool.core.date.DateUtil;
import com.alibaba.nacos.common.utils.UuidUtils;
import com.atguigu.gmall.common.execption.GmallException;
import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.product.utils.MinioUtil;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-06 11:03
 **/
@Api(tags = "上传图片接口")
@RestController
@RequestMapping("/admin/product")
public class FileController {
    @Autowired
    private MinioUtil minioUtil;
    @SneakyThrows
    @PostMapping("/fileUpload")
    public Result picturefileUpload(MultipartFile file) {
        String url = minioUtil.picturefileUpload(file);
        return Result.ok(url);
    }
}