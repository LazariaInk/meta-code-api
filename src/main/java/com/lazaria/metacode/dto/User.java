package com.lazaria.metacode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name ="userName")
    private String userName;

    @Column(name ="userLogin")
    private String userLogin;

    @Column(name ="userPassword")
    private String userPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    Collection<Role> roles = new ArrayList<>();
}
