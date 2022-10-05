package com.huahuo.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.huahuo.app.common.ImgR;
import com.huahuo.app.pojo.Imgs;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* @author Administrator
* @description 针对表【imgs】的数据库操作Service
* @createDate 2022-09-29 11:15:41
*/
public interface ImgsService extends IService<Imgs> {
   public Object simpleUpload(List<MultipartFile> files) throws IOException;

   public Imgs simpleUpload(MultipartFile file) throws IOException;

}
