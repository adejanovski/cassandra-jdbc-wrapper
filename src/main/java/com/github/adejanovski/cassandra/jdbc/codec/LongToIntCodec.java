package com.github.adejanovski.cassandra.jdbc.codec;

import java.nio.ByteBuffer;

import org.apache.cassandra.utils.ByteBufferUtil;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;

public class LongToIntCodec extends TypeCodec<Integer> {

	public LongToIntCodec(Class<Integer> javaClass) {
		super(DataType.bigint(), javaClass);
	}

	@Override
	public ByteBuffer serialize(Integer paramT, ProtocolVersion paramProtocolVersion) throws InvalidTypeException {
		if (paramT == null) {
			return null;
		}
		return ByteBufferUtil.bytes(paramT);
	}

	@Override
	public Integer deserialize(ByteBuffer paramByteBuffer, ProtocolVersion paramProtocolVersion) throws InvalidTypeException {
		if (paramByteBuffer == null) {
			return null;

		}
		// always duplicate the ByteBuffer instance before consuming it!
		Long value = ByteBufferUtil.toLong(paramByteBuffer.duplicate());
		return value.intValue();
	}

	@Override
	public Integer parse(String paramString) throws InvalidTypeException {
		return Integer.valueOf(paramString);
	}

	@Override
	public String format(Integer paramT) throws InvalidTypeException {
		return String.valueOf(paramT);
	}

}
