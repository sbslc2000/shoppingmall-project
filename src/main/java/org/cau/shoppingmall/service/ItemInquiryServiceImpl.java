package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.inquiry.ItemInquiryDto;
import org.cau.shoppingmall.dto.inquiry.ItemInquiryForm;
import org.cau.shoppingmall.entity.inquiry.ItemInquiry;
import org.cau.shoppingmall.entity.item.Item;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.ItemInquiryRepository;
import org.cau.shoppingmall.repository.UserRepository;
import org.cau.shoppingmall.repository.item.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemInquiryServiceImpl implements ItemInquiryService {

    private final ItemInquiryRepository itemInquiryRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;
    @Override
    public List<ItemInquiryDto> getByUserId(Long userId) {
        return null;
    }

    @Override
    public List<ItemInquiryDto> getByItemId(Long itemId) throws NoSuchElementException{
        Item item = itemRepository.findById(itemId).orElseThrow(
                () -> new NoSuchElementException("해당 아이템이 존재하지 않습니다.")
        );
        List<ItemInquiry> byItem = itemInquiryRepository.findByItem(item);

        List<ItemInquiryDto> dtoList = byItem.stream().map(m -> ItemInquiryDto.of(m)).collect(Collectors.toList());

        dtoList.sort(Comparator.comparing(ItemInquiryDto::getId).reversed());

        return dtoList;

    }

    @Override
    public ItemInquiry create(ItemInquiryForm form, Long userId) throws IOException {

        User user = userRepository.findById(userId).get();

        Item item = itemRepository.findById(form.getItemId()).orElseThrow(
                () -> new NoSuchElementException("요청으로 들어온 값의 상품이 없습니다.")
        );

        ItemInquiry itemInquiry = form.toEntity(user,item);
        ItemInquiry savedItemInquiry = itemInquiryRepository.save(itemInquiry);

        return savedItemInquiry;
    }
}
