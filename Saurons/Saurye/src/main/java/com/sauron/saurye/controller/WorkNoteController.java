package com.sauron.saurye.controller;

import com.sauron.saurye.domain.dto.WorkNoteDTO;
import com.sauron.saurye.domain.vo.WorkNoteVO;
import com.sauron.saurye.result.BasicServiceResponse;
import com.sauron.saurye.service.WorkNoteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 笔记接口
 * @author azlml
 * @date 2024/8/18
 */
@RestController
@RequestMapping("/workNote")
public class WorkNoteController {

    @Resource
    private WorkNoteService workNoteService;

    /**
     * 展示所有笔记
     * @return
     */
    @GetMapping("/list")
    public BasicServiceResponse<List<WorkNoteVO>> getWorkAllNote(){
        return BasicServiceResponse.success(workNoteService.getWorkAllNote());
    }

    /**
     * 删除工作笔记
     * @param WorkNoteId
     * @return
     */
    @DeleteMapping("/delete/{WorkNoteId}")
    public BasicServiceResponse<?> deleteWorkNote(@PathVariable Long WorkNoteId){
        //主要是根据笔记的id删除
        workNoteService.deleteWorkNote(WorkNoteId);

        return BasicServiceResponse.success();
    }

    /**
     * 统一管理增加或更新笔记
     * @param workNoteDTO
     * @return
     */
    @PostMapping("/addOrUpdate")
    public BasicServiceResponse<?> addOrUpdateWorkNote(@RequestBody WorkNoteDTO workNoteDTO){
        if(workNoteDTO.getId() == null){
            workNoteService.addWorkNote(workNoteDTO);
        }else{
            workNoteService.updateWorkNote(workNoteDTO);
        }
        return BasicServiceResponse.success();
   }
}
