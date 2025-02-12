package com.noman.nbSchool.model;

import com.noman.nbSchool.annotation.PasswordValidator;
import com.noman.nbSchool.validationGroups.OnProfileUpdate;
import com.noman.nbSchool.validationGroups.OnRegistration;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Person extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int personId;

    @NotBlank(message = "Name must not be blank", groups = {OnRegistration.class, OnProfileUpdate.class})
    @Size(min = 3, message = "Name must be at least 3 characters long", groups = {OnRegistration.class, OnProfileUpdate.class})
    private String name;

    @NotBlank(message = "Mobile number must not be blank", groups = {OnRegistration.class, OnProfileUpdate.class})
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Mobile number must be 11 digits", groups = {OnRegistration.class, OnProfileUpdate.class})
    private String mobileNumber;

    @NotBlank(message = "Email must not be blank", groups = {OnRegistration.class, OnProfileUpdate.class})
    @Email(message = "Please provide a valid email address", groups = {OnRegistration.class, OnProfileUpdate.class})
    private String email;

    @NotBlank(message = "Confirm Email must not be blank", groups = {OnRegistration.class})
    @Email(message = "Please provide a valid confirm email address", groups = {OnRegistration.class})
    @Transient
    private String confirmEmail;

    @NotBlank(message = "Password must not be blank", groups = {OnRegistration.class})
    @Size(min = 5, message = "Password must be at least 5 characters long", groups = {OnRegistration.class})
    @PasswordValidator(groups = {OnRegistration.class})
    private String pwd;

    @NotBlank(message = "Confirm Password must not be blank", groups = {OnRegistration.class})
    @Size(min = 5, message = "Confirm Password must be at least 5 characters long", groups = {OnRegistration.class})
    @Transient
    private String confirmPwd;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, targetEntity = Roles.class)
    @JoinColumn(name = "role_id", referencedColumnName = "roleId", nullable = false)
    private Roles roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = true)
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "class_id", referencedColumnName = "classId", nullable = true)
    private NbClass nbClass;
}