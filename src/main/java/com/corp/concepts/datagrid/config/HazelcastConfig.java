package com.corp.concepts.datagrid.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

@Configuration
public class HazelcastConfig {

    @Bean
    public ClientConfig clientConfig() {
        ClientConfig config = new ClientConfig();
        ClientNetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.addAddress("localhost:5702", "localhost:5703");
        networkConfig.setSmartRouting(false);
        networkConfig.setRedoOperation(true);
        networkConfig.setConnectionTimeout(30000);
        config.setInstanceName("hz-client-"+ Instant.now().getEpochSecond());

        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance(ClientConfig clientConfig) {
        return HazelcastClient.newHazelcastClient(clientConfig);
    }

}
