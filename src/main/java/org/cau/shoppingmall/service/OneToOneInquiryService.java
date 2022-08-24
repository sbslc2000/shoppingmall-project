package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryForm;
import org.cau.shoppingmall.entity.inquiry.OneToOneInquiry;

public interface OneToOneInquiryService {

    OneToOneInquiry create(OneToOneInquiryForm form,Long userId);

}
