package com.example.communityforum.service;

import com.example.communityforum.domain.member.Member;
import com.example.communityforum.domain.member.MemberRole;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

}