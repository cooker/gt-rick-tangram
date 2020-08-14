package com.github.cooker.server.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringJoiner;

/**
 * grant
 * 3/8/2020 3:16 下午
 * 描述：
 */
@RequestMapping("/")
@RestController
public class GatewayController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public void doGet(@RequestParam("auth") String auth, @RequestParam("appKey") String appKey){
        if (check(auth, appKey)){
            RequestAttributes attr = RequestContextHolder.currentRequestAttributes();
//            HttpServletRequest request = ((ServletRequestAttributes)attr).getRequest();
            HttpServletResponse response = ((ServletRequestAttributes)attr).getResponse();


            HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.add("Accept-Encoding", "gzip, deflate, br");
            httpHeaders.add("Accept", "*/*");

            HttpEntity<String> requestEntity = new HttpEntity<String>(null, httpHeaders);

            ResponseEntity<byte[]> result = restTemplate.exchange("https://baidu.com", HttpMethod.GET, requestEntity, byte[].class);

            response.setStatus(result.getStatusCodeValue());
            result.getHeaders().entrySet().stream().forEach(h->{
                StringJoiner sj = new StringJoiner(";");
                for (String v :  h.getValue()){
                    sj.add(v);
                }
                response.setHeader(h.getKey(), sj.toString());
            });
            try {
                response.getOutputStream().write(result.getBody());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @PostMapping
    public void doPost(@RequestParam("auth") String auth, @RequestParam("appKey") String appKey){
        if (check(auth, appKey)){

        }
    }


    protected boolean check(String auth, String appKey){
        if (StringUtils.equals(auth, "admin") && StringUtils.equals(appKey, "admin")){

            return true;
        }else {
            RequestAttributes attr = RequestContextHolder.currentRequestAttributes();
            HttpServletResponse response = ((ServletRequestAttributes)attr).getResponse();
            response.setStatus(500);
            try {
                response.getWriter().write("{\"code\":500,\"message\":\"server error\"}");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

    }
}
