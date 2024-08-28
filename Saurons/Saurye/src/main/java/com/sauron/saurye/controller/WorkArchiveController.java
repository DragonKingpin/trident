package com.sauron.saurye.controller;

import com.sauron.saurye.domain.dto.WorkArchiveDTO;
import com.sauron.saurye.domain.pojo.WorkArchive;
import com.sauron.saurye.domain.vo.WorkArchiveVO;
import com.sauron.saurye.result.BasicServiceResponse;
import com.sauron.saurye.service.WorkArchiveService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 存档接口
 *
 * @author azlml
 * @date 2024/8/18
 */
@RestController
@RequestMapping("/workArchive")
public class WorkArchiveController {

    @Resource
    private WorkArchiveService workArchiveService;

    /**
     * 根据当前用户id读取所有存档
     * @return 存档列表
     */
    @GetMapping("/list")
    public BasicServiceResponse<List<WorkArchiveVO>> getAllWorkArchive() {
        return BasicServiceResponse.success(workArchiveService.getAllWorkArchive());
    }

    /**
     * 添加或更新存档
     * @param workArchiveDTO
     * @return
     */
    @PostMapping("/addOrUpdate")
    public BasicServiceResponse<?> addOrUpdateWorkArchive(@RequestBody WorkArchiveDTO workArchiveDTO) {
        //前端没有传id，就是新存档
        if (workArchiveDTO.getId() == null) {
            workArchiveService.addWorkArchive(workArchiveDTO);
        }else{
            workArchiveService.updateWorkArchive(workArchiveDTO);
        }
        return BasicServiceResponse.success();
    }

    /**
     * 根据前端传回来的存档id进行删除
     * @param WorkArchiveId
     * @return
     */
    @DeleteMapping("/delete/{WorkArchiveId}")
    public BasicServiceResponse<?> deleteWorkArchive(@PathVariable Long WorkArchiveId) {

        workArchiveService.deleteWorkArchive(WorkArchiveId);

        return BasicServiceResponse.success();
    }

}
