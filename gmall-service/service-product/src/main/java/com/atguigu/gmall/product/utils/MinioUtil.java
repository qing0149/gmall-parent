package com.atguigu.gmall.product.utils;// 直接赋值粘贴，删除CSDN的权限转载中文

import cn.hutool.core.date.DateUtil;
import com.atguigu.gmall.execption.GmallException;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
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
 * @create: 2023-04-06 16:41
 **/
@Component
@ConfigurationProperties(prefix = "minio")
@RefreshScope
@Setter
public class MinioUtil {

    private String endpointUrl;
    private String accessKey;
    private String secreKey;
    private String bucketName;

    public String picturefileUpload(MultipartFile multipartFile) {
        try {
//            创立连接
            MinioClient minioClient = MinioClient.builder().endpoint(endpointUrl).credentials(accessKey, secreKey).build();
//            判断库是否存在
            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
//                没有则创建
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            //文件名字：年/月/日/uuid
            String picName = DateUtil.format(new Date(), "yyyy/MM/dd") +
                    "/" + UUID.randomUUID().toString().replace("-", "").substring(0, 5);
//            上传图片
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(picName)
                    .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                    .contentType(multipartFile.getContentType())
                    .build());
            //访问路径
            String pageUrl = endpointUrl + '/' + bucketName + '/' + picName;
            return pageUrl;
        } catch (ErrorResponseException e) {
            throw new GmallException("上传图片失败" + e.getMessage(), 500);
        } catch (InsufficientDataException e) {
            throw new RuntimeException(e);
        } catch (InternalException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (InvalidResponseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (ServerException e) {
            throw new RuntimeException(e);
        } catch (XmlParserException e) {
            throw new RuntimeException(e);
        }
    }
}
