package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.inquiry.ItemInquiryDto;
import org.cau.shoppingmall.dto.inquiry.ItemInquiryForm;
import org.cau.shoppingmall.entity.inquiry.ItemInquiry;

import java.io.IOException;
import java.util.List;

public interface ItemInquiryService {

    List<ItemInquiryDto> getByUserId(Long userId);

    List<ItemInquiryDto> getByItemId(Long itemId);

    ItemInquiry create(ItemInquiryForm form, Long userId) throws IOException;
}
