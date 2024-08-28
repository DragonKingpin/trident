package com.sauron.saurye.domain.pojo;

import java.time.LocalDateTime;

public class PluginCategory {

    // 分类id
    private Long id;

    //索引id
    private String guid;

    // 分类名称
    private String categoryName;

    // 创建时间
    private LocalDateTime createDateTime;

    public PluginCategory() {
    }

    public PluginCategory(Long id, String guid, String categoryName, LocalDateTime createDateTime) {
        this.id = id;
        this.guid = guid;
        this.categoryName = categoryName;
        this.createDateTime = createDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
        return "PluginCategory{" +
                "id=" + id +
                ", guid='" + guid + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", createDateTime=" + createDateTime +
                '}';
    }
}
