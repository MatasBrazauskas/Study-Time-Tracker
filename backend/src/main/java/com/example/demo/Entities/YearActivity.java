package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.SqlTypes;
import org.hibernate.type.descriptor.jdbc.JsonJdbcType;

import java.util.List;

@Entity
@Table(name = "year_activity")
@Data
public class YearActivity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UsersProfile user;

    @Column(nullable = false, columnDefinition = "YEAR")
    private int year;

    @Column(name = "year_activity", nullable = false, columnDefinition = "VARBINARY(46)")
    private byte[] yearActivity;

    @JdbcType(JsonJdbcType.class)
    @Column(name = "days_minutes", nullable = false, columnDefinition = "json")
    private List<DayMinutes> daysMinutes;

    @Data
    public static class DayMinutes {
        private int day;
        private int minutes;

        public DayMinutes() {}
        public DayMinutes(int day, int minutes) {
            this.day = day;
            this.minutes = minutes;
        }
    }
}
