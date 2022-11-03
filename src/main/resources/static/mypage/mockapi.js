/** 
** Warning : 오직 테스트용으로만 사용해주세요
*/

const PROTOCOL = "http:";
const BLANK = "";
const DOMAIN_NAME = "shoppingmall.ddnsking.com";

class ApiResponse {
    constructor(code,explanation,result) {
        var responseMsgGiver = new ApiResponseMsgGiver();

        this.code = code;
        var responseMsg = responseMsgGiver.getStatusMessage(code);
        this.explanation = responseMsg + explanation;
        this.result = result;
    }
}

/*
code 200 : success
code 204 : no content
code 400 : Bad Request
code 404 : Not Found
*/
class ApiResponseMsgGiver {

    getStatusMessage(code) {
        switch(code) {
            case 200:
                return "Success : ";
            case 204:
                return "No Content : ";
            case 400:
                return "Bad Request : ";
            case 404: 
                return "Not Found : ";
        }
    
    }

}



//like "http://shoppingmall.ddnsking.com/api/item/1"
function request(requestUrl) {

    var response;

    const parsedUrl = requestUrl.split("/");
    if(parsedUrl[0] != PROTOCOL ||parsedUrl[1] != BLANK|| parsedUrl[2] != DOMAIN_NAME) {
        response =  new ApiResponse(404,"url을 제대로 입력하십쇼",null);
        return response;
    } 

    if(parsedUrl[3] == "api") {
        if(parsedUrl[4] == "items") {
                if(!isNaN(parsedUrl[5])) {
                    console.log("parsedUrl[5] : ",parsedUrl[5]);
                    var itemRepository = new ItemRepository();
                    var result = itemRepository.get(parsedUrl[5]);

                    if(result == undefined) {
                        response = new ApiResponse(204,"해당 id의 아이템이 존재하지 않습니다.",null);
                        return response;
                    }
                    response = new ApiResponse(200,"아이템 정보가 제공되었습니다.",result);
                } else {
                    response = new ApiResponse(404, "/api/item/ 이후에는 숫자가 와야합니다.",null);
                }
        } else {
            response = new ApiResponse(404,"제공되는 URI가 아닙니다.",null);
        }
    } else {
        response = new ApiResponse(404,"제공되는 URI가 아닙니다.",null);
    }


    return response;
}


