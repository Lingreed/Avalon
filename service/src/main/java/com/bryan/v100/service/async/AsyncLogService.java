package com.bryan.v100.service.async;

import com.bryan.sys.domain.SysLogManage;
import com.bryan.v100.service.sys.SysLogManageService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/1/29
 */
@Service("asyncLogService")
public class AsyncLogService {
    @Resource
    private SysLogManageService sysLogManageService;

    /**
     * @Title: saveLogManage
     * @Description: 请求保存日志
     * @param log
     */
    @Async
    public void saveLogManage(SysLogManage log){
        sysLogManageService.insertSelective(log);
    }
}
