package com.ainijar.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 数据字典
 */
@Data
@TableName("judges")
public class Judges implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;
	/**
	 * 字典名称
	 */
	@NotBlank(message="名称不能为空")
	private String name;

	private Integer orderNum;

	@TableField(exist=false)
	private Integer judgesScore;

}
