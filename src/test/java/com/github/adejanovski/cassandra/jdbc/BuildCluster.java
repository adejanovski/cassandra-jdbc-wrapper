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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.datastax.driver.core.Cluster;

public class BuildCluster {
	
	
	public static String HOST = System.getProperty("host", ConnectionDetails.getHost());
	public static CCMBridge ccmBridge = null;
	
	@BeforeSuite
    public static void setUpBeforeSuite() throws Exception
    {   
		if(System.getProperty("cassandra.version")==null){
			CCMBridge.setCassandraVersion("2.1.9");
		}
		ccmBridge = CCMBridge.create("jdbc_cluster"+ new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()), 1, 1);
    	ccmBridge.waitForUp(1);
    	ccmBridge.waitForUp(2);    	
    	HOST = CCMBridge.ipOfNode(1);        
   
	    }

	    
	@AfterSuite
	public static void tearDownAfterSuite() throws Exception
	{	    	
        System.out.println("Stopping nodes");
        ccmBridge.forceStop();        
        ccmBridge.waitForDown(1);
        System.out.println("Discarding cluster");
        ccmBridge.remove();
	}
	    


}
