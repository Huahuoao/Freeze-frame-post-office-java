package com.huahuo.app.controller;



import com.huahuo.app.Result.R;
import com.huahuo.app.po.Imgs;

import com.huahuo.app.service.ImgsService;

import com.huahuo.app.service.UserService;
import com.huahuo.app.service.impl.BaiduServiceImpl;


import com.huahuo.app.dto.ImgsDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@Slf4j
@RequestMapping("/img")
@RestController
public class ImgController {

    @Autowired
    UserService userService;
    @Autowired
    BaiduServiceImpl baiduServiceImpl;
    private String prefix = "http://riwbp7bw1.hn-bkt.clouddn.com/";
    private String form = "&250px";
    @Autowired
    ImgsService imgsService;


    @PostMapping("/upload")
    @ApiOperation(value = "普通上传图床")
    @ApiParam("支持批量")
    public List simpleUploadImg(@RequestPart @RequestParam("file") List<MultipartFile> files, HttpServletRequest request) throws IOException {
        List list = imgsService.simpleUpload(files);
        return list;
    }

    /**
     * 注册的时候调用，用来获取一个SingleImgId来创建对象
     * @param file
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "注册时上传图片")
    @PostMapping("upload/sign")
    public R<ImgsDto> uploadImgWhenSign(@RequestPart @RequestParam("file") MultipartFile file) throws IOException {
        Imgs imgs = imgsService.simpleUpload(file);
        ImgsDto imgsDto = new ImgsDto();
        BeanUtils.copyProperties(imgs, imgsDto);
        String s = baiduServiceImpl.similarAdd(imgs.getUrl());
        imgsDto.setSingleId(s);
        return R.success(imgsDto,"最喜欢图片上传成功！");
    }

    /**
     * 1 删除唯一图片
     * 2 上传图片获得singleid
     * 3 修改singleid
     * 4 更新用户信息
     */
//    @ApiOperation(value = "修改最爱图片")
//    @PostMapping("update/fav")
//    public R<ImgsVo> updateFavoriteImg(@RequestPart @RequestParam("file") MultipartFile file,@RequestBody User user) throws IOException {
//        Imgs imgs = imgsService.simpleUpload(file);
//        ImgsVo imgsVo = new ImgsVo();
//        BeanUtils.copyProperties(imgs,imgsVo);
//        String s = baiduService.similarAdd(imgs.getUrl());
//        imgsVo.setSingleId(s);
//        return R.success(imgsVo,"最喜欢图片修改成功！");
//    }

}
