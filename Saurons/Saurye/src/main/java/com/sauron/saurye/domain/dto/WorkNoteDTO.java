package com.sauron.saurye.domain.dto;

public class WorkNoteDTO {

    //笔记id
    private Long id;

    //存档id
    private Long archivesId;

    //笔记内容
    private String content;

    public WorkNoteDTO() {
    }

    public WorkNoteDTO(Long id, Long archivesId, String content) {
        this.id = id;
        this.archivesId = archivesId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "WorkNoteDTO{" +
                "id=" + id +
                ", archivesId=" + archivesId +
                ", content='" + content + '\'' +
                '}';
    }
}
