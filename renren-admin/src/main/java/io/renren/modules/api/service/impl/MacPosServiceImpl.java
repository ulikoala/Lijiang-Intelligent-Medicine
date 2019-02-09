package io.renren.modules.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.api.dao.MacPosDao;
import io.renren.modules.api.entity.MacPosEntity;
import io.renren.modules.api.service.MacPosService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("macPosService")
public class MacPosServiceImpl extends ServiceImpl<MacPosDao, MacPosEntity> implements MacPosService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MacPosEntity> page = this.selectPage(
                new Query<MacPosEntity>(params).getPage(),
                new EntityWrapper<MacPosEntity>()
        );

        return new PageUtils(page);
    }

}
