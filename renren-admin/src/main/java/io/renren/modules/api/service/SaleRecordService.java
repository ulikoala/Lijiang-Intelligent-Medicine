package io.renren.modules.api.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.api.entity.SaleRecordEntity;

import java.util.Map;

/**
 * 药品销售表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-28 16:27:32
 */
public interface SaleRecordService extends IService<SaleRecordEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

