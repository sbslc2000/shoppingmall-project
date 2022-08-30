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

    if (document.getElementById('color').value == '색상')
        return;

    let selectedItemTag = document.createElement("div");
    selectedItemTag.setAttribute("id", selectedItemNum.toString());
    selectedItemTag.setAttribute("class", "itemDetails")



    let option = document.createElement("div");

    option.setAttribute("class", "option");

    let minButton = document.createElement("button"); // minButton
    minButton.setAttribute("class", "btn btn-outline-secondary minus");
    minButton.setAttribute("type", "button");
    minButton.setAttribute("onclick", "min(" + selectedItemNum.toString() + ")");
    minButton.innerHTML = '-'

    let countTag = document.createElement("span");
    countTag.setAttribute("class", "count");
    countTag.innerHTML = 1;

    let pluButton = document.createElement("button"); // minButton
    pluButton.setAttribute("class", "btn btn-outline-secondary plus");
    pluButton.setAttribute("type", "button");
    pluButton.setAttribute("onclick", "plu(" + selectedItemNum.toString() + ")");
    pluButton.innerHTML = '+'

    let sumPrice = document.createElement("span");
    sumPrice.setAttribute("class", "sumprice");
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

    var form = document.createElement('form');
    form.setAttribute("method", "post");
    form.setAttribute("action", "http://shoppingmall.ddnsking.com/order-form");
    document.characterSet = "utf-8";

    let selectedItemTag = document.getElementById("selectedItems");
    let itemDetailsList = selectedItemTag.getElementsByClassName("itemDetails");

    for (var i = 0; i < itemDetailsList.length; i++) {
        let splitedOption = itemDetailsList.item(i).getElementsByClassName("option")[0].innerHTML.split("/");
        var colorId = colorToId(splitedOption[0].trim());
        //console.log(colorId);
        var sizeId = sizeToId(splitedOption[1].trim());
        //console.log(sizeId);
        let quantity = itemDetailsList.item(i).getElementsByClassName("count")[0].innerHTML;

        var uri = window.location.pathname;
        var itemId = uri.split("/")[2];
        console.log(itemId);

        var colorField = document.createElement("input");
        colorField.setAttribute("type", "hidden");
        colorField.setAttribute("name", "orderItemList[" + i + "].colorId")
        colorField.setAttribute("value", colorId);

        var sizeField = document.createElement("input");
        sizeField.setAttribute("type", "hidden");
        sizeField.setAttribute("name", "orderItemList[" + i + "].sizeId")
        sizeField.setAttribute("value", sizeId);

        var itemField = document.createElement("input");
        itemField.setAttribute("type", "hidden");
        itemField.setAttribute("name", "orderItemList[" + i + "].itemId")
        itemField.setAttribute("value", itemId);

        var quantityField = document.createElement("input");
        quantityField.setAttribute("type", "hidden");
        quantityField.setAttribute("name", "orderItemList[" + i + "].quantity")
        quantityField.setAttribute("value", quantity);

        form.appendChild(colorField);
        form.appendChild(sizeField);
        form.appendChild(itemField);
        form.appendChild(quantityField);

    }


    document.body.appendChild(form);
    form.submit();


}

function getbucket() {

    document.getElementById('bucketalert').style.display = 'block';
}

function likebtn() {

    if (document.getElementById('likebutton').style.backgroundColor == 'pink') {
        document.getElementById('likebutton').style.backgroundColor = 'white';
        document.getElementById('likebutton').style.color = 'pink';
    } else {
        document.getElementById('likebutton').style.backgroundColor = 'pink';
        document.getElementById('likebutton').style.color = 'white';
    }
}