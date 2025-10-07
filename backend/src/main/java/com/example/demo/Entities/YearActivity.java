package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Column(name = "year_activity", nullable = false, columnDefinition = "VARBINARY(53)")
    private byte[] yearActivity;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "days_minutes", nullable = false, columnDefinition = "json")
    private List<Integer> daysMinutes;

    public void addSecondsToLast(int sec)
    {
        daysMinutes.set(daysMinutes.size() - 1, daysMinutes.get(daysMinutes.size() - 1) + sec);
    }

    public void pushBackLastSeconds(int sec)
    {
        daysMinutes.add(sec);
    }

    public YearActivity(String usersEmail, int year, int seconds)
    {
        this.usersEmail = usersEmail;
        this.year = year;
        this.daysMinutes = List.of(seconds);
        this.yearActivity = new byte[53];
        this.flipTheBitMaskBit();
    }

    public void flipTheBitMaskBit()
    {
        var i = getWeekIndex();
        var j = getDayOfWeekIndex();

        yearActivity[i] = (byte) (yearActivity[i] ^ (1 << j));
    }

    public static int getWeekIndex() {
        LocalDate today = LocalDate.now();

        WeekFields isoWeekFields = WeekFields.ISO;

        int weekIndex = today.get(isoWeekFields.weekOfYear());

        return weekIndex;
    }

    public static int getDayOfWeekIndex() {
        LocalDate today = LocalDate.now();

        DayOfWeek dayOfWeek = today.getDayOfWeek();

        int index = dayOfWeek.getValue();

        return index - 1;
    }
}
