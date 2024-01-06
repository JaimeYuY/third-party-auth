package com.ocean.angel.tool.mapper;

import com.ocean.angel.tool.domain.entity.Channel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ocean.angel.tool.domain.qo.ChannelQuery;
import com.ocean.angel.tool.domain.vo.ChannelVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *  渠道Mapper
 *
 * @author Jaime.yu
 * @time 2024-01-06
 */
public interface ChannelMapper extends BaseMapper<Channel> {

    /**
     * @desc 列表
     *
     * @param query
     * @return java.util.List<com.ocean.angel.tool.domain.vo.ChannelVO>
     */
    List<ChannelVO> listChannel(@Param("query") ChannelQuery query);

    /**
     * @desc 条数
     *
     * @param query
     * @return int
     */
    int countChannel(@Param("query") ChannelQuery query);

    /**
     * 获取Channel,依据appId
     * @param appId
     * @return
     */
    Channel getChannelByAppId(@Param("appId") String appId);
}
