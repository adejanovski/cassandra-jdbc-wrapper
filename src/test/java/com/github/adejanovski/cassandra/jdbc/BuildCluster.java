package com.github.adejanovski.cassandra.jdbc;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.Session;

public class BuildCluster {

	private final static Logger logger = LoggerFactory.getLogger(BuildCluster.class);

	public static String HOST = System.getProperty("host", ConnectionDetails.getHost());
	public static CCMBridge ccmBridge = null;
	public static Cluster cluster = null;
	public static Session session = null;
	public static Cluster cluster2 = null;
	public static Session session2 = null;
	public static Session sessionFluks = null;
	public static boolean clusterHasBuilt = false;
	public static boolean dynamicCluster = false;

	@BeforeSuite(groups={"init"})
	public static void setUpBeforeSuite() throws Exception {		
		System.setProperty("cassandra.version", "3.0.4");
		System.setProperty("ipprefix","127.0.0.");
		if(!isClusterActive()){
			ccmBridge = CCMBridge.create("jdbc_cluster" + new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()), 1, 1);
			ccmBridge.waitForUp(1);
			//ccmBridge.waitForUp(2);
			HOST = CCMBridge.ipOfNode(1);
			dynamicCluster = true;
		}
		else{
			HOST = "127.0.0.1";
		}
		
		clusterHasBuilt = true;		
	}
	
		
	@AfterSuite(groups={"init"})
	public static void tearDownAfterSuite() throws Exception {		
		System.out.println("CLOSING CASSANDRA CONNECTION");		
		if(dynamicCluster){
			System.out.println("Stopping nodes");
			clusterHasBuilt = false;
			try{
				ccmBridge.forceStop();			
				System.out.println("Discarding cluster");
				ccmBridge.remove();
				HOST = System.getProperty("host", ConnectionDetails.getHost());
			}catch(Exception e){
				System.out.println("Silent error discarding cluster");
			}
		}
	}
	
	
	public static boolean isClusterActive(){
		try{
			Builder builder = Cluster.builder().withQueryOptions(new QueryOptions().setConsistencyLevel(ConsistencyLevel.QUORUM).setSerialConsistencyLevel(ConsistencyLevel.LOCAL_SERIAL));
			cluster = builder.addContactPoint("127.0.0.1").build();
			session = cluster.connect();
			return true;
		} catch(Exception e){
			return false;			
		}
		
	}

}
