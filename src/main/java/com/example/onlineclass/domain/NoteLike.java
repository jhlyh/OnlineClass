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
 * @id 笔记点赞id
 * @createTime 创建时间
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class NoteLike implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private final Long id;
    private Date createTime;

    @ManyToOne(optional = false)
    @JoinTable(
            name = "note_note_like",
            joinColumns = @JoinColumn(name = "note_like"),
            inverseJoinColumns = @JoinColumn(name = "note")
    )
    @JsonIgnoreProperties("noteLikes")
    private Note note;

    @OneToMany(mappedBy = "noteLike")
    @JsonIgnoreProperties("noteLike")
    private List<User> users;
}
