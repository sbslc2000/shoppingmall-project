var selectedItemNum = 0;
var price = parseInt(document.getElementById("price").innerHTML);
function min(idNum) {
    let elem = document.getElementById(idNum);
    var countnum = elem.getElementsByClassName('count')[0].innerHTML;
    if (countnum > 1) {
        countnum = (countnum - 1);
        elem.getElementsByClassName('count')[0].innerHTML = countnum;
        elem.getElementsByClassName('sumprice')[0].innerHTML = (countnum * price);
    } else {
        elem.remove();
        selectedItemNum--;
    }
}

function plu(idNum) {
    let elem = document.getElementById(idNum);
    var countnum = elem.getElementsByClassName('count')[0].innerHTML;
    countnum = parseInt(countnum) + parseInt(1);
    elem.getElementsByClassName('count')[0].innerHTML = countnum;
    elem.getElementsByClassName('sumprice')[0].innerHTML = (countnum * price);
}

function addSelect() {

    if(document.getElementById('color').value == '색상')
        return;
    
    let selectedItemTag = document.createElement("div");
    selectedItemTag.setAttribute("id",selectedItemNum.toString());
    selectedItemTag.setAttribute("class" , "itemDetails")



    let option = document.createElement("div");

    option.setAttribute("class","option");

    let minButton = document.createElement("button"); // minButton
    minButton.setAttribute("class","btn btn-outline-secondary minus");
    minButton.setAttribute("type","button");
    minButton.setAttribute("onclick","min("+selectedItemNum.toString()+")");
    minButton.innerHTML = '-'

    let countTag = document.createElement("span");
    countTag.setAttribute("class","count");
    countTag.innerHTML = 1;

    let pluButton = document.createElement("button"); // minButton
    pluButton.setAttribute("class","btn btn-outline-secondary plus");
    pluButton.setAttribute("type","button");
    pluButton.setAttribute("onclick","plu("+selectedItemNum.toString()+")");
    pluButton.innerHTML = '+'

    let sumPrice = document.createElement("span");
    sumPrice.setAttribute("class","sumprice");
    sumPrice.innerHTML = price;

    let won = document.createElement("span");
    won.innerHTML = "원";

    selectedItemTag.appendChild(option);
    selectedItemTag.appendChild(minButton);
    selectedItemTag.appendChild(countTag);
    selectedItemTag.appendChild(pluButton);
    selectedItemTag.appendChild(sumPrice);
    selectedItemTag.appendChild(won);

    let selectedItems = document.getElementById("selectedItems");
    selectedItems.appendChild(selectedItemTag);

    selectedItemTag.getElementsByClassName("option")[0].innerHTML = document.getElementById('color').value + ' / ' + document.getElementById('size').value;
    //selectedItemTag.getElementById('option').innerHTML = document.getElementById('color').value + ' / ' + document.getElementById('size').value;
    console.log(price);
    console.log(selectedItemTag.getElementsByClassName("sumprice")[0].innerHTML);
    //selectedItemTag.getElementsByTagName("sumprice")[0].innerHTML = price.toString();
    //document.getElementById('sumprice').innerHTML = (parseInt(document.getElementById('count').innerHTML) * parseInt(document.getElementById('price').innerHTML)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

    document.getElementById('color').value = '색상';
    document.getElementById('size').value = '사이즈';


    selectedItemNum++;

}


function goBuyPage() {

    var url = "http://shoppingmall.ddnsking.com/order?"

    let selectedItems = document.getElementById("selectedItems");
    let itemDetailsList = selectedItems.getElementsByClassName("itemDetails");

    var i = 0;
    for(var selectedItemTag in itemDetailsList) {
        url += "orderItem["+i+"].itemId="+"#itemId(uri에서가져온다)"+"&";
        url += "orderItem["+i+"].colorId="+selectedItemTag.getElementsByTag+"&";
        url += "orderItem["+i+"].quantity="+selectedItemTag.getElementsByTagName("count").innerHTML+"&";
        url += "orderItem["+i+"].sizeId="+"#sizeId(option에서 parsing해서 가져온다)  "+"&";
    }

    location.href = url;



}