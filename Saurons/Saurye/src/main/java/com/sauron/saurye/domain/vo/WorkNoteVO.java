package com.sauron.saurye.domain.vo;

import com.sauron.saurye.domain.pojo.Plugin;

import java.time.LocalDateTime;
import java.util.List;

public class WorkNoteVO {

    //笔记id
    private Long id;

    //用户id
    private Long guid;

    //存档id
    private Long archivesId;

    //笔记内容
    private String content;

    //创建时间
    private LocalDateTime createDateTime;

    //笔记插件
    private List<Plugin> notePlugin;

    public WorkNoteVO() {
    }

    public WorkNoteVO(Long id, Long guid, Long archivesId, String content, LocalDateTime createDateTime, List<Plugin> notePlugin) {
        this.id = id;
        this.guid = guid;
        this.archivesId = archivesId;
        this.content = content;
        this.createDateTime = createDateTime;
        this.notePlugin = notePlugin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGuid() {
        return guid;
    }

    public void setGuid(Long guid) {
        this.guid = guid;
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

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public List<Plugin> getNotePlugin() {
        return notePlugin;
    }

    public void setNotePlugin(List<Plugin> notePlugin) {
        this.notePlugin = notePlugin;
    }

    @Override
    public String toString() {
        return "WorkNoteVO{" +
                "id=" + id +
                ", guid=" + guid +
                ", archivesId=" + archivesId +
                ", content='" + content + '\'' +
                ", createDateTime=" + createDateTime +
                ", notePlugin=" + notePlugin +
                '}';
    }
}
