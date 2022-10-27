package com.example.onlineclass.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/** 多对多关系表
 * @author jhlyh
 * @id 大学班id
 * @isHeadTeacher 是否班主任，0不是，1是
 * @user 用户
 * @aGrade 班级
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class UserGrade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private Integer isHeadTeacher;

    /** 大学班与用户为多对一关系
     *
     */
    @ManyToOne
    @JoinTable(
            name = "user_to_g",
            joinColumns = @JoinColumn(name = "user_grade"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    @JsonIgnoreProperties({"evaluates", "teacher", "User", "manageGrades","notes", "studyLogs", "noteLike", "evaluateLike"})
    private User user;

    /** 大学班与班级为多对一关系
     *
     */
    @ManyToOne
    @JoinTable(
            name = "grade_to_u",
            joinColumns = @JoinColumn(name = "user_grade"),
            inverseJoinColumns = @JoinColumn(name = "grade")
    )
    @JsonIgnoreProperties({"userGrades", "headteacher"})
    private Grade aGrade;
}
