package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Table(name = "year_activity")
@Data
@Entity
@NoArgsConstructor
public class YearActivity{

    private static final String objKey = "arr";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String usersEmail;

    @Column(nullable = false, columnDefinition = "YEAR")
    private int year;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "month_masks", columnDefinition = "json")
    private List<Integer> monthMasks = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "days_minutes", nullable = false, columnDefinition = "json")
    private List<Integer> secondsArray;

    public void addSecondsToLast(int sec)
    {
        secondsArray.set(secondsArray.size() - 1, secondsArray.get(secondsArray.size() - 1) + sec);
    }

    public void pushBackLastSeconds(int sec)
    {
        secondsArray.add(sec);
        this.flipTheBitMaskBit();
    }

    public YearActivity(String usersEmail, int year, int seconds)
    {
        this.usersEmail = usersEmail;
        this.year = year;
        this.secondsArray= List.of(seconds);
        this.flipTheBitMaskBit();
    }

    private void flipTheBitMaskBit()
    {
        int dayOfMonthIndex = LocalDate.now().getDayOfMonth() - 1;
        int monthIndex = LocalDate.now().getMonthValue() - 1;

        monthMasks.set(monthIndex, monthMasks.get(monthIndex) ^ (1 << dayOfMonthIndex));
    }
}
