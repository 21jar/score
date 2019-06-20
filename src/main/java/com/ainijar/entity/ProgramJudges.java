package com.ainijar.entity;

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
@TableName("program_judges")
public class ProgramJudges implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;
	private Long programId;
	private Long judgesId;
	private Integer score;

}
