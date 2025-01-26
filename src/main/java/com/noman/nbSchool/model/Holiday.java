package com.noman.nbSchool.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Holiday {
    private final String day;
    private final String reason;

    public enum Type {
        FESTIVAL, FEDERAL
    }

    private final Type type;
}
