package com.github.adejanovski.cassandra.jdbc.codec;

import java.nio.ByteBuffer;

import org.apache.cassandra.utils.ByteBufferUtil;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;

public class TimestampToLongCodec extends TypeCodec<Long> {

	public TimestampToLongCodec(Class<Long> javaClass) {
		super(DataType.timestamp(), javaClass);
	}

	@Override
	public ByteBuffer serialize(Long paramT, ProtocolVersion paramProtocolVersion) throws InvalidTypeException {
		if (paramT == null) {
			return null;
		}
		return ByteBufferUtil.bytes(paramT);
	}

	@Override
	public Long deserialize(ByteBuffer paramByteBuffer, ProtocolVersion paramProtocolVersion) throws InvalidTypeException {
		if (paramByteBuffer == null) {
			return null;

		}
		// always duplicate the ByteBuffer instance before consuming it!
		return ByteBufferUtil.toLong(paramByteBuffer.duplicate());
	}

	@Override
	public Long parse(String paramString) throws InvalidTypeException {
		return Long.valueOf(paramString);
	}

	@Override
	public String format(Long paramT) throws InvalidTypeException {
		return String.valueOf(paramT);
	}

}
