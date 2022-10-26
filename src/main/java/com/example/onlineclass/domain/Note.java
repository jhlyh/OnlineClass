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
 * @id 笔记id
 * @content 内容
 * @createTime 创建时间
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Note implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private final Long id;
    private String content;
    private Date createTime;

    @ManyToOne(optional = false)
    @JoinTable(
            name = "chapter_note",
            joinColumns = @JoinColumn(name = "note"),
            inverseJoinColumns = @JoinColumn(name = "chapter")
    )
    @JsonIgnoreProperties("notes")
    private Chapter chapter;

    @ManyToOne(optional = false)
    @JoinTable(
            name = "user_notes",
            joinColumns = @JoinColumn(name = "note"),
            inverseJoinColumns = @JoinColumn(name = "user")
    )
    @JsonIgnoreProperties("notes")
    private User user;

    @OneToMany(mappedBy = "note", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("note")
    private List<NoteLike> noteLikes;
}
