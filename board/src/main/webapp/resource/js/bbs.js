function bbsTitle(e)
{
	//console.log(e.value);
	document.getElementById("bbstitle").value=e.value;
	const qandabox = document.getElementsByClassName("qandabox")[0];
	if(e.value=="qanda")
	{
		qandabox.style.display="block";
		qandacheck();
	}
	else
	{
		qandabox.style.display="none";
		document.getElementById("ischecked").value=0;
	}
}

function bbsModify()
{
	const title = document.getElementById("title");
	const content = document.getElementById("content");
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
	
	document.bmodifyform.submit();
}

function deleteBoard(count,bbstitle)
{
	let y = confirm("정말로 삭제하시겠습니까?");
	if(y)
	{
		//보드 삭제
		fetch("/board/BoardDelete?count="+count+"&bbstitle="+bbstitle)
		.then(res=>res.json())
			.then(data=>{
				//console.log(data);
				if(data>0)
				{
					alert("삭제되었습니다.");
					document.location.href='/board/index.jsp?fname=boards/'+bbstitle;
				}
				else
				{
					alert("문제가 발생했습니다.");
					document.location.href='/board/index.jsp?fname=boards/'+bbstitle;
				}
			});
	}
	else
	{
		return false;
	}
}

function qandacheck()
{
	const chk = document.getElementById("ckbox");
	const ck = chk.checked;
	if(ck)document.getElementById("ischecked").value="1";
	else document.getElementById("ischecked").value="0";
}

function watchqna(count,bbstitle)
{
	fetch("/board/QandA?count="+count+"&bbstitle="+bbstitle)
		.then(res=>res.json())
			.then(data=>{
				//console.log(data);
				if(data>0)
				{
					document.location.href='/board/index.jsp?fname=boards/bwatch&count='+count+'&bbstitle='+bbstitle;
				}
				else
				{
					alert("문제가 발생했습니다.");
					location.reload();
				}
			});
}












