function comSubmit()
{
	const comment = document.getElementById("comment");
	if(document.getElementById("userid").value="")
	{
		alert("로그인 상태인지 확인하세요.");
		return false;
	}
	if(comment.value=="")
	{
		comment.focus();
		return false;
	}
	document.commentform.submit();
}

function modChange(i)
{
	const content = document.getElementsByClassName("comcontent")[i];
	const commodify = document.getElementsByClassName("commodify")[i];
	content.style.display="none";
	commodify.style.display="block";
}

function modCancel(i)
{
	const content = document.getElementsByClassName("comcontent")[i];
	const commodify = document.getElementsByClassName("commodify")[i];
	const modcomment = document.getElementsByClassName("modcomment")[i];
	modcomment.value=content.innerHTML;
	content.style.display="block";
	commodify.style.display="none";
}

const commodify = document.getElementsByClassName("commodify");

function modSubmit(form)
{
	form.submit();
}

function deleteComm(num,bbstitle,count)
{
	let y = confirm("정말로 삭제하시겠습니까?");
	if(y)
	{
		//보드 삭제
		fetch("/board/ComDelete?num="+num+"&bbstitle="+bbstitle)
		.then(res=>res.json())
			.then(data=>{
				//console.log(data);
				if(data>0)
				{
					alert("삭제되었습니다.");
					document.location.href='/board/index.jsp?fname=boards/bwatch&count='+count+'&bbstitle='+bbstitle;
				}
				else
				{
					alert("문제가 발생했습니다.");
					location.reload();
				}
			});
	}
	else
	{
		return false;
	}
}














