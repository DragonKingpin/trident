package com.sauron.saurye.domain.pojo;

import java.time.LocalDateTime;

public class PluginFeatureInfo {

    // 功能id
    private Long id;

    //功能名字
    private String featureName;

    //功能描述
    private String description;

    //索引id
    private String guid;

    //创建时间
    private LocalDateTime createDateTime;

    //创建者id
    private Long userId;

    public PluginFeatureInfo() {
    }

    public PluginFeatureInfo(Long id, String featureName, String description, String guid, LocalDateTime createDateTime, Long userId) {
        this.id = id;
        this.featureName = featureName;
        this.description = description;
        this.guid = guid;
        this.createDateTime = createDateTime;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    @Override
    public String toString() {
        return "PluginFeatureInfo{" +
                "id=" + id +
                ", featureName='" + featureName + '\'' +
                ", description='" + description + '\'' +
                ", guid='" + guid + '\'' +
                ", createDateTime=" + createDateTime +
                ", userId=" + userId +
                '}';
    }
}
