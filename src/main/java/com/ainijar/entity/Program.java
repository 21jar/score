package com.ainijar.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 数据字典
 */
@Data
@TableName("program")
public class Program implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;
	/**
	 * 字典名称
	 */
	@NotBlank(message="字典名称不能为空")
	private String name;
	/**
	 * 机构id
	 */
	private Long deptId;

	private Float score;

	@TableField(exist=false)
	private String deptName;

	@TableField(exist=false)
	private Long judgesId;

	@TableField(exist=false)
	private Integer judgesScore;

}
