package com.huahuo.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.huahuo.app.common.ImgR;
import com.huahuo.app.common.R;
import com.huahuo.app.exception.UserException;
import com.huahuo.app.mapper.ImgsMapper;
import com.huahuo.app.pojo.Imgs;
import com.huahuo.app.service.ImgsService;
import com.huahuo.app.utils.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author Administrator
* @description 针对表【imgs】的数据库操作Service实现
* @createDate 2022-09-29 11:15:41
*/
@Service
public class ImgsServiceImpl extends ServiceImpl<ImgsMapper, Imgs>
        implements ImgsService{


    private String prefix = "http://riwbp7bw1.hn-bkt.clouddn.com/";
    private String form = "&250px";
    @Autowired
    private QiniuService qiniuService;
    @Autowired
    ImgsService imgsService;

    @Override
    public List<ImgR> simpleUpload(List<MultipartFile> files) throws IOException {
        String MdUrl = null;
        List<ImgR> list = new ArrayList<>();

        for (MultipartFile file : files) {
            ImgR imgR = new ImgR();
            if (file.isEmpty()) {
                throw new UserException("图片为空！");
            }
            try {
                MdUrl=null;
                String url = null;
                String fileUrl = qiniuService.saveImage(file);
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
                imgR.setCode("200");
                imgR.setFileName(UrlUtil.removePrefix(url, prefix));
                imgR.setUrl(url);
                if(imgR.getUrl().equals("http://null"))
                    imgR.setCode("error");
                list.add(imgR);
            } catch (IOException e) {
                e.printStackTrace();
                imgR.setCode("500");
            }
        }
        return list;
    }

    @Override
    public Imgs simpleUpload(MultipartFile file) throws IOException {

        String MdUrl = null;
        String url = null;
        String fileUrl = qiniuService.saveImage(file);
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




