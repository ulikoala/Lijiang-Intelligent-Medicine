package io.renren.modules.api.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.api.dao.MacDao;
import io.renren.modules.api.entity.MacEntity;
import io.renren.modules.api.service.MacService;


@Service("macService")
public class MacServiceImpl extends ServiceImpl<MacDao, MacEntity> implements MacService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MacEntity> page = this.selectPage(
                new Query<MacEntity>(params).getPage(),
                new EntityWrapper<MacEntity>()
        );

        return new PageUtils(page);
    }

}
