package com.masaiqi.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Project implements Serializable {
    private Integer id;

    /**
     * 项目负责人
     */
    private Integer projectleader;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目简介
     */
    private String introduction;

    /**
     * 完成情况：完成；项目流产；已逾期
     */
    private String status;

    /**
     * 规定完成时间
     */
    private Date deadtime;

    /**
     * 开始时间
     */
    private Date starttime;

    /**
     * 所属团队Id
     */
    private Integer teamid;

    /**
     * 项目表Id
     */
    private Integer accessoryid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectleader() {
        return projectleader;
    }

    public void setProjectleader(Integer projectleader) {
        this.projectleader = projectleader;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeadtime() {
        return deadtime;
    }

    public void setDeadtime(Date deadtime) {
        this.deadtime = deadtime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Integer getTeamid() {
        return teamid;
    }

    public void setTeamid(Integer teamid) {
        this.teamid = teamid;
    }

    public Integer getAccessoryid() {
        return accessoryid;
    }

    public void setAccessoryid(Integer accessoryid) {
        this.accessoryid = accessoryid;
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
        Project other = (Project) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProjectleader() == null ? other.getProjectleader() == null : this.getProjectleader().equals(other.getProjectleader()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getIntroduction() == null ? other.getIntroduction() == null : this.getIntroduction().equals(other.getIntroduction()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDeadtime() == null ? other.getDeadtime() == null : this.getDeadtime().equals(other.getDeadtime()))
            && (this.getStarttime() == null ? other.getStarttime() == null : this.getStarttime().equals(other.getStarttime()))
            && (this.getTeamid() == null ? other.getTeamid() == null : this.getTeamid().equals(other.getTeamid()))
            && (this.getAccessoryid() == null ? other.getAccessoryid() == null : this.getAccessoryid().equals(other.getAccessoryid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProjectleader() == null) ? 0 : getProjectleader().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIntroduction() == null) ? 0 : getIntroduction().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDeadtime() == null) ? 0 : getDeadtime().hashCode());
        result = prime * result + ((getStarttime() == null) ? 0 : getStarttime().hashCode());
        result = prime * result + ((getTeamid() == null) ? 0 : getTeamid().hashCode());
        result = prime * result + ((getAccessoryid() == null) ? 0 : getAccessoryid().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", projectleader=").append(projectleader);
        sb.append(", name=").append(name);
        sb.append(", introduction=").append(introduction);
        sb.append(", status=").append(status);
        sb.append(", deadtime=").append(deadtime);
        sb.append(", starttime=").append(starttime);
        sb.append(", teamid=").append(teamid);
        sb.append(", accessoryid=").append(accessoryid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}