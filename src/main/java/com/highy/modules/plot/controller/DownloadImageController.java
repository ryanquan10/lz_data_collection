package com.highy.modules.plot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Controller;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/downloadimg/")
public class DownloadImageController {



    @Value("${document.path}")
    static String document = "/home/qy/code/data-collection/"; //本地测试
//    static String document = "/home/product";  //阿里云部署

    @RequestMapping(value = "/download")
    public byte[] downLoad(@RequestParam("fileName") String fileName) throws IOException {
        if(StringUtils.isBlank(fileName)){
           //todo         throw a new Exception
        }



        Path path = Paths.get(document,fileName);

        System.out.println("-----DownloadImageController----");
        System.out.println("fileName-----"+fileName+"----");

        return Files.readAllBytes(path);

    }

}
