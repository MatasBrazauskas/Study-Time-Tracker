package com.example.demo.Services;

import com.example.demo.Entities.YearActivity;
import com.example.demo.Repositories.UserProfileRepo;
import com.example.demo.Repositories.YearActivityRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class YearActivityService {

    private final YearActivityRepo yearRepo;
    private final UserProfileRepo userProfileRepo;
    private final YearActivityRepo yearActivityRepo;

    public YearActivityService(YearActivityRepo yearRepo, UserProfileRepo userProfileRepo, YearActivityRepo yearActivityRepo) {
        this.yearRepo = yearRepo;
        this.userProfileRepo = userProfileRepo;
        this.yearActivityRepo = yearActivityRepo;
    }

    public ResponseEntity<?> getYearsActivity(int year, String email, int seconds)
    {
        var yearActivity = yearRepo.findByUsersEmail(email);

        if(yearActivity == null){
            yearRepo.save(new YearActivity(email, year, seconds));
            return ResponseEntity.ok("Created a new year entry");
        }

        if(yearActivity.getDaysMinutes().stream().count() == countSetBits(yearActivity.getYearActivity())){
            yearActivity.addSecondsToLast(seconds);
        }else{
            yearActivity.pushBackLastSeconds(seconds);
            yearActivity.flipTheBitMaskBit();
        }

        yearActivityRepo.save(yearActivity);

        return ResponseEntity.ok(yearActivity);
    }

    public static int countSetBits(byte[] bitmask) {
        if (bitmask == null) {
            return 0;
        }

        int totalSetBits = 0;

        for (byte b : bitmask) {
            int unsignedByte = b & 0xFF;
            totalSetBits += Integer.bitCount(unsignedByte);
        }

        return totalSetBits;
    }
}
