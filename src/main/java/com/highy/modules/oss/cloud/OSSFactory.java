/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.oss.cloud;


/**
 * 文件上传Factory
 * @author jchaoy 453428948@qq.com
 */
public final class OSSFactory {
    /*private static SysParamsService sysParamsService;

    static {
        OSSFactory.sysParamsService = SpringContextUtils.getBean(SysParamsService.class);
    }*/

    public static AbstractCloudStorageService build(){
        /*//获取云存储配置信息
        CloudStorageConfig config = sysParamsService.getValueObject(Constant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

        if(config.getType() == Constant.CloudService.QINIU.getValue()){
            return new QiniuCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
            return new AliyunCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
            return new QcloudCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.FASTDFS.getValue()){
            return new FastDFSCloudStorageService(config);
        }else if(config.getType() == Constant.CloudService.LOCAL.getValue()){
            return new LocalCloudStorageService(config);
        }

        return null;*/
    	
    	// 临时测试使用
    	CloudStorageConfig config = new CloudStorageConfig();
    	config.setType(5);
    	// 本地测试
//    	config.setLocalPath("d:/upload");
    	config.setLocalPath("/usr/app/upload");
    	config.setLocalPrefix("filePath");
    	config.setLocalDomain("http://localhost:9080");
    	return new LocalCloudStorageService(config);
    }

}