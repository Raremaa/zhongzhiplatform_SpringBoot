package com.masaiqi.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class User implements Serializable {
    private Integer id;

    /**
     * 用户编号
     */
    private String no;

    /**
     * 权限:0-管理员；1-团队拥有者；2-团队成员
     */
    private Byte authority;

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
    private String pictureurl;

    /**
     * 用户个人介绍
     */
    private String introduction;

    /**
     * 所属团队Id
     */
    private Integer teamid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Byte getAuthority() {
        return authority;
    }

    public void setAuthority(Byte authority) {
        this.authority = authority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNo() == null ? other.getNo() == null : this.getNo().equals(other.getNo()))
            && (this.getAuthority() == null ? other.getAuthority() == null : this.getAuthority().equals(other.getAuthority()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPictureurl() == null ? other.getPictureurl() == null : this.getPictureurl().equals(other.getPictureurl()))
            && (this.getIntroduction() == null ? other.getIntroduction() == null : this.getIntroduction().equals(other.getIntroduction()))
            && (this.getTeamid() == null ? other.getTeamid() == null : this.getTeamid().equals(other.getTeamid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNo() == null) ? 0 : getNo().hashCode());
        result = prime * result + ((getAuthority() == null) ? 0 : getAuthority().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPictureurl() == null) ? 0 : getPictureurl().hashCode());
        result = prime * result + ((getIntroduction() == null) ? 0 : getIntroduction().hashCode());
        result = prime * result + ((getTeamid() == null) ? 0 : getTeamid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", no=").append(no);
        sb.append(", authority=").append(authority);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", pictureurl=").append(pictureurl);
        sb.append(", introduction=").append(introduction);
        sb.append(", teamid=").append(teamid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}