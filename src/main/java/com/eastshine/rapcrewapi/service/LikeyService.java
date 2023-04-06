package com.eastshine.rapcrewapi.service;


import com.eastshine.rapcrewapi.domain.Likey;
import com.eastshine.rapcrewapi.dto.LikeyDto;
import com.eastshine.rapcrewapi.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LikeyService {

    private final LikeyRepository likeyRepository;


    @Transactional
    public Long isLiked(LikeyDto likeyDto) {
        Likey likey;
        if (likeyDto.getLikeyId() != null) {
            likey = likeyRepository.findById(likeyDto.getLikeyId()).orElseThrow(()
            -> new IllegalArgumentException("좋아요 정보가 없습니다."));

        }

        return 1L;
    }


    @Transactional
    public Long isUnliked(LikeyDto likeyDto) {
        Likey likey;
        if (likeyDto.getLikeyId() != null) {
            likey = likeyRepository.findById(likeyDto.getLikeyId()).orElseThrow(()
                    -> new IllegalArgumentException("좋아요 정보가 없습니다."));

        }

        return 1L;
    }

}
