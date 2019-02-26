package com.cxs.bus.controller;

import com.cxs.sys.constant.BaseResult;
import com.cxs.sys.utils.RandomUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:chenxiaoshuang
 * @Date:2019/2/26 11:27
 */
@Controller
public class UploadController {

    private static final String MODEL = "file";

    @Value("${fileupload.uploadlocation}")
    private String uploadPath;


    /**
     * 文件上传
     */
    @PostMapping(MODEL + "/fileUpload")
    @ResponseBody
    public BaseResult<?> fileUpload(MultipartFile mf) {
        //得到文件的老名字
        String oldName = mf.getOriginalFilename();
        String dirName = RandomUtils.createDateStrUseCurrrentDate();
        File dirFile = new File(uploadPath, dirName);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        //创建文件新名字
        String newName = RandomUtils.createFileNameUseTime(oldName);
        File file = new File(dirFile, newName);
        Integer code = 1;
        try {
            mf.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            code = -1;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("msg", "");
        Map<String, Object> data = new HashMap<>();
        data.put("src", dirName + "/" + newName);
        map.put("data", data);
        return BaseResult.success(map);
    }


    /**
     * 文件下载
     */
    @GetMapping(MODEL + "/downLoad")
    public ResponseEntity<Object> fileDownLoad(String path) {
        String realPath = uploadPath + "/" + path;
        File file = new File(realPath);
        // 将下载的文件，封装byte[]
        byte[] bytes = null;
        try {
            bytes = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建封装响应头信息的对象
        HttpHeaders header = new HttpHeaders();
        // 封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
        header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 设置下载的文件的名称
        header.setContentDispositionFormData("attachment", "123.jpg");
        // 创建ResponseEntity对象
        ResponseEntity<Object> entity = new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
        return entity;

    }

}
