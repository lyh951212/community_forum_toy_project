package com.example.communityforum.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
public class Member {

    @Id
    @Column(length = 10)
    private String memberId;

    @Column(length = 15, nullable = false)
    private String memberPassword;

    @Column(length = 10, nullable = false)
    private String name;
    @Column(length = 11, nullable = false)
    private String phone;

    @Column(columnDefinition = "ENUM('ADMIN', 'GENERAL')")
    @Enumerated(EnumType.STRING)
    private MemberRole role = MemberRole.GENERAL;

    @Column(name = "term", nullable = false)
    private Boolean agreedTerms;

    protected Member() {}

    private Member(String memberId, String memberPassword, String name, String phone, Boolean agreedTerms) {
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.name = name;
        this.phone = phone;
        this.agreedTerms = agreedTerms;
    }

    public static Member of(String memberId, String memberPassword, String name, String phone, Boolean agreedTerms) {
        return new Member(memberId, memberPassword, name, phone, agreedTerms);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return memberId.equals(member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}