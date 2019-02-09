package io.renren.modules.api.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.api.entity.MacEntity;
import io.renren.modules.api.service.MacService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;



/**
 * 药柜设备表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-28 16:27:32
 */
@RestController
@RequestMapping("api/mac")
public class MacController {
    @Autowired
    private MacService macService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("api:mac:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = macService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{machineno}")
    @RequiresPermissions("api:mac:info")
    public R info(@PathVariable("machineno") String machineno){
        MacEntity mac = macService.selectById(machineno);

        return R.ok().put("mac", mac);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("api:mac:save")
    public R save(@RequestBody MacEntity mac){
        macService.insert(mac);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("api:mac:update")
    public R update(@RequestBody MacEntity mac){
        ValidatorUtils.validateEntity(mac);
        macService.updateAllColumnById(mac);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("api:mac:delete")
    public R delete(@RequestBody String[] machinenos){
        macService.deleteBatchIds(Arrays.asList(machinenos));

        return R.ok();
    }

}
