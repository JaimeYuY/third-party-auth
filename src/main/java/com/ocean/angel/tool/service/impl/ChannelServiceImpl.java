package com.ocean.angel.tool.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.ocean.angel.tool.common.ActionResult;
import com.ocean.angel.tool.common.PageResult;
import com.ocean.angel.tool.constants.ResultCode;
import com.ocean.angel.tool.constants.enums.CheckEnum;
import com.ocean.angel.tool.domain.dto.ChannelCheckDTO;
import com.ocean.angel.tool.domain.entity.Channel;
import com.ocean.angel.tool.mapper.ChannelMapper;
import com.ocean.angel.tool.service.ChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ocean.angel.tool.domain.qo.ChannelQuery;
import com.ocean.angel.tool.domain.vo.ChannelVO;
import com.ocean.angel.tool.domain.dto.ChannelDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *  服务实现类
 *
 * @author Jaime.yu
 * @time 2024-01-06
 */
@Service
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements ChannelService {

    @Resource
    private ChannelMapper mapper;

    @Override
    public PageResult<ChannelVO> page(ChannelQuery query) {
        List<ChannelVO> data = mapper.listChannel(query);
        int total = mapper.countChannel(query);
        PageResult<ChannelVO> pageResult = PageResult.success(data, total);
        pageResult.setPageSize(query.getPageSize());
        pageResult.setPageNum(query.getPageNum());
        int pages = total % query.getPageSize() == 0 ? total / query.getPageSize() : total / query.getPageSize() + 1;
        pageResult.setPages(pages);
        return pageResult;
    }

    @Override
    @Transactional
    public ActionResult<Long> save(ChannelDTO dto) {
        Channel entity = new Channel();
        BeanUtil.copyProperties(dto, entity);
        entity.setCreateTime(new Date());
        mapper.insert(entity);
        return ActionResult.success(entity.getId());
    }

    @Override
    @Transactional
    public ActionResult<Long> update(ChannelDTO dto) {

        // 参数非空校验
        if(null == dto.getId()) {
            return ActionResult.error(ResultCode.PARAM_ERROR);
        }

        // 存在性校验
        Channel entity = mapper.selectById(dto.getId());
        if(null == entity) {
            return ActionResult.error(ResultCode.RECORD_NOT_EXIST);
        }

        entity = new Channel();
        BeanUtil.copyProperties(dto, entity);
        entity.setUpdateTime(new Date());
        mapper.updateById(entity);
        return ActionResult.success(entity.getId());
    }

    @Override
    public ActionResult<Long> check(ChannelCheckDTO dto) {

        // 参数非空校验
        if(null == dto.getId() || !CheckEnum.LEGAL_PARAM().contains(dto.getStatus())) {
            return ActionResult.error(ResultCode.PARAM_ERROR);
        }

        // 存在性校验
        Channel channel = mapper.selectById(dto.getId());
        if(null == channel) {
            return ActionResult.error(ResultCode.RECORD_NOT_EXIST);
        }

        channel = new Channel();
        BeanUtil.copyProperties(dto, channel);

        if(CheckEnum.YES.getCode().equals(dto.getStatus())) {
            // 审核完成后，生成渠道应用秘钥
            String appId = RandomUtil.randomString(16);
            String appSecret = RandomUtil.randomString(32);
            channel.setAppId(appId);
            channel.setAppSecret(appSecret);
        }

        channel.setUpdateTime(new Date());
        mapper.updateById(channel);
        return ActionResult.success(channel.getId());
    }

    @Override
    public Channel getChannelByAppId(String appId) {
        return mapper.getChannelByAppId(appId);
    }
}
