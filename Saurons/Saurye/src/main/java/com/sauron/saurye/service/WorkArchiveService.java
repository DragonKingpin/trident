package com.sauron.saurye.service;

import com.sauron.saurye.domain.dto.WorkArchiveDTO;
import com.sauron.saurye.domain.pojo.WorkArchive;
import com.sauron.saurye.domain.vo.WorkArchiveVO;

import java.util.List;

public interface WorkArchiveService {

    /**
     * 获取所有归档
     * @return
     */
    List<WorkArchiveVO> getAllWorkArchive();

    /**
     * 添加存档
     * @param workArchiveDTO
     */
    void addWorkArchive(WorkArchiveDTO workArchiveDTO);

    /**
     * 更新存档
     * @param workArchiveDTO
     */
    void updateWorkArchive(WorkArchiveDTO workArchiveDTO);

    /**
     * 根据id删除存档
     * @param workArchiveId
     */
    void deleteWorkArchive(Long workArchiveId);
}
