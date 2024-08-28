package com.sauron.saurye.service.impl;

import com.sauron.saurye.constant.StatusConstant;
import com.sauron.saurye.context.UserContext;
import com.sauron.saurye.domain.dto.WorkNoteDTO;
import com.sauron.saurye.domain.pojo.Plugin;
import com.sauron.saurye.domain.pojo.WorkNote;
import com.sauron.saurye.domain.vo.WorkNoteVO;
import com.sauron.saurye.mapper.NotePluginMapper;
import com.sauron.saurye.mapper.WorkNoteMapper;
import com.sauron.saurye.service.WorkNoteService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkNoteServiceImpl implements WorkNoteService {

    @Resource
    private WorkNoteMapper workNoteMapper;

    @Resource
    private NotePluginMapper notePluginMapper;

    /**
     * 添加工作笔记
     * @param workNoteDTO
     */
    @Override
    public void addWorkNote(WorkNoteDTO workNoteDTO) {

        //构造workNote对象
        WorkNote workNote = new WorkNote();

        //设置属性
        BeanUtils.copyProperties(workNoteDTO, workNote);
        workNote.setCreateDateTime(LocalDateTime.now());
        workNote.setIsDeleted(StatusConstant.ENABLE);

        workNote.setUserId(UserContext.getCurrentId());

        //存表
        workNoteMapper.insertWorkNote(workNote);
    }

    /**
     * 获取所有工作笔记
     * @return
     */
    @Override
    public List<WorkNoteVO> getWorkAllNote() {

        List<WorkNote> workNoteList = workNoteMapper.selectAllWorkNote();

        //封装vo对象
        List<WorkNoteVO> workNoteVOList = workNoteList.stream().map(workNote -> {
            WorkNoteVO workNoteVO = new WorkNoteVO();
            BeanUtils.copyProperties(workNote, workNoteVO);

            //找到有关插件
            List<Plugin> pluginList = notePluginMapper.selectByNoteId(workNote.getId());
            workNoteVO.setNotePlugin(pluginList);

            return workNoteVO;
        }).collect(Collectors.toList());

        return workNoteVOList;
    }

    /**
     * 删除工作笔记
     * @param workNoteId
     */
    @Override
    public void deleteWorkNote(Long workNoteId) {
        workNoteMapper.deleteWorkNoteById(workNoteId);
    }

    /**
     * 更新工作笔记
     * @param workNoteDTO
     */
    @Override
    public void updateWorkNote(WorkNoteDTO workNoteDTO) {
        WorkNote workNote = new WorkNote();
        BeanUtils.copyProperties(workNoteDTO, workNote);

        workNoteMapper.updateWorkNote(workNote);
    }

}
