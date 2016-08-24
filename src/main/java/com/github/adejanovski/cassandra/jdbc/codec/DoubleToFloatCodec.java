package com.github.adejanovski.cassandra.jdbc.codec;

import java.math.BigDecimal;
import java.nio.ByteBuffer;

import org.apache.cassandra.utils.ByteBufferUtil;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.ProtocolVersion;
import com.datastax.driver.core.TypeCodec;
import com.datastax.driver.core.exceptions.InvalidTypeException;

public class DoubleToFloatCodec extends TypeCodec<Double> {

	public DoubleToFloatCodec(Class<Double> javaClass) {
		super(DataType.cfloat(), javaClass);
	}

	@Override
	public ByteBuffer serialize(Double paramT, ProtocolVersion paramProtocolVersion) throws InvalidTypeException {
		if (paramT == null) {
			return null;
		}
		return ByteBufferUtil.bytes(paramT.floatValue());
	}

	@Override
	public Double deserialize(ByteBuffer paramByteBuffer, ProtocolVersion paramProtocolVersion) throws InvalidTypeException {
		if (paramByteBuffer == null) {
			return null;

		}
		// always duplicate the ByteBuffer instance before consuming it!
		Float value = ByteBufferUtil.toFloat(paramByteBuffer.duplicate());		
		return value.doubleValue();
	}

	@Override
	public Double parse(String paramString) throws InvalidTypeException {
		return Double.valueOf(paramString);
	}

	@Override
	public String format(Double paramT) throws InvalidTypeException {
		return String.valueOf(paramT);
	}

}
