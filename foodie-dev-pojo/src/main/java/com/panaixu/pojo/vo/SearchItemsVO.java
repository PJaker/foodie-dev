package com.panaixu.pojo.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;

/**
 * 用于展示商品搜索列表结果的VO
 */
@Builder
@Data
public class SearchItemsVO {

    private String itemId;
    private String itemName;
    private int sellCounts;
    private String imgUrl;
    private int price;
    @Tolerate
    public SearchItemsVO(){}
}
