package com.itheima.health.service;

import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {

    /**
     * 查询所有的检查项
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 新增检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    /**
     * 删除
     * @param id
     * @return
     */
    void deleteById(int id) throws HealthException;

    /**
     * 通过id查询
     * @param id
     * @return
     */
    CheckItem findById(int id);


    /**
     * 更新
     * @param checkItem
     * @return
     */
    void update(CheckItem checkItem);

}
