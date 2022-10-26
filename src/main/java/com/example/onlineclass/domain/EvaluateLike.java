package com.example.onlineclass.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jhlyh
 * @id 课程评价点赞id
 * @createTime 创建时间
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class EvaluateLike implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private final Long id;
    private Date createTime;

    @ManyToOne(optional = false)
    @JoinTable(
            name = "evaluate_evaluate_like",
            joinColumns = @JoinColumn(name = "evaluate_like"),
            inverseJoinColumns = @JoinColumn(name = "evaluate")
    )
    @JsonIgnoreProperties("evaluateLikes")
    private Evaluate evaluate;

    @OneToMany(mappedBy = "evaluateLike")
    @JsonIgnoreProperties("evaluateLike")
    private List<User> users;
}
