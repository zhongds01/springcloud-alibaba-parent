package com.zds.common;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * ProtoStuffSerializer
 *
 * @author zhongdongsheng
 * @since 2022/5/4
 */
public class ProtoStuffSerializer implements RedisSerializer<Object> {

    private static class ProtoStuffWrapper {
        public Object data;

        public ProtoStuffWrapper() {
        }

        public ProtoStuffWrapper(Object data) {
            this.data = data;
        }
    }

    private final Schema<ProtoStuffWrapper> SCHEMA = RuntimeSchema.getSchema(ProtoStuffWrapper.class);

    @Override
    public byte[] serialize(Object object) throws SerializationException {
        if (object == null) {
            return new byte[0];
        }
        ProtoStuffWrapper protoStuffWrapper = new ProtoStuffWrapper(object);
        LinkedBuffer linkedBuffer =
            LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE * LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            return ProtostuffIOUtil.toByteArray(protoStuffWrapper, SCHEMA, linkedBuffer);
        } finally {
            linkedBuffer.clear();
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        ProtoStuffWrapper newMessage = SCHEMA.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, newMessage, SCHEMA);
        return newMessage.data;
    }
}
