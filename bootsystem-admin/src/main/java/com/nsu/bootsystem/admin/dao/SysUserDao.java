package com.nsu.bootsystem.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nsu.bootsystem.admin.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 
 * @author luodong
 * @email helloluodong@qq.com
 * @date 2020-06-08 08:54:10
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {

    SysUserEntity queryByUserName(String userName);

    void changeUserState(@Param("userId") Integer userId, @Param("userState") Integer userState);

    SysUserEntity queryByUserId(Integer userId);

    void updateThumb(Integer userId,String thumb);

    Boolean checkAccount(String userName);
}
