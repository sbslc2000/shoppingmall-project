package org.cau.shoppingmall.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cau.shoppingmall.entity.item.Color;
import org.cau.shoppingmall.repository.item.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ColorConverter {

    private final ColorRepository colorRepository;


    public String colorIdToName(Long id) {
        Optional<Color> byId = colorRepository.findById(id);

        if(byId.isPresent()) {
            return byId.get().getName();
        } else {
            throw new NoSuchElementException("해당하는 색상이 없습니다.");
        }
    }
}
