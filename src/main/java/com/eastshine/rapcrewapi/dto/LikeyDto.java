package com.eastshine.rapcrewapi.dto;

import com.eastshine.rapcrewapi.domain.Article;
import com.eastshine.rapcrewapi.domain.Likey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeyDto {

    private Long likeyId;
    private Integer type;
    private Long targetId;

    public LikeyDto(Likey likey) {
        likeyId = likey.getId();
        type = likey.getType();
        targetId = likey.getTargetId();
    }
}
