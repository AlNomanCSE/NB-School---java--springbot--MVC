package com.noman.nbSchool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name="contact_message")
public class Contact extends BaseEntity {
    //    Name the variable same as the form input name given in MVC
  /*
    * @NotNull: Checks if a given field is not null but allows empty values & zero elements inside collections.
      @NotEmpty: Checks if a given field is not null and its size/length is greater than zero.
      @NotBlank: Checks if a given field is not null and trimmed length is greater than zero.
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 10 digits")
    @Column(name = "mobile_num")
    private String mobileNum;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Subject must not be blank")
    @Size(min = 5, message = "Subject must be at least 5 characters long")
    private String subject;

    @NotBlank(message = "Message must not be blank")
    @Size(min = 10, message = "Message must be at least 10 characters long")
    private String message;
}


