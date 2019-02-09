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

import io.renren.modules.api.entity.MacPosEntity;
import io.renren.modules.api.service.MacPosService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-12-28 16:27:32
 */
@RestController
@RequestMapping("api/macpos")
public class MacposController {
    @Autowired
    private MacPosService macposService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("api:macpos:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = macposService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{machineno}")
    @RequiresPermissions("api:macpos:info")
    public R info(@PathVariable("machineno") String machineno){
        MacPosEntity macpos = macposService.selectById(machineno);

        return R.ok().put("macpos", macpos);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("api:macpos:save")
    public R save(@RequestBody MacPosEntity macpos){
        macposService.insert(macpos);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("api:macpos:update")
    public R update(@RequestBody MacPosEntity macpos){
        ValidatorUtils.validateEntity(macpos);
        macposService.updateAllColumnById(macpos);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("api:macpos:delete")
    public R delete(@RequestBody String[] machinenos){
        macposService.deleteBatchIds(Arrays.asList(machinenos));

        return R.ok();
    }

}
