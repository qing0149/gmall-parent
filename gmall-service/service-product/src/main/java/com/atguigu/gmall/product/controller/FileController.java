package com.atguigu.gmall.product.controller;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.result.Result;
import com.atguigu.gmall.product.utils.MinioUtil;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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