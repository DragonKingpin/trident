package com.sauron.saurye.domain.dto;

public class AddNotePluginDTO {

    //笔记id
    private Long noteId;

    //插件名称
    private String pluginName;

    public AddNotePluginDTO() {
    }

    public AddNotePluginDTO(Long noteId, String pluginName) {
        this.noteId = noteId;
        this.pluginName = pluginName;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    @Override
    public String toString() {
        return "AddNotePluginDTO{" +
                "noteId=" + noteId +
                ", pluginName='" + pluginName + '\'' +
                '}';
    }
}
