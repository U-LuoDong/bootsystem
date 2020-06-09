package com.nsu.bootsystem.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author luodong
 * @email helloluodong@qq.com
 * @date 2020-06-08 08:54:10
 */
@Data
@TableName("sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	@TableId
	private Integer userId;
	/**
	 * 用户账号
	 */
	private String userCode;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户密码
	 */
	private String userPassword;
	/**
	 * 1:正常,0:暂停
	 */
	private Integer userState;

}
