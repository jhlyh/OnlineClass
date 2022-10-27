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
 * @id 章节id
 * @name 班级名称
 * @headteacher 班主任（对应user）
 * @atmosphere 班训
 * @coverUrl 封面
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private String name;
    private String atmosphere;
    private String coverUrl;

    @OneToMany(mappedBy = "aGrade", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("aGrade")
    private List<UserGrade> userGrades;

    @ManyToOne
    @JoinTable(
            name = "headTeacher_grade",
            joinColumns = @JoinColumn(name = "grade"),
            inverseJoinColumns = @JoinColumn(name = "headteacher")
    )
    private User headteacher;
}
