package com.bryan.common.redis;

import com.bryan.common.utils.CollectionUtils;
import com.bryan.common.utils.DateUtil;
import com.bryan.common.utils.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * ClassName: RedisTemplateUtil
 * Function: 使用RedisTemplate操作redis<br/>
 * 利用RedisTemplate的execute方法执行redis操作，实现先从连接池申请资源，完成后再返还资源，避免创建过多的连接。
 */
public class RedisTemplateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisTemplateUtil.class);

    private static final RedisTemplate<String, Object> redisTemplate = SpringContextHolder.getBean("redisTemplate");

    @SuppressWarnings("unchecked")
    private static final RedisSerializer<String> keySerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();

    private static final FSTRedisSerializer<Object> fSTRedisSerializer = SpringContextHolder.getBean("fstRedisSerializer");

    /**
     * 设置key:value
     *
     * @param key
     * @param value
     * @param expire 过期时间(秒)
     */
    public static <T> Boolean set(final String key, final T value, final long expire) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] _key = keySerializer.serialize(key);
                    byte[] _value = fSTRedisSerializer.serialize(value);
                    connection.set(_key, _value);
                    if (expire > 0) {
                        connection.expire(_key, expire);
                    }
                    return Boolean.TRUE;
                } catch (Exception e) {
                    LOGGER.error("redis redisTemplate execute set!", e);
                    return Boolean.FALSE;
                }
            }
        }, true);
    }

    /**
     * 设置key:value，默认永久存储
     *
     * @param key
     * @param value
     */
    public static <T> Boolean set(final String key, final T value) {
        return set(key, value, -1);
    }

    public static Boolean setString(final String key, final String value, final long expire) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] _key = keySerializer.serialize(key);
                    byte[] _value = keySerializer.serialize(value);
                    connection.set(_key, _value);
                    if (expire > 0) {
                        connection.expire(_key, expire);
                    }
                    return Boolean.TRUE;
                } catch (Exception e) {
                    LOGGER.error("redis redisTemplate execute set!", e);
                    return Boolean.FALSE;
                }
            }
        }, true);
    }

    /**
     * 获取key的value
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(final String key) {
        T t = (T) redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] _key = keySerializer.serialize(key);
                    byte[] _value = connection.get(_key);
                    if (_value != null) {
                        T t = (T) fSTRedisSerializer.deserialize(_value);
                        return t;
                    }
                } catch (Exception e) {
                    LOGGER.error("redis redisTemplate execute get获取出错!", e);
                }
                return null;
            }
        }, true);
        return t;
    }

    public static String getString(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] _key = keySerializer.serialize(key);
                    byte[] _value = connection.get(_key);
                    if (_value != null) {
                        return keySerializer.deserialize(_value);
                    }
                } catch (Exception e) {
                    LOGGER.error("redis redisTemplate execute get获取出错!", e);
                }
                return null;
            }
        }, true);
    }

    /**
     * 删除key
     *
     * @param keys
     */
    public static Boolean delete(final String... keys) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                int result = 0;
                for (int i = 0; i < keys.length; i++) {
                    try {
                        connection.del(keySerializer.serialize(keys[i]));
                        result++;
                    } catch (Exception e) {
                        LOGGER.error("redis delete error!", e);
                        continue;
                    }
                }
                return result > 0;
            }
        }, true);
    }

    /**
     * 检查key是否存在
     *
     * @param key
     */
    public static Boolean exists(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    return connection.exists(keySerializer.serialize(key));
                } catch (Exception e) {
                    LOGGER.error("redis exists error!", e);
                }
                return Boolean.FALSE;
            }
        }, true);
    }

    /**
     * 设置key过期时间
     *
     * @param key
     * @param expire 过期时间(秒)
     */
    public static Boolean expire(final String key, final long expire) {
        if (expire <= 0) {
            return Boolean.FALSE;
        }
        return redisTemplate.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] _key = keySerializer.serialize(key);
                    connection.expire(_key, expire);
                } catch (Exception e) {
                    LOGGER.error("redis expire error!", e);
                }
                return Boolean.FALSE;
            }
        }, true);
    }

    /**
     * 设置key定时过期时间
     *
     * @param key
     * @param unixTime Unix时间戳(精确到秒)
     */
    public static Boolean expireAt(final String key, final long unixTime) {
        if (unixTime <= 0) {
            return Boolean.FALSE;
        }
        return redisTemplate.execute(new RedisCallback<Boolean>() {

            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] _key = keySerializer.serialize(key);
                    connection.expireAt(_key, unixTime);
                } catch (Exception e) {
                    LOGGER.error("redis expire error!", e);
                }
                return Boolean.FALSE;
            }
        }, true);
    }

    /**
     * 设置key定时过期时间
     *
     * @param key
     * @param date 本地时间
     */
    public static Boolean expireAt(final String key, final Date date) {
        if (date == null) {
            return Boolean.FALSE;
        }
        return expireAt(key, date.getTime() / 1000);
    }

    /**
     * 设置key定时过期时间
     *
     * @param key
     * @param dateString 时间字符串，格式为"yyyy-MM-dd HH:mm:ss"
     */
    public static Boolean expireAt(final String key, final String dateString) {
        if (!StringUtils.hasText(key)) {
            return Boolean.FALSE;
        }
        return expireAt(key, DateUtil.getFormatedDate(dateString, DateUtil.LONG_DATE_PATTERN));
    }

    /**
     * 从队列左边插入元素
     *
     * @param key 队列的key
     * @param v
     */
    public static <T> Long addInListLeft(final String key, final T v) {
        Assert.hasText(key, "键名不可以为空");
        Assert.notNull(v, "队列元素不可为空[null]");
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) {
                byte[] _key = keySerializer.serialize(key);
                byte[] _value = fSTRedisSerializer.serialize(v);
                return connection.lPush(_key, _value);
            }
        }, true);
    }

    /**
     * 从队列左边插入列表
     *
     * @param key  队列的key
     * @param list
     */
    public static <T> Long addInListLeft(final String key, final List<T> list) {
        Assert.hasText(key, "键名不可以为空");
        Assert.notNull(list, "队列元素不可为空[null]");
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) {
                byte[] _key = keySerializer.serialize(key);
                byte[][] rawValues = new byte[list.size()][];
                int i = 0;
                for (T v : list) {
                    rawValues[i++] = fSTRedisSerializer.serialize(v);
                }
                return connection.lPush(_key, rawValues);
            }
        }, true);
    }

    /**
     * 从队列中获取元素列表，0表示第一个元素，-1表示最后一个元素
     *
     * @param key  队列的key
     * @param from 起始位置
     * @param to   结束位置
     */
    public static <T> List<T> getReverseList(final String key, final int from, final int to) {
        Assert.hasText(key, "键名不可以为空");
      List<T> list=  getInList(key, from, to);
      Collections.reverse(list);
      return list;
    }

    /**
     * 从队列左边插入元素，如果队列长度超过length，从尾端删除多余的元素
     *
     * @param key    队列的key
     * @param length 队列指定长度
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Long addInListLeft(final String key, final T v, final long length) {
        Assert.hasText(key, "键名不可以为空");
        Assert.notNull(v, "队列元素不可为空[null]");
        ListOperations listOperations = redisTemplate.opsForList();
        Long size = listOperations.size(key);
        if (size >= length) {
            listOperations.trim(key, 0, length);//LTRIM会对list进行修剪，使其只包含指定范围的元素
        }
        return addInListLeft(key, v);
    }


    /**
     * 从队列尾端插入元素
     *
     * @param key 队列的key
     * @param v
     */
    public static <T> Long addInList(final String key, final T v) {
        Assert.hasText(key, "键名不可以为空");
        Assert.notNull(v, "队列元素不可为空[null]");
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) {
                byte[] _key = keySerializer.serialize(key);
                byte[] _value = fSTRedisSerializer.serialize(v);
                return connection.rPush(_key, _value);
            }
        }, true);
    }

    /**
     * 从队列尾端插入列表
     *
     * @param key  队列的key
     * @param list
     */
    public static <T> Long addInList(final String key, final List<T> list) {
        Assert.hasText(key, "键名不可以为空");
        Assert.notNull(list, "队列元素不可为空[null]");
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) {
                byte[] _key = keySerializer.serialize(key);
                byte[][] rawValues = new byte[list.size()][];
                int i = 0;
                for (T v : list) {
                    rawValues[i++] = fSTRedisSerializer.serialize(v);
                }
                return connection.rPush(_key, rawValues);
            }
        }, true);
    }

    /**
     * 从队列尾端插入元素，如果队列长度超过length，从头端删除多余的元素
     *
     * @param key    队列的key
     * @param length 队列指定长度
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> Long addInList(final String key, final T v, final long length) {
        Assert.hasText(key, "键名不可以为空");
        Assert.notNull(v, "队列元素不可为空[null]");
        ListOperations listOperations = redisTemplate.opsForList();
        Long size = listOperations.size(key);
        if (size >= length) {
            listOperations.trim(key, length - size + 1, -1);
        }
        return addInList(key, v);
    }

    /**
     * 从队列中获取元素列表，0表示第一个元素，-1表示最后一个元素
     *
     * @param key  队列的key
     * @param from 起始位置
     * @param to   结束位置
     */
    @SuppressWarnings({"unchecked"})
    public static <T> List<T> getInList(final String key, final int from, final int to) {
        Assert.hasText(key, "键名不可以为空");
        return redisTemplate.execute(new RedisCallback<List<T>>() {
            @Override
            public List<T> doInRedis(RedisConnection connection) {
                byte[] _key = keySerializer.serialize(key);
                List<byte[]> rawValues = connection.lRange(_key, from, to);
                List<T> resultList = new ArrayList<>(rawValues.size());
                for (byte[] bs : rawValues) {
                    resultList.add((T) fSTRedisSerializer.deserialize(bs));
                }
                return resultList;
            }
        }, true);
    }

    /**
     * Reids原子计数
     *
     * @param key
     * @return Long
     */
    public static Long atomicLong(final String key) {
        Assert.hasText(key, "键名不可以为空");
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] _key = keySerializer.serialize(key);
                return connection.incr(_key);
            }
        }, true);
    }

    /**
     * Reids原子计数
     *
     * @param key
     * @param value
     * @return Long
     */
    public static Long atomicLong(final String key, final long value) {
        Assert.hasText(key, "键名不可以为空");
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] _key = keySerializer.serialize(key);
                return connection.incrBy(_key, value);
            }
        }, true);
    }

    /**
     * Reids原子计数
     *
     * @param key
     * @param expire 生存周期(秒)
     * @return Long
     */
    public static Long atomicLongExpire(final String key, final long expire) {
        Assert.hasText(key, "键名不可以为空");
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] _key = keySerializer.serialize(key);
                Long result = connection.incr(_key);
                if (result.intValue() == 1 && expire > 0) {
                    connection.expire(_key, expire);
                }
                return result;
            }
        }, true);
    }

    public static RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * Hash设置map
     *
     * @param key
     * @param map
     */
    public static <V> Boolean hashSet(final String key, final Map<String, V> map) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] _key = keySerializer.serialize(key);
                    Map<byte[], byte[]> hashes = new HashMap<>();
                    for (Map.Entry<String, V> entry : map.entrySet()) {
                        byte[] _field = keySerializer.serialize(entry.getKey());
                        byte[] _value = fSTRedisSerializer.serialize(entry.getValue());
                        hashes.put(_field, _value);
                    }
                    connection.hMSet(_key, hashes);
                    return Boolean.TRUE;
                } catch (Exception e) {
                    LOGGER.error("redis redisTemplate execute hashSet!", e);
                    return Boolean.FALSE;
                }
            }
        }, true);
    }

    /**
     * Hash设置key:value
     *
     * @param key
     * @param value
     */
    public static <T> Boolean hashSet(final String key, final String field, final T value) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] _key = keySerializer.serialize(key);
                    byte[] _field = keySerializer.serialize(field);
                    byte[] _value = fSTRedisSerializer.serialize(value);
                    connection.hSet(_key, _field, _value);
                    return Boolean.TRUE;
                } catch (Exception e) {
                    LOGGER.error("redis redisTemplate execute hashSet!", e);
                    return Boolean.FALSE;
                }
            }
        }, true);
    }

    /**
     * Hash删除key
     *
     * @param key
     */
    public static <T> Long hashDel(final String key, final String field) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] _key = keySerializer.serialize(key);
                    byte[] _field = keySerializer.serialize(field);
                    return connection.hDel(_key, _field);
                } catch (Exception e) {
                    LOGGER.error("redis redisTemplate execute hashDel获取出错!", e);
                }
                return null;
            }
        }, true);
    }

    /**
     * Hash获取key的value
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public static <T> T hashGet(final String key, final String field) {
        return redisTemplate.execute(new RedisCallback<T>() {
            @Override
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    byte[] _key = keySerializer.serialize(key);
                    byte[] _field = keySerializer.serialize(field);
                    byte[] _value = connection.hGet(_key, _field);
                    if (_value != null) {
                        return (T) fSTRedisSerializer.deserialize(_value);
                    }
                } catch (Exception e) {
                    LOGGER.error("redis redisTemplate execute hashGet获取出错!", e);
                }
                return null;
            }
        }, true);
    }
    
    /**
     * Hash获取key的 hash所有值
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public static <T> Map<String,T> hashGetAll(final String key) {
        return redisTemplate.execute(new RedisCallback<Map<String,T>>() {
            @Override
            public Map<String,T> doInRedis(RedisConnection connection) throws DataAccessException {
            	Map<String,T> result = new HashMap<String, T>();
                try {
                    byte[] _key = keySerializer.serialize(key);
                    Map<byte[], byte[]> map = connection.hGetAll(_key);
                    if(!CollectionUtils.isEmpty(map)){
                    	for (Map.Entry<byte[], byte[]> entry : map.entrySet()) {
                    		String _field = keySerializer.deserialize(entry.getKey());
                    		T t = (T) fSTRedisSerializer.deserialize(entry.getValue());
                    		result.put(_field, t);
                    	}
                    }
                } catch (Exception e) {
                    LOGGER.error("redis redisTemplate execute hashGet获取出错!", e);
                }
                return result;
            }
        }, true);
    }

    /**
     * Hash检查key是否存在
     *
     * @param key
     */
    public static Boolean hashExists(final String key, final String field) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    return connection.hExists(keySerializer.serialize(key), keySerializer.serialize(field));
                } catch (Exception e) {
                    LOGGER.error("redis hash exists error!", e);
                }
                return Boolean.FALSE;
            }
        }, true);
    }

}
