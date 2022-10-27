package com.example.onlineclass.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author jhlyh
 * @id 课程性质id
 * @name 性质名称
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private String name;

    @OneToMany(mappedBy = "type")
    @JsonIgnoreProperties("type")
    private List<Course> courses;
}
