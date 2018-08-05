package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebApi {

    private static int count = 0;

    @Autowired
    private Publisher publisher;

    @GetMapping("/send")
    public void send() {
        count++;
        publisher.sendAutoCloseOrderMessage("ID：" + count);
    }
}
