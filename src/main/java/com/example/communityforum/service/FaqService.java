package com.example.communityforum.service;

import com.example.communityforum.domain.Faq;
import com.example.communityforum.domain.Member;
import com.example.communityforum.domain.constants.FaqType;
import com.example.communityforum.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    public Page<Faq> findAll(Pageable pageable){
        return faqRepository.findAll(pageable);
    }

    public Page<Faq> findAllByType(Pageable pageable, FaqType type){
        return faqRepository.findAllByType(type, pageable);
    }

    public void create(Member member, String title, String content, FaqType type, LocalDateTime registeredDate) {
        Faq faq = Faq.builder()
                .member(member)
                .type(type)
                .title(title)
                .content(content)
                .registeredDate(registeredDate)
                .build();

        faqRepository.save(faq);
    }
}
