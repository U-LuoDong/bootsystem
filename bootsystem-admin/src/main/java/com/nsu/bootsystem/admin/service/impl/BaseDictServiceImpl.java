package com.nsu.bootsystem.admin.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nsu.common.utils.PageUtils;
import com.nsu.common.utils.Query;

import com.nsu.bootsystem.admin.dao.BaseDictDao;
import com.nsu.bootsystem.admin.entity.BaseDictEntity;
import com.nsu.bootsystem.admin.service.BaseDictService;


@Service("baseDictService")
public class BaseDictServiceImpl extends ServiceImpl<BaseDictDao, BaseDictEntity> implements BaseDictService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BaseDictEntity> page = this.page(
                new Query<BaseDictEntity>().getPage(params),
                new QueryWrapper<BaseDictEntity>()
        );

        return new PageUtils(page);
    }

}