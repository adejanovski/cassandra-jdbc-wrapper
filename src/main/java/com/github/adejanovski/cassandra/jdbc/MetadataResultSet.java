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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.ExecutionInfo;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.google.common.util.concurrent.ListenableFuture;

public class MetadataResultSet{
	
	

	private ArrayList<MetadataRow> rows;
	
	public MetadataResultSet(){
		
	}
	
	public MetadataResultSet setRows(ArrayList<MetadataRow> schemas){
		this.rows = schemas;
		return this;
	}
	

	public ColumnDefinitions getColumnDefinitions() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean isExhausted() {
		// TODO Auto-generated method stub
		return false;
	}


	public Row one() {
		// TODO Auto-generated method stub
		return null;
	}


	public List<MetadataRow> all() {
		// TODO Auto-generated method stub
		return rows;
	}


	public Iterator<MetadataRow> iterator() {
		// TODO Auto-generated method stub
		return rows.iterator();
	}


	public int getAvailableWithoutFetching() {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean isFullyFetched() {
		// TODO Auto-generated method stub
		return false;
	}


	public ListenableFuture<Void> fetchMoreResults() {
		// TODO Auto-generated method stub
		return null;
	}


	public ExecutionInfo getExecutionInfo() {
		// TODO Auto-generated method stub
		return null;
	}


	public List<ExecutionInfo> getAllExecutionInfo() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean wasApplied() {
		// TODO Auto-generated method stub
		return false;
	}

}
