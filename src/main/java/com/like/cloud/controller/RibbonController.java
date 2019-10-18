package com.like.cloud.controller;

import com.like.cloud.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RibbonController
 * @Description TODO
 * @Author Ke
 * @Date 2019/10/16 16:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class RibbonController {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/user/{id}")
    public UserVO findBy(@PathVariable int id){
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("cloud-client");
        String str = serviceInstance.getServiceId() + "#" + serviceInstance.getHost() + "#" + serviceInstance.getPort();
        log.info("id={}",id);
        UserVO user = new UserVO();
        user.setId(id);
        user.setName(str);

        return user;
    }


    @GetMapping("/log-instance")
    public String logUserInstance(){
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("cloud-client");
        String str = serviceInstance.getServiceId() + "#" + serviceInstance.getHost() + "#" + serviceInstance.getPort();
        return str;
    }

}
