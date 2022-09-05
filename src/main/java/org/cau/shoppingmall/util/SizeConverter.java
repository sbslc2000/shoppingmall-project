package org.cau.shoppingmall.util;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.entity.item.Size;
import org.cau.shoppingmall.repository.item.SizeRepository;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SizeConverter {

    private final SizeRepository sizeRepository;

    public String sizeIdToName(Long id) {
        Optional<Size> byId = sizeRepository.findById(id);

        if(byId.isPresent()) {
            return byId.get().getName();
        } else {
            throw new NoSuchElementException("해당하는 사이즈가 없습니다.");
        }
    }
}
