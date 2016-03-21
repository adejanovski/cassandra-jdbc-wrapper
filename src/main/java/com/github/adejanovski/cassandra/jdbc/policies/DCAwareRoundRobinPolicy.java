package com.github.adejanovski.cassandra.jdbc.policies;

import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy.*;

public class DCAwareRoundRobinPolicy {
	
	Builder builder = com.datastax.driver.core.policies.DCAwareRoundRobinPolicy.builder();
			
	
	public DCAwareRoundRobinPolicy(String localDc) {		
        builder.withLocalDc(localDc);                        
    }
	
	public DCAwareRoundRobinPolicy(String localDc, int usedHostsPerRemoteDc) {		
        builder.withLocalDc(localDc)
        	   .withUsedHostsPerRemoteDc(usedHostsPerRemoteDc);        
                
    }
	
	public DCAwareRoundRobinPolicy(String localDc, int usedHostsPerRemoteDc, boolean allowRemoteDCsForLocalConsistencyLevel) {		
        builder.withLocalDc(localDc)
               .withUsedHostsPerRemoteDc(usedHostsPerRemoteDc);
        
        if(allowRemoteDCsForLocalConsistencyLevel){
        	builder.allowRemoteDCsForLocalConsistencyLevel();
        }
                
    }
	
	public com.datastax.driver.core.policies.DCAwareRoundRobinPolicy build(){
		return builder.build();
	}

}
