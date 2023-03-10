package com.example.communityforum.service;

import com.example.communityforum.domain.Board;
import com.example.communityforum.domain.Member;
import com.example.communityforum.domain.constants.BoardType;
import com.example.communityforum.domain.constants.SearchType;
import com.example.communityforum.dto.BoardDto;
import com.example.communityforum.dto.BoardWithCommentsDto;
import com.example.communityforum.repository.BoardRepository;
import com.example.communityforum.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class BoardService {
    private final MemberRepository memberRepository;

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Page<BoardDto> searchBoards(BoardType boardType, SearchType searchType, String searchKeyword, Pageable pageable){
        Page<BoardDto> dtos = null;
        if (searchKeyword == null || searchKeyword.isBlank()) {
            dtos = boardRepository.findByBoardType(boardType, pageable).map(BoardDto::from);
            return dtos;
        }
        switch(searchType){
            case TITLE:
                dtos = boardRepository.findByBoardTypeAndTitleContaining(boardType, searchKeyword,pageable).map(BoardDto::from);
                break;
            case CONTENT:
                dtos = boardRepository.findByBoardTypeAndContentContaining(boardType, searchKeyword,pageable).map(BoardDto::from);
                break;
            case ID:
                dtos = boardRepository.findByBoardTypeAndMember_MemberIdContaining(boardType, searchKeyword,pageable).map(BoardDto::from);
                break;
         }

            return dtos;
        }


        
        
    
    @Transactional(readOnly = true)
    public BoardWithCommentsDto getBoardWithComments(Long boardId){
        return boardRepository.findById(boardId)
                .map(BoardWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("???????????? ????????????. - boardId: " + boardId));
    }

    @Transactional(readOnly = true)
    public BoardDto getBoard(Long boardId){
        return boardRepository.findById(boardId)
                .map(BoardDto::from)
                .orElseThrow(()-> new EntityNotFoundException("???????????? ???????????? - boardId: " + boardId));
    }


    public void saveBoard(BoardDto dto) {
        Member member = memberRepository.getReferenceById(dto.getMemberDto().getMemberId());
        if (dto.getBoardType().equals(BoardType.FORUM)) {
            boardRepository.save(dto.toForumEntity(member));
        }
    }

    public void updateBoard(Long boardId, BoardDto dto){
        try {
            Board board = boardRepository.getReferenceById(boardId);

            Member member = memberRepository.getReferenceById(dto.getMemberDto().getMemberId());

            if (board.getMember().equals(member)){
                if (dto.getTitle() != null) { board.setTitle(dto.getTitle());}
                if (dto.getContent() != null) { board.setContent(dto.getContent());}
            }
        } catch (EntityNotFoundException e) {
            log.warn("????????? ???????????? ??????. ????????? ????????? ????????? ?????? ??? ???????????? - {}", e.getLocalizedMessage());
        }

    }

    public void deleteBoard(long boardId, String memberId){
        boardRepository.deleteByIdAndMember_MemberId(boardId, memberId);
    }

    public long getBoardCount() {
        return boardRepository.count();
    }

}
