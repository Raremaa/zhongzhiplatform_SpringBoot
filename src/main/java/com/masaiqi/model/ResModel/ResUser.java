package com.masaiqi.model.ResModel;

import com.masaiqi.entity.Project;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ResUser {
    /**
     * 用户id
     */
    private Integer user_id;

    /**
     * 用户编号
     */
    private String no;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 权限 admin project_leader team_leader
     */
    private String access;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 用户头像url路径
     */
    private String pictureUrl;

    /**
     * 用户个人介绍
     */
    private String introduction;

    /**
     * 所属团队Id
     */
    private Integer teamId;

    /**
     * 用户token信息
     */
    private String token;

    /**
     * 所属项目编号
     */
    private List<Integer> projectId;

    /**
     * 所在项目组名
     */
    private List<String> projectNames;

    /**
     * 权限:0-管理员；1-团队拥有者；2-团队成员
     */
    private Integer authority;

    /**
     * 所在项目组名字符串
     */
    private String projectName;

    /**
     * 用户身份字符串
     */
    private String authorityName;

    /**
     * 帮助前台封装用户所在项目组字符串名
     * @return
     */
    public String getProjectName() {
        StringBuilder sb = new StringBuilder(0);
        this.projectNames.forEach(obj -> {
            sb.append(obj+"</br>&emsp;&emsp;&emsp;&emsp;");
        });
        return sb.toString();
    }

    private String teamName;
}
