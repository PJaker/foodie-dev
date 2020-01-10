package com.panaixu.pojo.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * 6个最新商品的简单数据类型
 */
@Data
@Builder
public class SimpleItemVO {

    private String itemId;
    private String itemName;
    private String itemUrl;

   @Tolerate
    public SimpleItemVO(){}
}
