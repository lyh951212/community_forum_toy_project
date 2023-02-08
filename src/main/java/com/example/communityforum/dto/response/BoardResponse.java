package com.example.communityforum.dto.response;

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
    private final String userId;

    private BoardResponse(Long id, String title, String content, LocalDateTime registeredDate, String phone, String userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.registeredDate = registeredDate;
        this.phone = phone;
        this.userId = userId;
    }

    public static BoardResponse of(Long id, String title, String content, LocalDateTime registeredDate, String phone, String userId){
        return new BoardResponse(id, title, content, registeredDate, phone, userId);
    }

    public static BoardResponse from(BoardDto dto){
        return new BoardResponse(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getRegisteredDate(),
                dto.getUserAccountDto().getPhone(),
                dto.getUserAccountDto().getUserId()
        );
    }
}
