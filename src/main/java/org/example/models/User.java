package org.example.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "user")
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iduser")
    private int iduser;
    @NotEmpty(message = "name not be empty")
    @Size(min = 3, max = 10, message = "name must be between 2 and 10 characters")
    @Column(name = "username")
    private String username;
    @NotEmpty(message = "surname not be empty")
    @Column(name = "surname")
    private  String surname;
    @Column(name = "year")
    private int year;
    @Column(name = "month")
    private int month;
    @Column(name = "day")
    private int day;
    @NotEmpty(message = "email not be empty")
    @Email(message = "email not valid")
    @Column(name= "email")
    private String email;
    @Column(name =  "address")
    private String address;
    @Column(name = "phone")
    private int phone;


}
