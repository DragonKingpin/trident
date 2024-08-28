package com.sauron.saurye.domain.dto;

public class WorkArchiveDTO {

    //存档id
    private Long id;

    //存档名称
    private String archiveName;

    //存档策略id
    private Long strategyId;

    public WorkArchiveDTO() {
    }

    public WorkArchiveDTO(Long id, String archiveName, Long strategyId) {
        this.id = id;
        this.archiveName = archiveName;
        this.strategyId = strategyId;
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

    @Override
    public String toString() {
        return "WorkArchiveDTO{" +
                "id=" + id +
                ", archiveName='" + archiveName + '\'' +
                ", strategyId=" + strategyId +
                '}';
    }
}
