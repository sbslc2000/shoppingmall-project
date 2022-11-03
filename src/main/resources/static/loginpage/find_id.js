function changebannerPW() {
    document.getElementById("banner").innerHTML = "비밀번호 찾기"

}

function changebannerID() {
    document.getElementById("banner").innerHTML = "아이디 찾기"


}

function showid() {
    var idname = document.getElementById('idname').value;
    var idnum = document.getElementById('idnum').value;

    fetch('http://peteworld.shop/api/users/findID?userName=' + idname + '&phoneNumber=' + idnum)
        .then((response) => response.text())
        .then((data) =>

            document.getElementById('userid').innerHTML = data
        );

    document.getElementById('idname').style.display = 'none';
    document.getElementById('idnum').style.display = 'none ';
    document.getElementById('idbtn').style.display = 'none ';
    if (true) {
        document.getElementById('idresult').style.display = 'block ';
        document.getElementById('userid').style.display = 'block ';
    } else {
        document.getElementById('idwarn').style.display = 'block ';
    }
}

function showpw() {
    var form = document.createElement('form');
    form.setAttribute("method", "get");
    form.setAttribute("action", "http://peteworld.shop/api/users/findpw");
    document.characterSet = "utf-8";



    document.getElementById('pwname').style.display = 'none';
    document.getElementById('pwnum').style.display = 'none ';
    document.getElementById('pwbtn').style.display = 'none ';
    document.getElementById('emailinput').style.display = 'none ';
    if (true) {
        document.getElementById('pwresult').style.display = 'block ';
    } else {
        document.getElementById('pwwarn').style.display = 'block ';
    }
}