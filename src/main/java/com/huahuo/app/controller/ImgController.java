package com.huahuo.app.controller;


import com.huahuo.app.common.ImgR;
import com.huahuo.app.common.R;
import com.huahuo.app.pojo.Imgs;

import com.huahuo.app.pojo.User;
import com.huahuo.app.service.ImgsService;

import com.huahuo.app.service.UserService;
import com.huahuo.app.service.impl.BaiduService;
import com.huahuo.app.service.impl.QiniuService;

import com.huahuo.app.utils.UrlUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@RequestMapping("/img")
@RestController
public class ImgController {

    @Autowired
    UserService userService;
    @Autowired
    BaiduService baiduService;
    private String prefix = "http://riwbp7bw1.hn-bkt.clouddn.com/";
    private String form = "&250px";
    @Autowired
   private QiniuService qiniuService;
    @Autowired
    ImgsService imgsService;


    @PostMapping("/upload/1")
    @ApiOperation("文件上传")
    @ApiParam("支持批量")
    public R<Object> simpleUploadImg(@RequestParam("file") List<MultipartFile> files, HttpServletRequest request) throws IOException {
        Object o = imgsService.simpleUpload(files);


        return R.success(o);
    }

    /**
     * 注册的时候调用，用来获取一个SingleImgId来创建对象
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("upload/2")
    public String uploadImgByBaidu(@RequestParam("file") MultipartFile file) throws IOException {
        Imgs imgs = imgsService.simpleUpload(file);
        String s = baiduService.similarAdd(imgs.getUrl());
        return s;
    }
}
