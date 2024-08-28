package com.sauron.saurye.domain.pojo;

public class WorkStrategy {

    //策略id
    private Long id;

    //策略名称
    private String strategyName;

    //索引id
    private String guid;

    public WorkStrategy() {
    }

    public WorkStrategy(Long id, String strategyName, String guid) {
        this.id = id;
        this.strategyName = strategyName;
        this.guid = guid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "WorkStrategy{" +
                "id=" + id +
                ", strategyName='" + strategyName + '\'' +
                ", guid='" + guid + '\'' +
                '}';
    }
}
