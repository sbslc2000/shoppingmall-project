package org.cau.shoppingmall.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${file.dir}")
    private String fileDir;

    private String getFullPath(String directory, String fileName) {
        return fileDir + directory + "/" + fileName;
    }


    /*
    * static/img/OneToOneInquiry에 이미지를 저장하고, 이미지 파일명을 리스트로 반환한다.
    * */
    public List<String> storeOneToOneInquiryImages(List<MultipartFile> multipartFiles) throws IOException {

        List<String> storedFileNameList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                String originalFileName = multipartFile.getOriginalFilename();
                String storeFileName = createStoreFileName(originalFileName);

                storedFileNameList.add(storeFileName);
                multipartFile.transferTo(new File(getFullPath("review",storeFileName)));
            }
        }

        return storedFileNameList;
    }

    private String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString()+ext;
        return uuid;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos);
    }
}