package com.example.demo.DTOs;

import com.example.demo.Entities.YearActivity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YearActivityOutput
{
    private int year;
    private List<Integer> secondsArray;
    private List<Integer> monthMasks;

    public YearActivityOutput(YearActivity yearActivity)
    {
        this.year = yearActivity.getYear();
        this.secondsArray = yearActivity.getSecondsArray();
        this.monthMasks = yearActivity.getMonthMasks();
    }
}
