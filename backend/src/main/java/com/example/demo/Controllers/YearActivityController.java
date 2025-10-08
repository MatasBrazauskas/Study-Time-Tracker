package com.example.demo.Controllers;

import com.example.demo.DTOs.AuthUserProfile;
import com.example.demo.Services.YearActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/activity")
public class YearActivityController {

    private final YearActivityService yearService;

    public YearActivityController(YearActivityService yearService) {
        this.yearService = yearService;
    }

    @GetMapping("/{year}/{seconds}")
    public ResponseEntity<?> getYearsActivity(@PathVariable int year, @PathVariable int seconds , @AuthenticationPrincipal AuthUserProfile usersProfile){
        log.info("User year activity: {}, {}", year, seconds);
        return yearService.getYearsActivity(year, usersProfile.getEmail(), seconds);
    }
}
