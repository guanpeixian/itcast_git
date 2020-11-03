package com.itheima.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.dao.CheckItemDao;
import com.itheima.health.entity.PageResult;
import com.itheima.health.entity.QueryPageBean;
import com.itheima.health.exception.HealthException;
import com.itheima.health.pojo.CheckItem;
import com.itheima.health.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Description: No Description
 * 解决 dubbo 2.6.0 【注意，注意，注意】
 * interfaceClass 发布出去服务的接口为这个CheckItemService.class
 * 没加interfaceClass, 调用No Provider 的异常
 * User: Eric
 */
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 查询 所有检查项
     * @return
     */
    @Override
    public List<CheckItem> findAll() {

        return checkItemDao.findAll();
    }

    /**
     * 新增检查项
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {

        checkItemDao.add(checkItem);
    }

    /**
     * 分页查询 第一种方式
     * 问题：1：麻烦；2：不能通用，当前切换数据库的时候改动的代码多。
     * @param queryPageBean
     * @return
     */
    /*@Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {

        Long total = checkItemDao.total(queryPageBean);
        List<CheckItem> checkItemList = checkItemDao.findPage(queryPageBean);

        return new PageResult<>(total,checkItemList);
    }*/

    /**
     * 分页查询 第二种方式
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        //第二种，Mapper接口方式的调用，推荐这种使用方式。
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());

        //模糊查询 拼接 %
        //判断是否有查询条件
        if (!StringUtils.isEmpty(queryPageBean.getQueryString())){
            //有查询条件，拼接%
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        Page<CheckItem> page = checkItemDao.findByCondition(queryPageBean.getQueryString());
        //封装到分页结果对象中
        PageResult<CheckItem> pageResult = new PageResult<>(page.getTotal(), page.getResult());
        return pageResult;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public void deleteById(int id) throws HealthException {
        //先判断这个检查项是否被检查组使用了
        //调用dao查询检查项的id是否在t_checkgroup_checkitem表中存在记录
        int cut = checkItemDao.findCountByCheckItemId(id);
        //被使用了则不能删除
        if (cut>0){
            //已经被检查组使用了，则不能删除，报错
            //？？？ health_web能捕获到这个异常吗？
            throw new HealthException(MessageConstant.CHECKITEM_IN_USE);
        }
        //没使用的就可以调用dao删除
        checkItemDao.deleteById(id);
    }

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(int id) {
        return checkItemDao.findById(id);
    }

    /**
     * 更新
     * @param checkItem
     * @return
     */
    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }
}
