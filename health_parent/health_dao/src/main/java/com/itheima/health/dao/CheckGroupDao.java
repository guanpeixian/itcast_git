package com.itheima.health.dao;

import com.itheima.health.pojo.CheckGroup;

public interface CheckGroupDao {
    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 添加检查组与检查项的关系
     * @param checkGroupId
     * @param checkitemId
     */
    void addCheckGroupCheckItem(Integer checkGroupId, Integer checkitemId);
}
