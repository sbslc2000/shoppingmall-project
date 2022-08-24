var selectedItemNum = 0;
var price = document.getElementById("price");
function addSelect() {

    let selectedItemTag = document.createElement("div");
    selectedItemTag.setAttribute("id",selectedItemNum.toString());

    selectedItemNum++;

    let option = document.createElement("div");

    option.setAttribute("class","option");

    let minButton = document.createElement("button"); // minButton
    minButton.setAttribute("class","btn btn-outline-secondary minus");
    minButton.setAttribute("type","button");
    minButton.setAttribute("onclick","min()");
    minButton.innerHTML = '-'

    let countTag = document.createElement("span");
    countTag.setAttribute("class","count");
    countTag.innerHTML = 1;

    let pluButton = document.createElement("button"); // minButton
    pluButton.setAttribute("class","btn btn-outline-secondary plus");
    pluButton.setAttribute("type","button");
    pluButton.setAttribute("onclick","plu()");
    pluButton.innerHTML = '+'

    let sumPrice = document.createElement("span");
    sumPrice.setAttribute("class","sumprice");

    let won = document.createElement("span");
    won.innerHTML = "원";

    selectedItemTag.appendChild(minButton);
    selectedItemTag.appendChild(countTag);
    selectedItemTag.appendChild(pluButton);
    selectedItemTag.appendChild(sumPrice);
    selectedItemTag.appendChild(won);

    let selectedItems = document.getElementById("selectedItems");
    selectedItems.appendChild(selectedItemTag);

    selectedItemTag.getElementsByClassName("option")[0] = document.getElementById('color').value + ' / ' + document.getElementById('size').value;
    //selectedItemTag.getElementById('option').innerHTML = document.getElementById('color').value + ' / ' + document.getElementById('size').value;
    selectedItemTag.getElementsByTagName("sumprice")[0].innerHTML = parseInt(document.getElementById('price').innerHTML).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    //document.getElementById('sumprice').innerHTML = (parseInt(document.getElementById('count').innerHTML) * parseInt(document.getElementById('price').innerHTML)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

    document.getElementById('color').value = '색상';
    document.getElementById('size').value = '사이즈';



    function min(elem) {
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

    function plu(elem) {
        var countnum = elem.getElementsByClassName('count')[0].innerHTML;
        countnum = parseInt(countnum) + parseInt(1);
        elem.getElementsByClassName('count')[0].innerHTML = countnum;
        elem.getElementsByClassName('sumprice')[0].innerHTML = (countnum * price);
    }
}