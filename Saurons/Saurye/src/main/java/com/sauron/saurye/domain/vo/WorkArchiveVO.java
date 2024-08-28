package com.sauron.saurye.domain.vo;

import com.sauron.saurye.domain.pojo.WorkNote;

import java.time.LocalDateTime;
import java.util.List;

public class WorkArchiveVO {

    //存档id
    private Long id;

    //存档名称
    private String archiveName;

    //对应策略名称
    private String strategyName;

    //创建时间
    private LocalDateTime createDateTime;

    //存档下的笔记
    private List<WorkNote> workNoteList;

    public WorkArchiveVO() {
    }

    public WorkArchiveVO(Long id, String archiveName, String strategyName, LocalDateTime createDateTime, List<WorkNote> workNoteList) {
        this.id = id;
        this.archiveName = archiveName;
        this.strategyName = strategyName;
        this.createDateTime = createDateTime;
        this.workNoteList = workNoteList;
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

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public List<WorkNote> getWorkNoteList() {
        return workNoteList;
    }

    public void setWorkNoteList(List<WorkNote> workNoteList) {
        this.workNoteList = workNoteList;
    }

    @Override
    public String toString() {
        return "WorkArchiveVO{" +
                "id=" + id +
                ", archiveName='" + archiveName + '\'' +
                ", strategyName='" + strategyName + '\'' +
                ", createDateTime=" + createDateTime +
                ", workNoteList=" + workNoteList +
                '}';
    }
}
