package com.example.onlineclass.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author jhlyh
 * @id 大学班id
 *@isHeadTeacher 是否班主任，0不是，1是
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class UserClass implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private final Long id;
    private Integer isHeadTeacher;

    @ManyToOne(optional = false)
    @JoinTable(
            name = "user_to_c",
            joinColumns = @JoinColumn(name = "user_class"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    @JsonIgnoreProperties("classes")
    private User user;

    @ManyToOne(optional = false)
    @JoinTable(
            name = "class_to_u",
            joinColumns = @JoinColumn(name = "user_class"),
            inverseJoinColumns = @JoinColumn(name = "class")
    )
    @JsonIgnoreProperties("userClasses")
    private Class aClass;
}
