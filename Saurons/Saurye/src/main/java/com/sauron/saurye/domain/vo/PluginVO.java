package com.sauron.saurye.domain.vo;

import java.time.LocalDateTime;
import java.util.List;

public class PluginVO {

    //插件名称
    private String pluginName;

    //插件分类名称
    private String categoryName;

    //插件创建时间
    private LocalDateTime createDateTime;

    //插件功能名称
    private List<String> featureName;

    public PluginVO() {
    }

    public PluginVO(String pluginName, String categoryName, LocalDateTime createDateTime, List<String> featureName) {
        this.pluginName = pluginName;
        this.categoryName = categoryName;
        this.createDateTime = createDateTime;
        this.featureName = featureName;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
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

    public List<String> getFeatureName() {
        return featureName;
    }

    public void setFeatureName(List<String> featureName) {
        this.featureName = featureName;
    }

    @Override
    public String toString() {
        return "PluginVO{" +
                "pluginName='" + pluginName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", createDateTime=" + createDateTime +
                ", featureName=" + featureName +
                '}';
    }
}
