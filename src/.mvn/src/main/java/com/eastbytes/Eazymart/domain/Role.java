package com.innovex.Innovex.domain;

import javax.persistence.*;
import lombok.Data;

/**
 * Created by nydiarra on 06/05/17.
 */
@Entity
@Data
public class Role {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    private String roleName;

    @Column(name="description")
    private String description;
}
