package com.example.demo.Services;

import com.example.demo.Entities.YearActivity;
import com.example.demo.Repositories.UserProfileRepo;
import com.example.demo.Repositories.YearActivityRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class YearActivityService {

    private final YearActivityRepo yearRepo;

    public YearActivityService(YearActivityRepo yearRepo) {
        this.yearRepo = yearRepo;
    }

    public YearActivity getYearsActivity(int year, String email, int seconds)
    {
        var yearActivity = yearRepo.findByUsersEmail(email);

        if(yearActivity == null){
            var createdYearActivity = yearRepo.save(new YearActivity(email, year, seconds));
            return createdYearActivity;
        }

        if(dayCounted(yearActivity.getMonthMasks())){
            yearActivity.addSecondsToLast(seconds);
        }else{
            yearActivity.pushBackLastSeconds(seconds);
        }

        yearRepo.save(yearActivity);

        return yearActivity;
    }

    private static boolean dayCounted(List<Integer> monthsMask)
    {
        int dayOfMonthIndex = LocalDate.now().getDayOfMonth() - 1;
        int monthIndex = LocalDate.now().getMonthValue() - 1;

        return (monthsMask.get(monthIndex) & (1 << dayOfMonthIndex)) == (1 << dayOfMonthIndex);
    }
}
