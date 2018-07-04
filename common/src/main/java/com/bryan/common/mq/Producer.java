package com.bryan.common.mq;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.bryan.common.config.SysConfigUtil;
import com.bryan.common.config.SysKey;
import com.bryan.common.constant.mq.MqConstant;
import com.bryan.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * rocketMq 生产者
 *
 * @author Bryan.Lin
 * @version 1.0
 * @date 2018/7/4
 */
public class Producer {

    private static Logger logger = LoggerFactory.getLogger(Producer.class);

    private static DefaultMQProducer producer = null;

    /**
     * 启动rocketMq
     */
    public static void initMqProducer() {
        try {
            if (producer == null) {
                /**
                 * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
                 * 注意：ProducerGroupName需要由应用来保证唯一<br>
                 * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
                 * 因为服务器会回查这个Group下的任意一个Producer
                 */
                producer = new DefaultMQProducer(MqConstant.MQ_GROUP_NAME);

                /**
                 * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
                 * 注意：切记不可以在每次发送消息时，都调用start方法
                 */
                producer.setNamesrvAddr(SysConfigUtil.getString(SysKey.NAMESRV_ADDR));
                producer.start();

                logger.info("producer初始化成功,SUCCESS");
            } else {
                logger.info("producer存在,不再初始化");
            }
        } catch (Exception e) {
            logger.error("系统初始化生产者错误!!!", e);
            throw new ServiceException("系统初始化生产者错误!!!");
        }

    }

    /**
     * @Title: stopProducer
     * @Description: 在contextFinalizer, 销毁上下文的时候，关闭producer
     */
    public static void stopProducer() {
        if (producer != null) {
            producer.shutdown();
            logger.info("程序停止,producer关闭成功");
        }
    }

    /**
     * 发送消息
     *
     * @param topic
     * @param tag
     * @param key
     * @param body
     */
    public static void sendMq(String topic, String tag, String key, String body) {
        try {
            /**
             * 下面这段代码表明一个Producer对象可以发送多个topic，多个tag的消息。
             * 注意：send方法是同步调用，只要不抛异常就标识成功。但是发送成功也可会有多种状态，<br>
             * 例如消息写入Master成功，但是Slave不成功，这种情况消息属于成功，但是对于个别应用如果对消息可靠性要求极高，<br>
             * 需要对这种情况做处理。另外，消息可能会存在发送失败的情况，失败重试由应用来处理。
             */
            Message msg = new Message(
                    topic,// topic
                    tag,// tag
                    key,// key
                    body.getBytes());// body
            SendResult sendResult = producer.send(msg);
            logger.info("消息tag-{},body-{},发送结果:{}", tag, body, sendResult);
        } catch (Exception e) {
            logger.error(tag + "发送消息失败", e);
            throw new ServiceException("发送消息失败");
        }
    }

    /**
     * 发送项目mq
     * key要保持唯一,投资\还款\回款\可以使用相关的订单号
     *
     * @param key
     * @param body
     */
    public static void sendProjectMq(String key, String body) {
        try {
            Message msg = new Message(
                    MqConstant.TOPIC,// topic
                    MqConstant.TOPIC_TAG_PROJECT,// tag
                    key,// key
                    body.getBytes());// body
            SendResult sendResult = producer.send(msg);
            logger.info("项目mq,key:{},消息body-{},发送结果:{}", key, body, sendResult);
        } catch (Exception e) {
            logger.error("项目mq,key:{},发送消息失败", key, e);
            throw new ServiceException("发送消息失败");
        }
    }

    /**
     * 发送调度mq
     * key要保持唯一,可以使用主键
     *
     * @param key
     * @param body
     */
    public static void sendSchedulerMq(String key, String body) {
        try {
            Message msg = new Message(
                    MqConstant.TOPIC,// topic
                    MqConstant.TOPIC_TAG_SCHEDULER,// tag
                    key,// key
                    body.getBytes());// body
            SendResult sendResult = producer.send(msg);
            logger.info("调度mq,key:{},消息body-{},发送结果:{}", key, body, sendResult);
        } catch (Exception e) {
            logger.error("调度mq,key:{},发送消息失败", key, e);
            throw new ServiceException("发送消息失败");
        }
    }

    /**
     * 发送活动mq
     * key要保持唯一,可以使用主键
     *
     * @param key
     * @param body
     */
    public static void sendActtMq(String key, String body) {
        try {
            Message msg = new Message(
                    MqConstant.TOPIC,// topic
                    MqConstant.TOPIC_TAG_ACTT,// tag
                    key,// key
                    body.getBytes());// body
            SendResult sendResult = producer.send(msg);
            logger.info("活动mq,key:{},消息body-{},发送结果:{}", key, body, sendResult);
        } catch (Exception e) {
            logger.error("活动mq,key:{},发送消息失败", key, e);
            throw new ServiceException("发送消息失败");
        }
    }

    /**
     * 发送统计mq
     * key要保持唯一,可以使用主键
     *
     * @param key
     * @param body
     */
    public static void sendStat(String key, String body) {
        try {
            Message msg = new Message(
                    MqConstant.TOPIC,// topic
                    MqConstant.TOPIC_TAG_STAT,// tag
                    key,// key
                    body.getBytes());// body
            SendResult sendResult = producer.send(msg);
            logger.info("发送统计mq,key:{},消息body-{},发送结果:{}", key, body, sendResult);
        } catch (Exception e) {
            logger.error("发送统计mq,key:{},发送消息失败", key, e);
            throw new ServiceException("发送消息失败");
        }
    }

    /**
     * 发送消息（短信，站内信，邮件，推送）mq
     * key要保持唯一,可以使用主键
     *
     * @param key
     * @param body
     */
    public static void sendMsgMq(String key, String body) {
        try {
            Message msg = new Message(
                    MqConstant.TOPIC,// topic
                    MqConstant.TOPIC_TAG_MSG,// tag
                    key,// key
                    body.getBytes());// body
            SendResult sendResult = producer.send(msg);
            logger.info("发送消息（短信，站内信，邮件，推送）mq,key:{},消息body-{},发送结果:{}", key, body, sendResult);
        } catch (Exception e) {
            logger.error("发送消息（短信，站内信，邮件，推送）mq,key:{},发送消息失败", key, e);
            throw new ServiceException("发送消息失败");
        }
    }

    /**
     * 发送银行类通知(充值\提现等接口)mq
     * key要保持唯一,可以使用主键
     *
     * @param key
     * @param body
     */
    public static void sendBankMq(String key, String body) {
        try {
            Message msg = new Message(
                    MqConstant.TOPIC,// topic
                    MqConstant.TOPIC_TAG_BANK,// tag
                    key,// key
                    body.getBytes());// body
            SendResult sendResult = producer.send(msg);
            logger.info("bank mq,key:{},消息body-{},发送结果:{}", key, body, sendResult);
        } catch (Exception e) {
            logger.error("bank mq,key:{},发送消息失败", key, e);
            throw new ServiceException("发送消息失败");
        }
    }

    /**
     * 节日活动业务
     * key要保持唯一,可以使用主键
     *
     * @param key
     * @param body
     */
    public static void sendDayMq(String key, String body) {
        try {
            Message msg = new Message(
                    MqConstant.TOPIC,// topic
                    MqConstant.TOPIC_TAG_DAY,// tag
                    key,// key
                    body.getBytes());// body
            SendResult sendResult = producer.send(msg);
            logger.info("节日活动mq,key:{},消息body-{},发送结果:{}", key, body, sendResult);
        } catch (Exception e) {
            logger.error("节日活动mq,key:{},发送消息失败", key, e);
            throw new ServiceException("发送消息失败");
        }
    }
}
