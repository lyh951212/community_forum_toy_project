package com.example.communityforum.repository;

import com.example.communityforum.domain.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<NoticeBoard, Long>{

}
