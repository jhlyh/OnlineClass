package com.example.onlineclass.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Class implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private final Long id;
    private String name;
    private String atmosphere;
    private String coverUrl;

    @OneToMany(mappedBy = "aClass", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("aClass")
    private List<UserClass> userClasses;
}
