package com.example.demo.Controllers;

import com.example.demo.Services.YearActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(name = "yearActivity")
public class YearActivityController {

    private final YearActivityService yearService;

    public YearActivityController(YearActivityService yearService) {
        this.yearService = yearService;
    }

    @GetMapping
    public ResponseEntity<?> getYearsActivity(@RequestBody int year){
        log.info("getYearsActivity");
        return ResponseEntity.ok().build();
    }
}
