package io.renren.modules.api.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.api.entity.MacPosEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-28 16:27:32
 */
public interface MacPosService extends IService<MacPosEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

