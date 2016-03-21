package com.github.adejanovski.cassandra.jdbc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.TupleValue;
import com.datastax.driver.core.UDTValue;
import com.datastax.driver.core.DataType.Name;
import com.datastax.driver.core.exceptions.DriverInternalError;
import com.google.common.collect.Maps;

public enum DataTypeEnum {

        ASCII     (1,  String.class, DataType.Name.ASCII),
        BIGINT    (2,  Long.class, DataType.Name.BIGINT),
        BLOB      (3,  ByteBuffer.class, DataType.Name.BLOB),
        BOOLEAN   (4,  Boolean.class, DataType.Name.BOOLEAN),
        COUNTER   (5,  Long.class, DataType.Name.COUNTER),
        DECIMAL   (6,  BigDecimal.class, DataType.Name.DECIMAL),
        DOUBLE    (7,  Double.class, DataType.Name.DOUBLE),
        FLOAT     (8,  Float.class, DataType.Name.FLOAT),
        INET      (16, InetAddress.class, DataType.Name.INET),
        INT       (9,  Integer.class, DataType.Name.INT),
        TEXT      (10, String.class, DataType.Name.TEXT),
        TIMESTAMP (11, Date.class, DataType.Name.TIMESTAMP),
        UUID      (12, UUID.class, DataType.Name.UUID),
        VARCHAR   (13, String.class, DataType.Name.VARCHAR),
        VARINT    (14, BigInteger.class, DataType.Name.VARINT),
        TIMEUUID  (15, UUID.class, DataType.Name.TIMEUUID),
        LIST      (32, List.class, DataType.Name.LIST),
        SET       (34, Set.class, DataType.Name.SET),
        MAP       (33, Map.class, DataType.Name.MAP),
        UDT       (48, UDTValue.class, DataType.Name.UDT),
        TUPLE     (49, TupleValue.class, DataType.Name.TUPLE),
        CUSTOM    (0,  ByteBuffer.class, DataType.Name.CUSTOM),       
        SMALLINT  (19, Integer.class, DataType.Name.SMALLINT),
        TINYINT   (20, Integer.class, DataType.Name.TINYINT),
        DATE      (17, Date.class, DataType.Name.DATE),
        TIME      (18, Date.class, DataType.Name.TIME);

        final int protocolId;
        final Class<?> javaType;
        final Name cqlType;

        private static final DataTypeEnum[] nameToIds;
        private static final Map<DataType.Name, DataTypeEnum> cqlDataTypeToDataType;
        
        static {
        	
        	cqlDataTypeToDataType = Maps.newHashMap();
        	
            int maxCode = -1;
            for (DataTypeEnum name : DataTypeEnum.values())
                maxCode = Math.max(maxCode, name.protocolId);
            nameToIds = new DataTypeEnum[maxCode + 1];
            for (DataTypeEnum name : DataTypeEnum.values()) {
                if (nameToIds[name.protocolId] != null)
                    throw new IllegalStateException("Duplicate Id");
                nameToIds[name.protocolId] = name;
                
                cqlDataTypeToDataType.put(name.cqlType, name);
            }
        }

        private DataTypeEnum(int protocolId, Class<?> javaType, Name cqlType) {
            this.protocolId = protocolId;
            this.javaType = javaType;
            this.cqlType = cqlType;
        }

        
        static DataTypeEnum fromCqlTypeName(DataType.Name cqlTypeName) {
        	return cqlDataTypeToDataType.get(cqlTypeName);
        }
        
        static DataTypeEnum fromProtocolId(int id) {
        	DataTypeEnum name = nameToIds[id];
            if (name == null)
                throw new DriverInternalError("Unknown data type protocol id: " + id);
            return name;
        }

        /**
         * Returns whether this data type name represent the name of a collection type
         * that is a list, set or map.
         *
         * @return whether this data type name represent the name of a collection type.
         */
        public boolean isCollection() {
            switch (this) {
                case LIST:
                case SET:
                case MAP:
                    return true;
                default:
                    return false;
            }
        }

        /**
         * Returns the Java Class corresponding to this CQL type name.
         *
         * The correspondence between CQL types and java ones is as follow:
         * <table>
         *   <caption>DataType to Java class correspondence</caption>
         *   <tr><th>DataType (CQL)</th><th>Java Class</th></tr>
         *   <tr><td>ASCII         </td><td>String</td></tr>
         *   <tr><td>BIGINT        </td><td>Long</td></tr>
         *   <tr><td>BLOB          </td><td>ByteBuffer</td></tr>
         *   <tr><td>BOOLEAN       </td><td>Boolean</td></tr>
         *   <tr><td>COUNTER       </td><td>Long</td></tr>
         *   <tr><td>CUSTOM        </td><td>ByteBuffer</td></tr>
         *   <tr><td>DECIMAL       </td><td>BigDecimal</td></tr>
         *   <tr><td>DOUBLE        </td><td>Double</td></tr>
         *   <tr><td>FLOAT         </td><td>Float</td></tr>
         *   <tr><td>INET          </td><td>InetAddress</td></tr>
         *   <tr><td>INT           </td><td>Integer</td></tr>
         *   <tr><td>LIST          </td><td>List</td></tr>
         *   <tr><td>MAP           </td><td>Map</td></tr>
         *   <tr><td>SET           </td><td>Set</td></tr>
         *   <tr><td>TEXT          </td><td>String</td></tr>
         *   <tr><td>TIMESTAMP     </td><td>Date</td></tr>
         *   <tr><td>UUID          </td><td>UUID</td></tr>
         *   <tr><td>UDT           </td><td>UDTValue</td></tr>
         *   <tr><td>TUPLE         </td><td>TupleValue</td></tr>
         *   <tr><td>VARCHAR       </td><td>String</td></tr>
         *   <tr><td>VARINT        </td><td>BigInteger</td></tr>
         *   <tr><td>TIMEUUID      </td><td>UUID</td></tr>
         * </table>
         *
         * @return the java Class corresponding to this CQL type name.
         */
        public Class<?> asJavaClass() {
            return javaType;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
   }



