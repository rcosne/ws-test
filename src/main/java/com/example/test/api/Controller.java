package com.example.test.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class Controller {

    protected static final Logger LOG = LoggerFactory.getLogger(Controller.class);

    @GetMapping("/test")
    public void test() {
        LOG.info("------------------------- test !");
    }
}
