package com.nsu.bootsystem.admin.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nsu.bootsystem.admin.entity.BaseDictEntity;
import com.nsu.bootsystem.admin.service.BaseDictService;
import com.nsu.common.utils.PageUtils;
import com.nsu.common.utils.R;



/**
 * 
 *
 * @author luodong
 * @email helloluodong@qq.com
 * @date 2020-06-08 08:54:11
 */
@RestController
@RequestMapping("admin/basedict")
public class BaseDictController {
    @Autowired
    private BaseDictService baseDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("admin:basedict:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = baseDictService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{dictId}")
    //@RequiresPermissions("admin:basedict:info")
    public R info(@PathVariable("dictId") String dictId){
		BaseDictEntity baseDict = baseDictService.getById(dictId);

        return R.ok().put("baseDict", baseDict);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("admin:basedict:save")
    public R save(@RequestBody BaseDictEntity baseDict){
		baseDictService.save(baseDict);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("admin:basedict:update")
    public R update(@RequestBody BaseDictEntity baseDict){
		baseDictService.updateById(baseDict);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("admin:basedict:delete")
    public R delete(@RequestBody String[] dictIds){
		baseDictService.removeByIds(Arrays.asList(dictIds));

        return R.ok();
    }

}
