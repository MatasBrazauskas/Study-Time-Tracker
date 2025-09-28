package com.example.demo.Services;

import com.example.demo.Repositories.YearActivityRepo;
import org.springframework.stereotype.Service;

@Service
public class YearActivityService {

    private final YearActivityRepo yearRepo;

    public YearActivityService(YearActivityRepo yearRepo) {
        this.yearRepo = yearRepo;
    }
}
