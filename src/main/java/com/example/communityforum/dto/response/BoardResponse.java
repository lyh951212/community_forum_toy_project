package com.example.communityforum.dto.response;

import com.example.communityforum.domain.constants.BoardType;
import com.example.communityforum.dto.BoardDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime registeredDate;
    private final String phone;
    private final String memberId;
    private final BoardType boardType;

    private BoardResponse(Long id, String title, String content, LocalDateTime registeredDate, String phone, String memberId, BoardType boardType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.registeredDate = registeredDate;
        this.phone = phone;
        this.memberId = memberId;
        this.boardType = boardType;
    }

    public static BoardResponse of(Long id, String title, String content, LocalDateTime registeredDate, String phone, String memberId, BoardType boardType){
        return new BoardResponse(id, title, content, registeredDate, phone, memberId, boardType);
    }

    public static BoardResponse from(BoardDto dto){
        return new BoardResponse(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getRegisteredDate(),
                dto.getMemberDto().getPhone(),
                dto.getMemberDto().getMemberId(),
                dto.getBoardType()
        );
    }
}
