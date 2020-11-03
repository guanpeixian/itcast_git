package com.itheima.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.health.constant.MessageConstant;
import com.itheima.health.entity.Result;
import com.itheima.health.pojo.CheckGroup;
import com.itheima.health.service.CheckGroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author guanpx
 * @create 2020/11/2 22:16
 * @description
 */
@Controller
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @PostMapping("add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){

        //调用业务服务
        checkGroupService.add(checkGroup,checkitemIds);
        //响应结果
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }
}
