package com.example.onlineclass.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jhlyh
 * @id 课程评价点赞id
 * @createTime 创建时间
 * @users 点赞人
 * @evaluate 点赞的评价
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class EvaluateLike implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 与用户为一对多关系
     *
     */
    @OneToMany(mappedBy = "evaluateLike")
    @JsonIgnoreProperties({"evaluates", "teacher", "User", "manageGrades","notes", "studyLogs", "noteLike", "evaluateLike"})
    private List<User> users;

    /**与评价为多对一关系
     *
     */
    @ManyToOne
    @JoinTable(
            name = "evaluate_evaluate_like",
            joinColumns = @JoinColumn(name = "evaluate_like"),
            inverseJoinColumns = @JoinColumn(name = "evaluate")
    )
    @JsonIgnoreProperties({"evaluateLikes", "user", "course"})
    private Evaluate evaluate;

}
