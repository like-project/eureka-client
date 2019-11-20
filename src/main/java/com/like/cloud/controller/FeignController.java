package com.like.cloud.controller;

import com.like.cloud.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName FeignController
 * @Description feign
 * @Author Ke
 * @Date 2019/10/18 18:18
 * @Version 1.0
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class FeignController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/feign/{id}")
    public UserVO findBy(@PathVariable int id){
        log.info("id={}",id);
        UserVO user = new UserVO();
        user.setId(id);
        user.setName("port:" + port);
        return user;
    }
}
