function checkpw() {
    var pw11 = document.getElementById('pw1').value;
    var pw22 = document.getElementById('pw2').value;

    var char = ["!", "@", "#", "$", "%"];
    var eng = ["a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"];
    var num = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"];
    var check_char = 0;
    var check_eng = 0;
    var check_num = 0;


     if (pw11.length < 8) {
        document.getElementById('pw1warn').innerHTML = "숫자, 영문, 특수문자(!@#$%)포함 8자리 이상";
        document.getElementById('pw1warn').style.color = 'red';
    }

    for (var i = 0; i < char.length; i++) {
        if ((pw11.indexOf(char[i]) != -1)) {
            check_char = 1;
            break;

        }
    }
    for (var i = 0; i < eng.length; i++) {
        if (pw11.indexOf(eng[i]) != -1) {
            check_eng = 1;
            break;
        }
    }
    for (var i = 0; i < eng.length; i++) {
        if (pw11.indexOf(num[i]) != -1) {
            check_num = 1;
            break;
        }
    }

    if ((check_char == 0) || (check_eng == 0) || (check_num == 0)) {
        document.getElementById('pw1warn').innerHTML = "숫자, 영문, 특수문자(!@#$%)포함 8자리 이상";
        document.getElementById('pw1warn').style.color = 'red';
    }
}

function samePW() {
    if (document.getElementById('pw1').value != '' && document.getElementById('pw2').value != '') {
        if (document.getElementById('pw1').value == document.getElementById('pw2').value) {
            document.getElementById('pw2warn').innerHTML = "비밀번호가 일치합니다.";
            document.getElementById('pw2warn').style.color = 'green';
        } else {
            document.getElementById('pw2warn').innerHTML = "비밀번호가 일치하지 않습니다.";
            document.getElementById('pw2warn').style.color = 'red';
        }
    }

}

function checkblank() {
    if (document.getElementById('nameinput').value == '') {
        document.getElementById('warnname').innerHTML = "*필수항목 입니다.";
        document.getElementById('warnname').style.color = 'red';
    }
    if (document.getElementById('monthselect').value == '월' || document.getElementById('dayselect').value == '일' || document.getElementById('year').value == '') {
        document.getElementById('warnbirth').innerHTML = "*필수항목 입니다.";
        document.getElementById('warnbirth').style.color = 'red';
    }
    if (document.getElementById('phoneinput').value == '' || document.getElementById('dayselect').value == '') {
        document.getElementById('warnphone').innerHTML = "*필수항목 입니다.";
        document.getElementById('warnphone').style.color = 'red';
    }
    if (document.getElementById('mailinput').value == '' || document.getElementById('dayselect').value == '') {
        document.getElementById('warnmail').innerHTML = "*필수항목 입니다.";
        document.getElementById('warnmail').style.color = 'red';
    }


}
