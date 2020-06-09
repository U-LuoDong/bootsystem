package com.nsu.bootsystem.admin.dao;

import com.nsu.bootsystem.admin.entity.SysUserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

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
}
