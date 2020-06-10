package com.nsu.bootsystem.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.nsu.common.valid.AddGroup;
import com.nsu.common.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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
	@NotNull(message = "修改必须指定用户id",groups = {UpdateGroup.class})
	@Null(message = "新增不能指定用户id",groups = {AddGroup.class})
	@TableId
	private Integer userId;
	/**
	 * 用户账号
	 */
	private String userCode;
	/**
	 * 用户名称
	 */
	@NotBlank(message = "用户名不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String userName;
	/**
	 * 用户密码
	 */
	@NotBlank(message="密码不能为空", groups = AddGroup.class)
	private String userPassword;
	/**
	 * 确认密码
	 */
	@NotBlank(message="确认密码不能为空", groups = AddGroup.class)
	@TableField(exist = false)
	private String confirmPas;
	/**
	 * 1:正常,0:暂停
	 */
	private Integer userState;
	/**
	 * 管理员头像
	 */
	@NotBlank(message="管理员头像不能为空", groups = {AddGroup.class})
	private String thumb;
}
