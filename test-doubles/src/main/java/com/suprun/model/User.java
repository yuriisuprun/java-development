package com.suprun.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Yurii_Suprun
 */
@Data
@AllArgsConstructor
@Entity
@Table(name = "app_user")
public class User {

    @Id
    private Long id;
    private String name;
    private String email;
}

