package com.example.communityforum.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "registeredDate")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class BoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false) private Board board; // 게시글 (ID)
    @Setter @ManyToOne(optional = false) @JoinColumn(name = "memberId") private Member member; // 유저 정보
    @Setter
    @Column(nullable = false, length = 50)
    @Size(min = 2, max = 50, message = "댓글을 2~50자 사이로 입력해주세요.")
    private String content; // 본문
    @CreatedDate @Column(nullable = false) private LocalDateTime registeredDate; // 생성일시

    protected BoardComment() {}

    private BoardComment(Board board, Member member, String content) {
        this.board = board;
        this.member = member;
        this.content = content;
    }

    public static BoardComment of(Board board, Member member, String content){
        return new BoardComment(board, member, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardComment)) return false;
        BoardComment boardComment = (BoardComment) o;
        return id != null && id.equals(boardComment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
