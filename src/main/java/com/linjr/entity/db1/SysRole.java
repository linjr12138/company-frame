package com.linjr.entity.db1;

import com.linjr.vo.resp.PermissionRespNodeVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysRole implements Serializable {
    private String id;

    private String name;

    private String description;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    private List<PermissionRespNodeVO> permissionRespNode;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public List<PermissionRespNodeVO> getPermissionRespNode() {
        return permissionRespNode;
    }

    public void setPermissionRespNode(List<PermissionRespNodeVO> permissionRespNode) {
        this.permissionRespNode = permissionRespNode;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                ", permissionRespNode=" + permissionRespNode +
                '}';
    }
}