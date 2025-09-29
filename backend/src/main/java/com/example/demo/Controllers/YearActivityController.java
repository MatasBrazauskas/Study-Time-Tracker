package com.example.demo.Controllers;

import com.example.demo.Services.YearActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/activity")
public class YearActivityController {

    private final YearActivityService yearService;

    public YearActivityController(YearActivityService yearService) {
        this.yearService = yearService;
    }

    @GetMapping("/{year}")
    public ResponseEntity<?> getYearsActivity(@PathVariable int year){
        log.info("User year activity: {}", year);
        return ResponseEntity.ok().build();
    }
}
