package com.github.adejanovski.cassandra.jdbc.codec;

import java.math.BigDecimal;
import java.nio.ByteBuffer;

import org.apache.cassandra.utils.ByteBufferUtil;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;

public class BigDecimalToBigintCodec extends TypeCodec<BigDecimal> {

	public BigDecimalToBigintCodec(Class<BigDecimal> javaClass) {
		super(DataType.bigint(), javaClass);
	}

	@Override
	public ByteBuffer serialize(BigDecimal paramT, ProtocolVersion paramProtocolVersion) throws InvalidTypeException {
		if (paramT == null) {
			return null;
		}
		return ByteBufferUtil.bytes(paramT.longValue());
	}

	@Override
	public BigDecimal deserialize(ByteBuffer paramByteBuffer, ProtocolVersion paramProtocolVersion) throws InvalidTypeException {
		if (paramByteBuffer == null) {
			return null;

		}
		// always duplicate the ByteBuffer instance before consuming it!
		Long value = ByteBufferUtil.toLong(paramByteBuffer.duplicate());
		return new BigDecimal(value);
	}

	@Override
	public BigDecimal parse(String paramString) throws InvalidTypeException {
		return BigDecimal.valueOf(Long.valueOf(paramString));
	}

	@Override
	public String format(BigDecimal paramT) throws InvalidTypeException {
		return String.valueOf(paramT);
	}

}
