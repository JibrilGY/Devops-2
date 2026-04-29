package org.example.devops2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    // Ana sayfa mesajı
    @GetMapping("/")
    public ResponseEntity<String> home() {
        LOGGER.info("Home endpoint accessed");
        return new ResponseEntity<>("Welcome to  Driver Management System API! test", HttpStatus.OK);
    }

    // Projenin durumunu kontrol etmek için bir health check endpoint'i
    @GetMapping("/status")
    public ResponseEntity<String> status() {
        LOGGER.info("Status check endpoint accessed");
        return new ResponseEntity<>("System is up and running!", HttpStatus.OK);
    }

    // Basit bir proje bilgi endpoint'i
    @GetMapping("/info")
    public ResponseEntity<String> info() {
        LOGGER.info("Info endpoint accessed");
        return new ResponseEntity<>("This API manages Drivers, Vehicles and Maintenance.", HttpStatus.OK);
    }
}
