package com.ftms.ftmsapi.controller;

import com.ftms.ftmsapi.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TimeController {

    @Autowired
    TimeRepository timeRepository;
}
