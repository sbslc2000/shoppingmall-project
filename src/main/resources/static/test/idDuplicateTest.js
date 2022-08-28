ourPageUrl = "http://shoppingmall.ddnsking.com";



/*
var item = fetch(ourPageUrl + "/api/users/isDuplicate?username=")
    .then(response => response.json()) // response.json()은 응답 데이터를 JSON 으로 변환하는 작업을 함
    .then( item => {

        console.log(item); // 해당 api의 정보를 확인


        var imgElemList = document.getElementsByTagName("img");  //html의 img 태그를 전부 가져온다.


        //각각의 img 태그에 대해서 속성 src 를 이미지 파일의 경로로 바꿔준다.
        for(var i = 0; i < imgElemList.length ; i++) {
            imgElemList[i].src = ourPageUrl+"/img/item/"+item.img[i]+".jpg";
        }

        // 상품명 태그를 가져와 상품명을 바꿔준다.
        var itemName = document.getElementById("itemname");
        itemName.innerHTML = item.name;

    })*/


function isDuplicate() {
    let idInput = document.getElementById("idinput");

    let inputUserName = idInput.value;

    fetch(ourPageUrl+"/api/users/isDuplicate?username="+inputUserName)
        .then(response => response.text())
        .then( item => {
            console.log(item);
            if(item == "false") {
                document.getElementById("warnid").innerHTML= "사용 가능한 아이디 입니다.";
                버튼.();
            } else {
                document.getElementById("warnid").innerHTML= "중복된 아이디 입니다.";
                회원가입제출버튼.disable();
            }



        } )

}