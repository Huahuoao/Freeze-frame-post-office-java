package com.huahuo.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.huahuo.app.common.ImgR;
import com.huahuo.app.exception.UserException;
import com.huahuo.app.mapper.ImgsMapper;
import com.huahuo.app.po.Imgs;
import com.huahuo.app.service.ImgsService;
import com.huahuo.app.utils.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.huahuo.app.constant.StatusCode.ALL_OK;
import static com.huahuo.app.constant.StatusCode.GET_RESOURCES_FAILED;
import static com.huahuo.app.constant.UserErrorCode.IMAGE_IS_BLANK;

/**
 * @author Administrator
 * @description 针对表【imgs】的数据库操作Service实现
 * @createDate 2022-09-29 11:15:41
 */
@Service
public class ImgsServiceImpl extends ServiceImpl<ImgsMapper, Imgs>
        implements ImgsService {


    private String prefix = "http://riwbp7bw1.hn-bkt.clouddn.com/";
    private String form = "&250px";
    @Autowired
    private QiniuServiceImpl qiniuServiceImpl;
    @Autowired
    ImgsService imgsService;

    @Override
    public List<ImgR> simpleUpload(List<MultipartFile> files) throws IOException {
        String MdUrl;
        String url;
        List<ImgR> list = new ArrayList<>();

        for (MultipartFile file : files) {
            ImgR imgR = new ImgR();
            if (file.isEmpty()) {
                throw new UserException(IMAGE_IS_BLANK);
            }
            String fileUrl = qiniuServiceImpl.saveImage(file);
            url = "http://" + fileUrl;
            MdUrl = "![img](" + url + ")";
            Imgs imgs = new Imgs();
            imgs.setUrl(url);
            imgs.setMrakdownUrl(MdUrl);
            imgs.setCreateTime(LocalDateTime.now());
            imgs.setName(UrlUtil.removePrefix(url, prefix));
            imgs.setFileKey(UrlUtil.removePrefix(url, prefix));
            imgs.setUpdateTime(LocalDateTime.now());
            imgs.setMiniurl(url + form);
            imgsService.save(imgs);
            imgR.setCode(ALL_OK);
            imgR.setFileName(UrlUtil.removePrefix(url, prefix));
            imgR.setUrl(url);
            if (imgR.getUrl().equals("http://null"))
                imgR.setCode(GET_RESOURCES_FAILED);
            list.add(imgR);
        }
        return list;
    }

    @Override
    public Imgs simpleUpload(MultipartFile file) throws IOException {

        String MdUrl;
        String url;
        String fileUrl = qiniuServiceImpl.saveImage(file);
        url = "http://" + fileUrl;
        MdUrl = "![img](" + url + ")";
        Imgs imgs = new Imgs();
        imgs.setUrl(url);
        imgs.setMrakdownUrl(MdUrl);
        imgs.setCreateTime(LocalDateTime.now());
        imgs.setName(UrlUtil.removePrefix(url, prefix));
        imgs.setFileKey(UrlUtil.removePrefix(url, prefix));
        imgs.setUpdateTime(LocalDateTime.now());
        imgs.setMiniurl(url + form);
        imgsService.save(imgs);
        return imgs;
    }


}




