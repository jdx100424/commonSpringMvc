package com.maoshen.redis.serializer;

import java.nio.charset.Charset;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class ObjectRedisSerializer implements RedisSerializer<Object> {
	public final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	final byte[] EMPTY_ARRAY = new byte[0];

	private final JavaType javaType;

	private ObjectMapper objectMapper = new ObjectMapper();

	public ObjectRedisSerializer() {
		Class<Object> type = Object.class;
		this.javaType = getJavaType(type);
	}

	public ObjectRedisSerializer(JavaType javaType) {
		this.javaType = javaType;
	}

	boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}

	public Object deserialize(byte[] bytes) throws SerializationException {
		if (isEmpty(bytes)) {
			return null;
		}
		try {
			return this.objectMapper
					.readValue(bytes, 0, bytes.length, javaType);
		} catch (Exception ex) {
			throw new SerializationException("Could not read JSON: "
					+ ex.getMessage(), ex);
		}
	}

	public byte[] serialize(Object t) throws SerializationException {
		if (t == null) {
			return EMPTY_ARRAY;
		}
		try {
			return this.objectMapper.writeValueAsBytes(t);
		} catch (Exception ex) {
			throw new SerializationException("Could not write JSON: "
					+ ex.getMessage(), ex);
		}
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		Assert.notNull(objectMapper, "'objectMapper' must not be null");
		this.objectMapper = objectMapper;
	}

	protected JavaType getJavaType(Class<?> clazz) {
		return TypeFactory.defaultInstance().constructType(clazz);
	}
}