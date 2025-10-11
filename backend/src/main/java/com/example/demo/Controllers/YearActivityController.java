package com.example.demo.Controllers;

import com.example.demo.DTOs.AuthUserProfile;
import com.example.demo.DTOs.YearActivityOutput;
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
    public ResponseEntity<YearActivityOutput> getYearsActivity(@PathVariable int year, @PathVariable int seconds , @AuthenticationPrincipal AuthUserProfile usersProfile){
        log.info("User year activity: {}, {}", year, seconds);
        var user = yearService.getYearsActivity(year, usersProfile.getEmail(), seconds);

        if(user == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new YearActivityOutput(user));
    }
}
