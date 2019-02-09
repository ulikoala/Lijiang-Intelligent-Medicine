package io.renren.modules.api.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.api.dao.SaleRecordDao;
import io.renren.modules.api.entity.SaleRecordEntity;
import io.renren.modules.api.service.SaleRecordService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("saleRecordService")
public class SaleRecordServiceImpl extends ServiceImpl<SaleRecordDao, SaleRecordEntity> implements SaleRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SaleRecordEntity> page = this.selectPage(
                new Query<SaleRecordEntity>(params).getPage(),
                new EntityWrapper<SaleRecordEntity>()
        );

        return new PageUtils(page);
    }

}
