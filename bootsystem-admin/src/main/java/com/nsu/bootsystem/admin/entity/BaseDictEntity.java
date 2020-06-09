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
@TableName("base_dict")
public class BaseDictEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 数据字典id(主键)
	 */
	@TableId
	private String dictId;
	/**
	 * 数据字典类别代码
	 */
	private String dictTypeCode;
	/**
	 * 数据字典类别名称
	 */
	private String dictTypeName;
	/**
	 * 数据字典项目名称
	 */
	private String dictItemName;
	/**
	 * 数据字典项目代码(可为空)
	 */
	private String dictItemCode;
	/**
	 * 排序字段
	 */
	private Integer dictSort;
	/**
	 * 1:使用 0:停用
	 */
	private String dictEnable;
	/**
	 * 备注
	 */
	private String dictMemo;

}
