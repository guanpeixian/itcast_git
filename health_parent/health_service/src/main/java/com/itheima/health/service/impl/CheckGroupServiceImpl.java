package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.health.dao.CheckGroupDao;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author guanpx
 * @create 2020/11/2 22:23
 * @description
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //添加检查组
        checkGroupDao.add(checkGroup,checkitemIds);
        //获取检查组的id
        Integer checkGroupId = checkGroup.getId();
        //遍历检查项id，添加检查组与检查项的关系
        if(checkitemIds != null){
            //有勾选
            for (Integer checkitemId : checkitemIds) {
                //添加检查组与检查项的关系
                checkGroupDao.addCheckGroupCheckItem(checkGroupId,checkitemId);
            }
        }
    }
}
