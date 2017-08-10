package com.maria.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created   on 8/8/2017.
 */
@RestController
@RequestMapping(value = "/api/test")
public class AuthenticationController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void test() {

    }
}
