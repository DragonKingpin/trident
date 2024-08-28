package com.sauron.saurye.domain.pojo;

public class PluginFeature {
    //主键id
    private Long id;

    //对应的内容id
    private Long infoId;

    //索引id
    private String guid;

    //未完待续
    private String value;

    public PluginFeature() {
    }

    public PluginFeature(Long id, Long infoId, String guid, String value) {
        this.id = id;
        this.infoId = infoId;
        this.guid = guid;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "PluginFeature{" +
                "id=" + id +
                ", infoId=" + infoId +
                ", guid='" + guid + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
