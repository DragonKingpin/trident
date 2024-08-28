package com.sauron.saurye.domain.pojo;

import java.time.LocalDateTime;

public class WorkArchive {

    //存档id
    private Long id;

    //存档名称
    private String archiveName;

    //对应策略id
    private Long strategyId;

    //用户id
    private String guid;

    //创建者id
    private Long userId;

    //创建时间
    private LocalDateTime createDateTime;

    public WorkArchive() {
    }

    public WorkArchive(Long id, String archiveName, Long strategyId, String guid, Long userId, LocalDateTime createDateTime) {
        this.id = id;
        this.archiveName = archiveName;
        this.strategyId = strategyId;
        this.guid = guid;
        this.userId = userId;
        this.createDateTime = createDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArchiveName() {
        return archiveName;
    }

    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
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
        return "WorkArchive{" +
                "id=" + id +
                ", archiveName='" + archiveName + '\'' +
                ", strategyId=" + strategyId +
                ", guid='" + guid + '\'' +
                ", userId=" + userId +
                ", createDateTime=" + createDateTime +
                '}';
    }
}
