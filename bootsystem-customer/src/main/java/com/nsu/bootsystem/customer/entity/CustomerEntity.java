package com.nsu.bootsystem.customer.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @date 2020-06-10 17:51:35
 */
@Data
@TableName("customer")
public class CustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 客户编号(主键)
	 */
	@NotNull(message = "修改必须指定客户id",groups = {UpdateGroup.class})
	@Null(message = "新增不能指定客户id",groups = {AddGroup.class})
	@TableId
	private Integer custId;
	/**
	 * 客户名称
	 */
	@NotBlank(message = "客户名称不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String custName;
	/**
	 * 负责人id
	 */
	private Integer custUserId;
	/**
	 * 创建人id
	 */
	private Integer custCreateId;
	/**
	 * 客户信息来源
	 */
	@NotBlank(message = "客户信息来源不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String custSource;
	/**
	 * 客户所属行业
	 */
	@NotBlank(message = "客户所属行业不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String custIndustry;
	/**
	 * 客户级别
	 */
	private String custLevel;
	/**
	 * 联系人
	 */
	@NotBlank(message = "联系人不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String custLinkman;
	/**
	 * 固定电话
	 */
	private String custPhone;
	/**
	 * 移动电话
	 */
	@NotBlank(message = "手机号不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String custMobile;
	/**
	 * 邮政编码
	 */
	private String custZipcode;
	/**
	 * 联系地址
	 */
	@NotBlank(message = "联系地址不能为空",groups = {AddGroup.class,UpdateGroup.class})
	private String custAddress;
	/**
	 * 创建时间
	 */
	private Date custCreatetime;
	/**
	 * 0:未删除  1：已删除【逻辑删除字段】
	 */
	@TableLogic
	private Integer state;
}
