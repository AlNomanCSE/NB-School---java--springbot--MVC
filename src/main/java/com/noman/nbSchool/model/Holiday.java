package com.noman.nbSchool.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "holidays")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String day;

    private  String reason;

    @Enumerated(EnumType.STRING)
    private  Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }

}
