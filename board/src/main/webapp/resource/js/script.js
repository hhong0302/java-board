function findIdPw()
{
	const url = "/board/member/findIdPw.jsp";
	const width = 500, height=350;
	let left = (document.body.offsetWidth / 2)-(width / 2);
	let top = (document.body.offsetHeight / 2)-(height / 2);
	left+=window.screenLeft;
	
	window.open(url, "popup", `width=${width}, height=${height}, left=${left}, top=${top}`);
}

function memLevel(e,i,num)
{
	const val = e.value;
	const mems = ["탈퇴회원","신규회원","일반회원","VIP회원"];
	let y = confirm("회원 정보를 "+mems[val]+"으로 수정하시겠습니까?");
	if(y)
	{
		//회원정보 수정
		fetch("/board/LevelUpdate?level="+val+"&num="+num)
		.then(res=>res.json())
			.then(data=>{
				//console.log(data);
				if(data>0)
				{
					
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
		e.options[i].selected=true;
		//console.log(e.options);
		return false;
	}
}