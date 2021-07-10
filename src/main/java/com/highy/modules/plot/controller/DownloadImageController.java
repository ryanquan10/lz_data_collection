package com.highy.modules.plot.controller;

import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Controller;
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


    static String document = "/home/qy/code/data-collection/";
//    static String document = "/home/product";

    @RequestMapping(value = "/download")
    public byte[] downLoad(@RequestParam("fileName") String fileName) throws IOException {
        if(StringUtils.isBlank(fileName)){
//          throw new Exception
        }

        Path path = Paths.get(document,fileName);

        System.out.println("-----DownloadImageController----");
        System.out.println("fileName-----"+fileName+"----");

        return Files.readAllBytes(path);

    }

}
