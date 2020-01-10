package com.panaixu.service;

import com.panaixu.pojo.Category;
import com.panaixu.pojo.vo.CategoryVO;
import com.panaixu.pojo.vo.NewItemsVO;

import java.util.List;

/**
 * @PACKAGE_NAME: com.panaixu.service
 * @Auther: PJaker
 * @DATE: 2020/1/2
 * @TIME: 11:18
 * @Description:
 */
public interface CategoryService {

    /**
     * 查询所有一级分类
     * @return
     */
    public List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类id查询子分类信息
     * @param rootCatId
     * @return
     */
    public List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     * @param rootCatId
     * @return
     */
    public List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);
}
