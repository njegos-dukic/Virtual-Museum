package org.unibl.etf.virtualmuseumapi.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "USER")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "email", nullable = false, length = 128)
    private String email;

    @Column(name = "isAdmin", nullable = false)
    private Integer isAdmin;

    @Column(name = "adminToken", length = 256)
    private String adminToken;

    @Column(name = "isApproved", nullable = false)
    private Boolean isApproved = false;

    @Column(name = "isBlocked", nullable = false)
    private Boolean isBlocked = false;

    @Column(name = "isPasswordReset", nullable = false)
    private Boolean isPasswordReset = false;
}