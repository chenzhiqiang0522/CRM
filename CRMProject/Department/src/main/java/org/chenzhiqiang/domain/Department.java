package org.chenzhiqiang.domain;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Department {
    private Long id;
    private String name;
    private String intro;
    private Date createTime;
    private Date updateTime;
    private Long managerId;
    private Long parentId;
    private String path;
    private Integer state;

    public Department(Long id, String name, String intro, Date createTime, Date updateTime, Long managerId, Long parentId, String path, Integer state) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.managerId = managerId;
        this.parentId = parentId;
        this.path = path;
        this.state = state;
    }

    public Department(String name, String intro, Date createTime, Date updateTime, Long managerId, Long parentId, String path, Integer state) {
        this.name = name;
        this.intro = intro;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.managerId = managerId;
        this.parentId = parentId;
        this.path = path;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Department(String name, String intro) {
        this.name = name;
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", managerId=" + managerId +
                ", parentId=" + parentId +
                ", path='" + path + '\'' +
                ", state=" + state +
                '}';
    }
}
