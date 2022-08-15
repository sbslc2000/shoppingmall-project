package org.cau.shoppingmall.util;

import lombok.Data;

@Data
public class ImgMapper {

    public ImgMapper(String imgUUID, String color) {
        this.imgUUID = imgUUID;
        this.color = color;
    }

    private String color;

    private String imgUUID;


}
