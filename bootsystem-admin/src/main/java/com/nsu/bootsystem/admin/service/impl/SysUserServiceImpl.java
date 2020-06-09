package com.nsu.bootsystem.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nsu.common.utils.PageUtils;
import com.nsu.common.utils.Query;

import com.nsu.bootsystem.admin.dao.SysUserDao;
import com.nsu.bootsystem.admin.entity.SysUserEntity;
import com.nsu.bootsystem.admin.service.SysUserService;


@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysUserEntity> page = this.page(
                new Query<SysUserEntity>().getPage(params),//返回page对象
                new QueryWrapper<SysUserEntity>()
        );

        return new PageUtils(page);
    }

    //登录验证
    @Override
    public SysUserEntity queryByUserName(String userName) {
        return baseMapper.queryByUserName(userName);
    }

    @Override
    public void changeUserState(Integer userId,Integer userState) {
        baseMapper.changeUserState(userId,userState);
    }
}