package com.woniu.springboot.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author cl
 * @Date 2020/3/22 21:49
 */
@ApiModel(value = "com-woniu-springboot-model-Goods")
@Data
public class Goods {
    /**
     * 自增长id
     */
    @ApiModelProperty(value = "自增长id")
    private Integer id;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String name;

    /**
     * 商品价格
     */
    @ApiModelProperty(value = "商品价格")
    private Long price;
}