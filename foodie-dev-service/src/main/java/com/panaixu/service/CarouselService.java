package com.panaixu.service;

import com.panaixu.pojo.Carousel;

import java.util.List;

/**
 * @PACKAGE_NAME: com.panaixu.service
 * @Auther: PJaker
 * @DATE: 2020/1/2
 * @TIME: 10:49
 * @Description: 轮播图
 */
public interface CarouselService {

    /**
     * 查询所有轮播图列表
     * @param isShow
     * @return
     */
    public List<Carousel> queryAll(Integer isShow);
}
