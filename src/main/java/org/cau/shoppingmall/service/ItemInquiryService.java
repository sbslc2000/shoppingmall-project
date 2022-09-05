package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.inquiry.ItemInquiryDto;
import org.cau.shoppingmall.dto.inquiry.ItemInquiryForm;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryDto;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryForm;
import org.cau.shoppingmall.entity.inquiry.ItemInquiry;
import org.cau.shoppingmall.entity.inquiry.OneToOneInquiry;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemInquiryService {

    List<ItemInquiryDto> getByUserId(Long userId);

    List<ItemInquiryDto> getByItemId(Long itemId);

    ItemInquiry create(ItemInquiryForm form, Long userId, List<MultipartFile> multipartFileList) throws IOException;
}
