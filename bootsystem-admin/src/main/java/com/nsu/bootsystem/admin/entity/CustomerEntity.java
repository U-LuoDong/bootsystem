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
 * @date 2020-06-08 08:54:11
 */
@Data
@TableName("customer")
public class CustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 客户编号(主键)
	 */
	@TableId
	private Integer custId;
	/**
	 * 客户名称
	 */
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
	private String custSource;
	/**
	 * 客户所属行业
	 */
	private String custIndustry;
	/**
	 * 客户级别
	 */
	private String custLevel;
	/**
	 * 联系人
	 */
	private String custLinkman;
	/**
	 * 固定电话
	 */
	private String custPhone;
	/**
	 * 移动电话
	 */
	private String custMobile;
	/**
	 * 邮政编码
	 */
	private String custZipcode;
	/**
	 * 联系地址
	 */
	private String custAddress;
	/**
	 * 创建时间
	 */
	private Date custCreatetime;

}
