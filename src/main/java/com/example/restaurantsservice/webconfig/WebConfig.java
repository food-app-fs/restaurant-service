package com.example.restaurantsservice.webconfig;


import com.example.restaurantsservice.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient menuClient(){
        return WebClient
                .builder()
                .baseUrl("http://menu-service/")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public Client client(){
        HttpServiceProxyFactory proxyFactory =
                HttpServiceProxyFactory
                        .builder(WebClientAdapter.forClient(menuClient()))
                        .build();
        return proxyFactory.createClient(Client.class);
    }
}
