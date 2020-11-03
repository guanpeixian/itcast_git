package com.itheima.health.dao;

import com.github.pagehelper.Page;
import com.itheima.health.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {

    /**
     * 查询 所有检查项
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 新增检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询 第一种方式
     * 问题：1：麻烦；2：不能通用，当前切换数据库的时候改动的代码多。
     * @param queryPageBean
     * @return
     */
    /*Long total(QueryPageBean queryPageBean);
    List<CheckItem> findPage(QueryPageBean queryPageBean);*/

    /**
     * 分页条件查询 第二种方式
     * Mapper接口方式的调用，推荐这种使用方式。
     * @param queryString
     * @return
     */
    Page<CheckItem> findByCondition(String queryString);

    /**
     * 检查 检查项是否被检查组使用了
     * @param id
     * @return
     */
    int findCountByCheckItemId(int id);

    /**
     * 删除
     * @param id
     * @return
     */
    void deleteById(int id);

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
