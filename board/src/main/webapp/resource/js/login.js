function loginSubmit()
{
	const form = document.loginform;
	const is_checked = form.huid.checked;
	if(form.userid.value=="")
	{
		alert("아이디를 입력하세요.");
		form.userid.focus();
		return false;
	}
	if(form.userpass.value=="")
	{
		alert("비밀번호를 입력하세요.");
		form.userpass.focus();
		return false;
	}
	if(is_checked)
	{
		setCookie('user', form.userid.value, '3');
	}
	else
	{
		delCookie('user');
	}
	
	form.submit();
}

function press(f)
{
    if(f.keyCode == 13){ //javascript에서는 13이 enter키를 의미함
    //console.log(1);
        loginSubmit(); //formname에 사용자가 지정한 form의 name입력
    }

}

function isChecked(){
   const chk = document.loginform.huid;
   const is_checked = chk.checked;
}

window.onload = function()
{
	if(getCookie("user"))
	{
		document.getElementById("userid").value=getCookie("user");
		document.loginform.huid.checked = true;
	}
}