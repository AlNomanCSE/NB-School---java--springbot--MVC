package com.noman.nbSchool.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Contact {
//    Name the variable same as the form input name given in MVC
    String name;
    String mobileNum;
    String email;
    String subject;
    String message;
}