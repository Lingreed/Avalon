package com.bryan.quartz.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bryan.common.constant.scheduler.SchedulerConstant;
import com.bryan.common.utils.StringUtil;
import com.bryan.dao.scheduler.domain.SchedulerTask;
import com.bryan.quartz.scheduler.JobRegisterHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzSchedulerTask extends BaseTask {
    private static final Logger logger = LoggerFactory.getLogger(QuartzSchedulerTask.class);

    @Override
    public void doTask(String body) {
        logger.info("调度执行,body:{}", body);
        try {
            if (StringUtil.isNotBlank(body)) {
                JSONObject jsonObj = JSON.parseObject(body);
                String optTag = jsonObj.getString("bizType");
                SchedulerTask task = jsonObj.getObject("schedulerTask", SchedulerTask.class);

                if (SchedulerConstant.OPT_TAG_DEPLOY.equals(optTag)) {
                    // 部署调度
                    JobRegisterHandle.deployScheduler(task);
                }

                if (SchedulerConstant.OPT_TAG_INSTALL.equals(optTag)) {
                    // 立即执行
                    JobRegisterHandle.instantScheduler(task);
                }

                if (SchedulerConstant.OPT_TAG_STOP.equals(optTag)) {
                    // 暂停
                    JobRegisterHandle.suspendScheduler(task);
                }

                if (SchedulerConstant.OPT_TAG_RECOVERY.equals(optTag)) {
                    // 暂停
                    JobRegisterHandle.recoveryScheduler(task);
                }

                if (SchedulerConstant.OPT_TAG_DELETE.equals(optTag)) {
                    // 删除
                    JobRegisterHandle.deleteScheduler(task);
                }
            } else {
                logger.info("调度消息body为空,不在执行!!!");
            }
        } catch (Exception e) {
            logger.error("调度异步通知错误", e);
        }
    }

}
