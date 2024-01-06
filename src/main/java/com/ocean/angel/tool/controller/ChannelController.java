package com.ocean.angel.tool.controller;

import com.ocean.angel.tool.common.BaseController;
import com.ocean.angel.tool.domain.dto.ChannelCheckDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ocean.angel.tool.common.ActionResult;
import com.ocean.angel.tool.common.PageResult;
import com.ocean.angel.tool.domain.dto.ChannelDTO;
import com.ocean.angel.tool.domain.entity.Channel;
import com.ocean.angel.tool.domain.qo.ChannelQuery;
import com.ocean.angel.tool.domain.vo.ChannelVO;
import com.ocean.angel.tool.service.ChannelService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 *  渠道前端控制器
 *
 * @author Jaime.yu
 * @time 2024-01-06
 */
@RestController
@RequestMapping("/channel")
public class ChannelController extends BaseController {

    @Resource
    private ChannelService service;

    /**
     * 列表
     */
    @GetMapping("/list")
    public PageResult<ChannelVO> page(ChannelQuery query) {
        return service.page(query);
    }

    /**
     * 获取信息
     */
    @GetMapping("/get/{id}")
    public ActionResult<Channel> get(@PathVariable Long id) {
        Channel entity = service.getById(id);
        if(null == entity) {
            return ActionResult.error();
        }
        return ActionResult.success(entity);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public ActionResult<Long> save(@RequestBody ChannelDTO dto) {
        return service.save(dto);
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    public ActionResult<Long> update(@RequestBody ChannelDTO dto) {
        return service.update(dto);
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ActionResult<Void> delete(@PathVariable Long id) {
        service.removeById(id);
        return ActionResult.success();
    }

    /**
     * 审核
     */
    @PostMapping("/check")
    public ActionResult<Long> check(@RequestBody ChannelCheckDTO dto) {
        return service.check(dto);
    }

}
