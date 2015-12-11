/*
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.github.adejanovski.cassandra.jdbc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;








import com.datastax.driver.core.DataType;
import com.datastax.driver.core.TupleValue;
import com.datastax.driver.core.UDTValue;
import com.github.adejanovski.cassandra.jdbc.ColumnDefinitions;
import com.github.adejanovski.cassandra.jdbc.ColumnDefinitions.Definition;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class MetadataRow{
	
	private ArrayList<String> entries;
	private HashMap<String,Integer> names;
	private ColumnDefinitions colDefinitions;
	private ArrayList<ColumnDefinitions.Definition> definitions;
	
	public MetadataRow(){
		entries = Lists.newArrayList();
		names = Maps.newHashMap();		
		definitions = Lists.newArrayList();
		
		
	}
	
	public MetadataRow addEntry(String key,String value){
		names.put(key,entries.size());
		entries.add(value);
		definitions.add(new Definition("","",key,DataType.text()));
		return this;
	}
	
	
	
	public UDTValue getUDTValue(int i) {
		return null;
	}


	public TupleValue getTupleValue(int i) {
		return null;
	}


	public UDTValue getUDTValue(String name) {
		return null;
	}


	public TupleValue getTupleValue(String name) {
		return null;
	}


	public ColumnDefinitions getColumnDefinitions() {
		Definition[] definitionArr = new Definition[definitions.size()];
		definitionArr = definitions.toArray(definitionArr);
		

		return new ColumnDefinitions(definitionArr);
	}


	public boolean isNull(int i) {
		return entries.get(i)==null;
	}


	public boolean isNull(String name) {
		return isNull(names.get(name));
	}

 
	public boolean getBool(int i) {
		return Boolean.parseBoolean(entries.get(i));
	}

 
	public boolean getBool(String name) {
		return getBool(names.get(name));
	}

 
	public int getInt(int i) {
		return Integer.parseInt(entries.get(i));
	}

 
	public int getInt(String name) {		
		return getInt(names.get(name));
	}

 
	public long getLong(int i) {
		return Long.parseLong(entries.get(i));
	}

 
	public long getLong(String name) {
		return getLong(names.get(name));
	}

 
	public Date getDate(int i) {
		return null;
	}

 
	public Date getDate(String name) {

		return null;
	}

 
	public float getFloat(int i) {

		return 0;
	}

 
	public float getFloat(String name) {

		return 0;
	}

 
	public double getDouble(int i) {

		return 0;
	}

 
	public double getDouble(String name) {

		return 0;
	}

 
	public ByteBuffer getBytesUnsafe(int i) {

		return null;
	}

 
	public ByteBuffer getBytesUnsafe(String name) {

		return null;
	}

 
	public ByteBuffer getBytes(int i) {

		return null;
	}

 
	public ByteBuffer getBytes(String name) {

		return null;
	}

 
	public String getString(int i) {

		
		return entries.get(i);
	}

 
	public String getString(String name) {

		return getString(names.get(name));
	}

 
	public BigInteger getVarint(int i) {

		return null;
	}

 
	public BigInteger getVarint(String name) {

		return null;
	}

 
	public BigDecimal getDecimal(int i) {

		return null;
	}

 
	public BigDecimal getDecimal(String name) {

		return null;
	}

 
	public UUID getUUID(int i) {

		return null;
	}

 
	public UUID getUUID(String name) {

		return null;
	}

 
	public InetAddress getInet(int i) {

		return null;
	}

 
	public InetAddress getInet(String name) {

		return null;
	}

 
	public <T> List<T> getList(int i, Class<T> elementsClass) {

		return null;
	}

 
	public <T> List<T> getList(String name, Class<T> elementsClass) {

		return null;
	}

 
	public <T> Set<T> getSet(int i, Class<T> elementsClass) {

		return null;
	}

 
	public <T> Set<T> getSet(String name, Class<T> elementsClass) {

		return null;
	}

 
	public <K, V> Map<K, V> getMap(int i, Class<K> keysClass,
			Class<V> valuesClass) {

		return null;
	}

 
	public <K, V> Map<K, V> getMap(String name, Class<K> keysClass,
			Class<V> valuesClass) {

		return null;
	}
	
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		for(String entry:entries){
			builder.append(entry + " -- ");
		}
		return "[" + builder.toString() + "]";
	}

}
