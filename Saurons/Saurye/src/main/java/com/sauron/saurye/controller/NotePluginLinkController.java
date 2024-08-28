package com.sauron.saurye.controller;

import com.sauron.saurye.domain.dto.AddNotePluginDTO;
import com.sauron.saurye.domain.pojo.Plugin;
import com.sauron.saurye.result.BasicServiceResponse;
import com.sauron.saurye.service.NotePluginLinkService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 笔记插件接口
 * @author azlml
 * @date 2024/8/18
 */
@RestController
@RequestMapping("/notePlugin")
public class NotePluginLinkController {

    @Resource
    private NotePluginLinkService notePluginLinkService;

    /**
     * 展示所有笔记相关的插件
     * @param noteId
     * @return
     */
    @GetMapping("/list/{noteId}")
    public BasicServiceResponse<List<Plugin>> getNotePlugin(@PathVariable Long noteId)
    {
        return BasicServiceResponse.success(notePluginLinkService.listAllPlugin(noteId));
    }



    /**
     * 笔记添加新的插件
     * @param addNotePluginDTO
     * @return
     */
    @PostMapping("/add")
    public BasicServiceResponse<?> addNotePlugin(@RequestBody AddNotePluginDTO addNotePluginDTO)
    {
        notePluginLinkService.addNotePlugin(addNotePluginDTO);

        return BasicServiceResponse.success();
    }

    /**
     * 删除笔记对应的插件
     * @param PluginId  插件id
     * @return
     */
    @DeleteMapping("/delete/{PluginId}")
    public BasicServiceResponse<?> deleteNotePlugin(@PathVariable Long PluginId)
    {
        notePluginLinkService.deletedNotePlugin(PluginId);

        return BasicServiceResponse.success();
    }

}
