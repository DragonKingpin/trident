package com.sauron.saurye.service;

import com.sauron.saurye.domain.dto.WorkNoteDTO;
import com.sauron.saurye.domain.vo.WorkNoteVO;

import java.util.List;

public interface WorkNoteService {


    /**
     * 添加工作笔记
     * @param workNoteDTO
     */
    void addWorkNote(WorkNoteDTO workNoteDTO);

    /**
     * 获取所有工作笔记
     * @return
     */
    List<WorkNoteVO> getWorkAllNote();

    /**
     * 删除工作笔记
     * @param workNoteId
     */
    void deleteWorkNote(Long workNoteId);

    /**
     * 更新工作笔记
     * @param workNoteDTO
     */
    void updateWorkNote(WorkNoteDTO workNoteDTO);
}
