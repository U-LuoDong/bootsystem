package com.nsu.bootsystem.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nsu.common.utils.PageUtils;
import com.nsu.bootsystem.admin.entity.SysUserEntity;

import java.util.Map;

/**
 * 
 *
 * @author luodong
 * @email helloluodong@qq.com
 * @date 2020-06-08 08:54:10
 */
public interface SysUserService extends IService<SysUserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    SysUserEntity queryByUserName(String userName);

    SysUserEntity queryByUserId(Integer userId);

    void changeUserState(Integer userId,Integer userState);

    void updateThumb(Integer userId,String thumb);

    Boolean checkAccount(String userName);
}

