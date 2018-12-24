package com.masaiqi.controller;

import com.masaiqi.entity.QiNiu;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;

@RestController
public class QiNiuController {
    // 获取 七牛云的 token
    @RequestMapping(value = "${vue-url.getToken}",method = RequestMethod.GET)
    public QiNiu getToken() {
        QiNiu qiNiu = new QiNiu();
        String accessKey = "tENTHr-AKjugTECRTk5pCeRsbYxkGkaAnsbeZav5";
        String secretKey = "Pcj1-qAzkIBBHsbS0RylxRB5wA-1D7MaSzj2ds4h";
        String bucket = "zhongzhi";
        long expireSeconds = 1200;   //过期时间
        StringMap putPolicy = new StringMap();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket,null, expireSeconds,putPolicy);
        qiNiu.setKey(UUID.randomUUID().toString().replaceAll("\\-", ""));
        qiNiu.setToken(upToken);
        return qiNiu;
    }
}
