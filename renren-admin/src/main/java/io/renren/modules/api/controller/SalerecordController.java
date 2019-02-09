package io.renren.modules.api.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.api.entity.SaleRecordEntity;
import io.renren.modules.api.service.SaleRecordService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 药品销售表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-28 16:27:32
 */
@RestController
@RequestMapping("api/salerecord")
public class SalerecordController {
    @Autowired
    private SaleRecordService salerecordService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("api:salerecord:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = salerecordService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{orderno}")
    @RequiresPermissions("api:salerecord:info")
    public R info(@PathVariable("orderno") Long orderno){
        SaleRecordEntity salerecord = salerecordService.selectById(orderno);

        return R.ok().put("salerecord", salerecord);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("api:salerecord:save")
    public R save(@RequestBody SaleRecordEntity salerecord){
        salerecordService.insert(salerecord);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("api:salerecord:update")
    public R update(@RequestBody SaleRecordEntity salerecord){
        ValidatorUtils.validateEntity(salerecord);
        salerecordService.updateAllColumnById(salerecord);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("api:salerecord:delete")
    public R delete(@RequestBody Long[] ordernos){
        salerecordService.deleteBatchIds(Arrays.asList(ordernos));

        return R.ok();
    }

}
