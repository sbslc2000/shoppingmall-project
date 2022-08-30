package org.cau.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryDto;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryForm;
import org.cau.shoppingmall.entity.inquiry.InquiryType;
import org.cau.shoppingmall.entity.inquiry.OneToOneInquiry;
import org.cau.shoppingmall.entity.user.User;
import org.cau.shoppingmall.repository.InquiryTypeRepository;
import org.cau.shoppingmall.repository.OneToOneInquiryRepository;
import org.cau.shoppingmall.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OneToOneInquiryServiceImpl implements OneToOneInquiryService{

    private final UserRepository userRepository;
    private final InquiryTypeRepository inquiryTypeRepository;
    private final ImageService imageService;
    private final OneToOneInquiryRepository oneToOneInquiryRepository;
    @Override
    public OneToOneInquiry create(OneToOneInquiryForm form, Long userId, List<MultipartFile> multipartFileList) throws IOException {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("사용자를 찾을 수 없습니다.")
        );

        InquiryType inquiryType = inquiryTypeRepository.findById(form.getType()).orElseThrow(
                () -> new NoSuchElementException("문의 유형을 찾을 수 없습니다.")
        );

        List<String> imgs = imageService.storeOneToOneInquiryImages(multipartFileList);


        OneToOneInquiry oneToOneInquiry = form.toEntity(user, inquiryType, imgs.toString());

        OneToOneInquiry savedOneToOneInquiry = oneToOneInquiryRepository.save(oneToOneInquiry);

        return savedOneToOneInquiry;

    }

    public List<OneToOneInquiryDto> getByUserId(Long userId) {
        List<OneToOneInquiry> inquiryList = oneToOneInquiryRepository.findByUserId(userId);

        List<OneToOneInquiryDto> dtoList = inquiryList.stream().map((m) -> OneToOneInquiryDto.of(m)).collect(Collectors.toList());

        dtoList.sort(Comparator.comparing(OneToOneInquiryDto::getId).reversed());

        return dtoList;

    }
}
