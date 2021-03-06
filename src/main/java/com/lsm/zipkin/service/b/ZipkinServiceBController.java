package com.lsm.zipkin.service.b;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@RestController
public class ZipkinServiceBController {

    private static final Logger LOG = Logger.getLogger(ZipkinServiceBController.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/b")
    public String callHome() {
        LOG.info("calling trace zipkin-service-a");
        return restTemplate.getForObject("http://localhost:8772/info", String.class);
    }

    /**
     * 必须加这个,否则无法追踪
     *
     * @return
     */
    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }
}