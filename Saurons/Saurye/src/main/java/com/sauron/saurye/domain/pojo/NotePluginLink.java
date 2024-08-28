package com.sauron.saurye.domain.pojo;

public class NotePluginLink {

    //主键id
    private Long id;

    //笔记id
    private Long noteId;

    //插件id
    private Long pluginId;

    //索引id
    private String guid;

    public NotePluginLink() {
    }

    public NotePluginLink(Long id, Long noteId, Long pluginId, String guid) {
        this.id = id;
        this.noteId = noteId;
        this.pluginId = pluginId;
        this.guid = guid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Long getPluginId() {
        return pluginId;
    }

    public void setPluginId(Long pluginId) {
        this.pluginId = pluginId;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "NotePluginLink{" +
                "id=" + id +
                ", noteId=" + noteId +
                ", pluginId=" + pluginId +
                ", guid='" + guid + '\'' +
                '}';
    }
}
