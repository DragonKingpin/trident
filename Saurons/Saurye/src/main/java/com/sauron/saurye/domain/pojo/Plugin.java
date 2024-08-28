package com.sauron.saurye.domain.pojo;

import java.time.LocalDateTime;

public class Plugin {

    //插件id
    private Long id;

    //插件名称
    private String pluginName;

    //插件分类
    private Long categoryId;

    //插件创建时间
    private LocalDateTime createDateTime;

    //索引id
    private String guid;

    public Plugin() {
    }

    public Plugin(Long id, String pluginName, Long categoryId, LocalDateTime createDateTime, String guid) {
        this.id = id;
        this.pluginName = pluginName;
        this.categoryId = categoryId;
        this.createDateTime = createDateTime;
        this.guid = guid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "Plugin{" +
                "id=" + id +
                ", pluginName='" + pluginName + '\'' +
                ", categoryId=" + categoryId +
                ", createDateTime=" + createDateTime +
                ", guid='" + guid + '\'' +
                '}';
    }
}
