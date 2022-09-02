function changebannerPW() {
    document.getElementById("banner").innerHTML = "비밀번호 찾기"

}

function changebannerID() {
    document.getElementById("banner").innerHTML = "아이디 찾기"


}

function showid() {
    var form = document.createElement('form');
    form.setAttribute("method", "post");
    form.setAttribute("action", "http://shoppingmall.ddnsking.com/");
    document.characterSet = "utf-8";



    document.getElementById('idname').style.display = 'none';
    document.getElementById('idnum').style.display = 'none ';
    document.getElementById('idbtn').style.display = 'none ';
    if () {
        document.getElementById('idresult').style.display = 'block ';
        document.getElementById('userid').style.display = 'block ';
    } else {
        document.getElementById('idwarn').style.display = 'block ';
    }
}

function showpw() {
    var form = document.createElement('form');
    form.setAttribute("method", "post");
    form.setAttribute("action", "http://shoppingmall.ddnsking.com/");
    document.characterSet = "utf-8";



    document.getElementById('pwname').style.display = 'none';
    document.getElementById('pwnum').style.display = 'none ';
    document.getElementById('pwbtn').style.display = 'none ';
    document.getElementById('emailinput').style.display = 'none ';
    if () {
        document.getElementById('pwresult').style.display = 'block ';
    } else {
        document.getElementById('pwwarn').style.display = 'block ';
    }
}