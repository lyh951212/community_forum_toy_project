package com.example.communityforum.dto.response;

import com.example.communityforum.dto.BoardWithCommentsDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class BoardWithCommentResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime registeredDate;
    private final String phone;
    private final String userId;
    private final Set<BoardCommentResponse> boardCommentResponses;

    private BoardWithCommentResponse(Long id,
                                    String title,
                                    String content,
                                    LocalDateTime registeredDate,
                                    String phone,
                                    String userId,
                                    Set<BoardCommentResponse> boardCommentResponses
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.registeredDate = registeredDate;
        this.phone = phone;
        this.userId = userId;
        this.boardCommentResponses = boardCommentResponses;
    }

    public static BoardWithCommentResponse of(Long id,
                                              String title,
                                              String content,
                                              LocalDateTime registeredDate,
                                              String phone,
                                              String userId,
                                              Set<BoardCommentResponse> boardCommentResponses
    ) {
        return new BoardWithCommentResponse(id, title, content, registeredDate, phone, userId, boardCommentResponses);
    }

    public static BoardWithCommentResponse from(BoardWithCommentsDto dto) {
        return new BoardWithCommentResponse(
                dto.getId(),
                dto.getTitle(),
                dto.getContent(),
                dto.getRegisteredDate(),
                dto.getUserAccountDto().getPhone(),
                dto.getUserAccountDto().getUserId(),
                dto.getBoardCommentDtos().stream()
                        .map(BoardCommentResponse::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }
}
