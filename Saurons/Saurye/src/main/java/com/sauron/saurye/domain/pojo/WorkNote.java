package com.sauron.saurye.domain.pojo;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.time.LocalDateTime;

public class WorkNote {

    //笔记id
    private Long id;

    //用户id
    private String guid;

    //创建者id
    private Long userId;

    //存档id
    private Long archivesId;

    //笔记内容
    private String content;

    //是否删除
    private Integer isDeleted;

    //创建时间
    private LocalDateTime createDateTime;

    public WorkNote() {
    }

    public WorkNote(Long id, String guid, Long userId, Long archivesId, String content, Integer isDeleted, LocalDateTime createDateTime) {
        this.id = id;
        this.guid = guid;
        this.userId = userId;
        this.archivesId = archivesId;
        this.content = content;
        this.isDeleted = isDeleted;
        this.createDateTime = createDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(Long archivesId) {
        this.archivesId = archivesId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }


    @Override
    public String toString() {
        return "WorkNote{" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", userId=" + userId +
                ", archivesId=" + archivesId +
                ", content='" + content + '\'' +
                ", isDeleted=" + isDeleted +
                ", createDateTime=" + createDateTime +
                '}';
    }
}
