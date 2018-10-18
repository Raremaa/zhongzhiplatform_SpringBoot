package com.masaiqi.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class Task implements Serializable {
    private Integer id;

    /**
     * 用户表主键
     */
    private Integer userid;

    /**
     * 项目表主键
     */
    private Integer projectid;

    /**
     * 任务的相关描述
     */
    private String msg;

    /**
     * 个人完成情况：已提交；未提交；
     */
    private String userstatus;

    /**
     * 提交的附件在附件表中的Id
     */
    private Integer accessoryid;

    /**
     * 任务完成情况(由项目负责人裁定)：完成;未完成
     */
    private String status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(String userstatus) {
        this.userstatus = userstatus;
    }

    public Integer getAccessoryid() {
        return accessoryid;
    }

    public void setAccessoryid(Integer accessoryid) {
        this.accessoryid = accessoryid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        Task other = (Task) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getProjectid() == null ? other.getProjectid() == null : this.getProjectid().equals(other.getProjectid()))
            && (this.getMsg() == null ? other.getMsg() == null : this.getMsg().equals(other.getMsg()))
            && (this.getUserstatus() == null ? other.getUserstatus() == null : this.getUserstatus().equals(other.getUserstatus()))
            && (this.getAccessoryid() == null ? other.getAccessoryid() == null : this.getAccessoryid().equals(other.getAccessoryid()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getProjectid() == null) ? 0 : getProjectid().hashCode());
        result = prime * result + ((getMsg() == null) ? 0 : getMsg().hashCode());
        result = prime * result + ((getUserstatus() == null) ? 0 : getUserstatus().hashCode());
        result = prime * result + ((getAccessoryid() == null) ? 0 : getAccessoryid().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", projectid=").append(projectid);
        sb.append(", msg=").append(msg);
        sb.append(", userstatus=").append(userstatus);
        sb.append(", accessoryid=").append(accessoryid);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}