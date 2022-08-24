
var colorList = ["화이트", "그레이","블루","블랙","오렌지","카키","레드","스카이블루","네이비","베이지","그린",
"퍼플","브라운","아이보리","차콜","옐로우","핑크","챠콜","라이트카키","민트"];

var sizeList = ["M","L","Free"];

function colorToId(colorName) {

    for (var i = 0; i < colorList.length; i++) {
        if (colorList[i] === colorName) {
            return i+1;
        }
    }
}

function sizeToId(sizeName) {
    for (var i = 0; i < sizeList.length; i++) {
        if (sizeList[i] === sizeName) {
            return i+1;
        }
    }
}
