package com.bryan.common.redis;

import org.nustaq.serialization.FSTConfiguration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.stereotype.Component;

/**
 * ClassName: FSTRedisSerializer
 * Function: 高效序列化反序列化框架FST的实现
 */
@Component("fstRedisSerializer")
public class FSTRedisSerializer<T> implements RedisSerializer<T> {

    private static final FSTConfiguration fstConfiguration = FSTConfiguration.createDefaultConfiguration();

    /**
     * 序列化
     *
     * @param t
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
        byte[] target = null;
        try {
            target = fstConfiguration.asByteArray(t);
        } catch (Exception e) {
            throw new SerializationException(" FST serialize error", e);
        }
        return target;
    }

    /**
     * 反序列化
     *
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        T target = null;
        try {
            if (bytes == null || bytes.length < 1) {
                return null;
            }
            target = (T) fstConfiguration.asObject(bytes);
        } catch (Exception e) {
            throw new SerializationException("FST deserialize error", e);
        }

        return target;
    }

}