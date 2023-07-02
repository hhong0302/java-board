

function setCookie(cname, value, days)
{
	let exdate = new Date();
	exdate.setDate(exdate.getDate()+days);  //쿠키 만료까지의 기간
	let cookie_value = escape(value) + ((days == null)?'':'; expires'+exdate.toUTCString());
	document.cookie = cname + "=" + cookie_value;
}
//쿠키등록 setCookie("user",아이디,"3");

function getCookie(cname)
{
	let x, y;
	let val = document.cookie.split(";");
	for(let i=0;i<val.length;i++)
	{
		x = val[i].substr(0, val[i].indexOf("="));
		y = val[i].substr(val[i].indexOf("=")+1);
		//앞뒤 공백 제거 정규식
		x = x.replace(/^\s+|\s+$/g, '');  //x.replace(" " , "");
		if(x==cname)
		{
			return unescape(y);  //unescape로 디코딩 한 후 리턴
		}
	}
}

function delCookie(cname)
{
	document.cookie = cname + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT';
}

