package com.wondersgroup.swaggerdemo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * <p>Copyright:2018-2100万达信息股份有限公司 版权所有</p>
 * <p>Company:万达股份有限公司</p>
 *
 * @author Wang si rui
 * @version 1.0
 * @Date
 * @Rewrite record： 1、
 */
@ApiModel(description = "Class representing a person tracked by the application.")
@Data
public class Person {

  @ApiModelProperty(notes = "Unique identifier of the person. No two persons can have the same id.",example = "1", required = true, position = 0)
  @NotNull
  private Integer id;

  @ApiModelProperty(notes = "First name of the person.", example = "John", required = true, position = 1)
  @NotBlank
  @Size(min = 1, max = 20)
  private String firstName;

  @ApiModelProperty(notes = "Last name of the person.", example = "Doe", required = true, position = 2)
  @NotBlank
  private String lastName;

  @ApiModelProperty(notes = "Age of the person. Non-negative integer", example = "42", position = 3)
  @Min(0)
  @Max(100)
  private Integer age;
}
