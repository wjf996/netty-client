package com.netty.client.dto

import lombok.Data

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull


/**
 * @author WangJunfeng
 * @version 1.0
 * @description: 消息接收dto
 * @date 2020/8/28 16:35
 */
@Data
class Person {
    @NotBlank(message = "名称不能为空")
    String name
    @NotNull(message = "年龄不能为空")
    Integer age
}
