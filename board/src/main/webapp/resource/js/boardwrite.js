const title = document.getElementById("title");
const content = document.getElementById("content");
const wthing = document.getElementsByClassName('writething');
title.addEventListener('keyup',()=>
{
    if(title.value == "") wthing[0].style.display='block';
    else wthing[0].style.display='none';
});

content.addEventListener('keyup',()=>
{
    if(content.value == "") wthing[1].style.display='block';
    else wthing[1].style.display='none';
});

function bbsSave()
{
	const title = document.getElementById("title");
	const content = document.getElementById("content");
	const userid = document.getElementById("userid");
	if(title.value=="")
	{
		wthing[0].style.display="block";
		title.focus();
		return false;
	}
	if(content.value=="")
	{
		wthing[1].style.display="block";
		content.focus();
		return false;
	}
	if(userid.value==""||userid.value==null||userid.value=="null")
	{
		alert("로그인 상태인지 확인하세요.");
		return false;
	}
	
	document.bwriteform.submit();
}




























