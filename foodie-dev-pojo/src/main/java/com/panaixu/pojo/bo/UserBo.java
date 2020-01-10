package com.panaixu.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @PACKAGE_NAME: com.panaixu.pojo.bo
 * @Auther: PJaker
 * @DATE: 2019/12/24
 * @TIME: 12:12
 * @Description:
 */
@Data
@Builder
@ApiModel(value = "用户对象BO",description = "从客户端，由用户传入的数据封装再次entity中")
public class UserBo {
    @ApiModelProperty(value = "用户名称",name = "username",example = "Tom",required = true)
    private String username;
    @ApiModelProperty(value = "用户密码",name = "password",example = "123456",required = true)
    private String password;
    @ApiModelProperty(value = "用户确认密码",name = "confirmPassword",example = "123456",required = false)
    private String confirmPassword;

    @Tolerate
    public UserBo() {

    }
}
