package com.sauron.saurye.service.impl;

import com.sauron.saurye.constant.MessageConstant;
import com.sauron.saurye.context.UserContext;
import com.sauron.saurye.domain.dto.WorkArchiveDTO;
import com.sauron.saurye.domain.pojo.User;
import com.sauron.saurye.domain.pojo.WorkArchive;
import com.sauron.saurye.domain.pojo.WorkNote;
import com.sauron.saurye.domain.vo.WorkArchiveVO;
import com.sauron.saurye.exception.ArchiveNameAlreadyExistsException;
import com.sauron.saurye.exception.ArchiveNotFoundException;
import com.sauron.saurye.mapper.NotePluginLinkMapper;
import com.sauron.saurye.mapper.WorkArchiveMapper;
import com.sauron.saurye.mapper.WorkNoteMapper;
import com.sauron.saurye.mapper.WorkStrategyMapper;
import com.sauron.saurye.service.WorkArchiveService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkArchiveServiceImpl implements WorkArchiveService {

    @Resource
    private WorkArchiveMapper workArchiveMapper;

    @Resource
    private WorkStrategyMapper workStrategyMapper;

    @Resource
    private WorkNoteMapper workNoteMapper;

    @Resource
    private NotePluginLinkMapper notePluginLinkMapper;

    /**
     * 获取所有工作存档
     * @return
     */
    @Override
    public List<WorkArchiveVO> getAllWorkArchive() {

        List<WorkArchive>  workArchiveList = workArchiveMapper.getAllWorkArchive(UserContext.getCurrentId());

        List<WorkArchiveVO> workArchiveVOList = new ArrayList<>();
        //封装vo对象
        workArchiveList.stream().forEach(workArchive -> {
            WorkArchiveVO workArchiveVO = new WorkArchiveVO();
            BeanUtils.copyProperties(workArchive,workArchiveVO);

            String strategyName = workStrategyMapper.selectByStrategyId(workArchive.getStrategyId()).getStrategyName();
            workArchiveVO.setStrategyName(strategyName);

            List<WorkNote> workNoteList = workNoteMapper.selectNotesByArchiveId(workArchive.getId());
            workArchiveVO.setWorkNoteList(workNoteList);

            workArchiveVOList.add(workArchiveVO);
        });

        return workArchiveVOList;
    }

    /**
     * 添加工作存档
     * @param workArchiveDTO
     */
    @Override
    public void addWorkArchive(WorkArchiveDTO workArchiveDTO) {

        //排除存档名称相同的情况
        if (workArchiveMapper.selectByWorkArchiveName(workArchiveDTO.getArchiveName()) != null){
            throw new ArchiveNameAlreadyExistsException(MessageConstant.ARCHIVE_NAME_ALREADY_EXISTS);
        }

        WorkArchive workArchive = new WorkArchive();
        BeanUtils.copyProperties(workArchiveDTO,workArchive);

        workArchive.setUserId(UserContext.getCurrentId());
        workArchive.setCreateDateTime(LocalDateTime.now());

        workArchiveMapper.addWorkArchive(workArchive);
    }

    /**
     * 更新工作存档
     * @param workArchiveDTO
     */
    @Override
    public void updateWorkArchive(WorkArchiveDTO workArchiveDTO) {
        WorkArchive workArchive = new WorkArchive();
        BeanUtils.copyProperties(workArchiveDTO,workArchive);

        workArchiveMapper.updateWorkArchive(workArchive);
    }

    /**
     * 根据id删除存档
     * @param workArchiveId
     */
    @Override
    public void deleteWorkArchive(Long workArchiveId) {

        //检查是否存在这个存档
        WorkArchive workArchive = workArchiveMapper.selectByWorkArchiveId(workArchiveId);
        if (workArchive == null){
            throw new ArchiveNotFoundException(MessageConstant.ARCHIVE_NOT_FOUND);
        }

        //根据存档id找到所有的有关文章，把文章对应的插件都删了
        workNoteMapper.selectNotesByArchiveId(workArchiveId).stream().forEach(workNote -> {
            notePluginLinkMapper.deleteNotePluginLinkByNoteId(workNote.getId());
        });

        //根据存档的id，把对应存档里面的笔记都删除
        workNoteMapper.deleteWorkNoteByWorkArchiveId(workArchiveId);

        //删除对应的存档
        workArchiveMapper.deleteWorkArchiveById(workArchiveId);

    }
}
