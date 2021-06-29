/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.oss.cloud;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.aliyun.oss.OSSClient;
import com.highy.common.exception.ErrorCode;
import com.highy.common.exception.RenException;

/**
 * 阿里云存储
 *
 * @author jchaoy 453428948@qq.com
 */
public class AliyunCloudStorageService extends AbstractCloudStorageService {

    public AliyunCloudStorageService(CloudStorageConfig config){
        this.config = config;
    }

    @Override
    public String upload(byte[] data, String path) {
        return upload(new ByteArrayInputStream(data), path);
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        OSSClient client = new OSSClient(config.getAliyunEndPoint(), config.getAliyunAccessKeyId(),
                config.getAliyunAccessKeySecret());
        try {
            client.putObject(config.getAliyunBucketName(), path, inputStream);
            client.shutdown();
        } catch (Exception e){
            throw new RenException(ErrorCode.OSS_UPLOAD_FILE_ERROR, e, "");
        }

        return config.getAliyunDomain() + "/" + path;
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath(config.getAliyunPrefix(), suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath(config.getAliyunPrefix(), suffix));
    }
}