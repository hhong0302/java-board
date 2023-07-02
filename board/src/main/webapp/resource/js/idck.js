

const uidck = document.getElementById("useridck");


uidck.addEventListener('keyup',()=>
{
	if(!uidPw.test(uidck.value))
	{
		cantuse[0].style.display='block';
		document.getElementById("canuseid").style.display="none";
	    document.getElementById("cantuseid").style.display="none";
	}
	else
	{
		cantuse[0].style.display='none';
		fetch("/board/IdCheck")
		.then(res=>res.text())
			.then(data=>{
				const dt = data.split(' ');
				if(dt[dt.length]=="") dt.pop();
				//console.log(dt);
				
				for(let i=0;i<dt.length;i++)
				{
					//console.log(dt[i]);
					if(uidck.value!=dt[i])
	               	{
	               	  	document.getElementById("cantuseid").style.display="none";
						document.getElementById("canuseid").style.display="block";
	               		document.getElementById("userid").value=uidck.value;
	                	
	               	}
	               	else
	              	{
	              		document.getElementById("canuseid").style.display="none";
	              		document.getElementById("cantuseid").style.display="block";
	              		document.getElementById("userid").value="";
	              		break;
	              	}
				}
				
			});
	}
    
});



function register(){
   //변수 정의
   const uidck = document.getElementById("useridck");
   const userid = document.getElementById("userid");
   const userpass = document.getElementById("userpass");
   const reuserpass = document.getElementById("reuserpass");
   const username = document.getElementById("username");
   const useremail = document.getElementById("useremail");
   const postcode = document.getElementById("postcode");
   const addr = document.getElementById("addr");
   const detailaddr = document.getElementById("detailaddr");
   const tel1 = document.getElementById("tel1");
   const tel2 = document.getElementById("tel2");
   const tel3 = document.getElementById("tel3");
   const tel = tel1.value + "-" + tel2.value + "-" + tel3.value;
   //const postcodeok = document.getElementById("postcodeok");


//아이디 확인
    if(!uidPw.test(userid.value)){
      //alert("아이디는 영문,숫자 4자 이상 8자 이하 입니다.");
      //userid.value = "";
      cantuse[0].style.display='block';
      uidck.focus();
      return false;
   }else if(userpass.value == ""){
      //alert("비밀번호를 입력하세요.");
      cantuse[1].style.display='block';
      userpass.focus();
      return false;
   }else if(reuserpass.value != userpass.value){
      //alert("비밀번호가 다릅니다. 비밀번호를 다시 확인하세요.");
      cantuse[2].style.display='block';
      reuserpass.focus();
      return false;
   }else if(!uname.test(username.value)){
      //console.log(username.value);
      //alert("이름은 한글 또는 영문으로 2자 이상 적어주세요.");
      cantuse[3].style.display='block';
      return false;
   }else if(!ueamil.test(useremail.value)){
      //alert("이메일 형식이 맞지 않습니다. 다시 확인 하세요.");
      //useremail.value == "";
      cantuse[4].style.display='block';
      useremail.focus();
      return false;
   }else if(postcode.value == ""){
      //alert("우편번호를 입력하세요.");
      cantuse[5].style.display='block';
      postcode.focus();
      return false;
   }else if(addr.value == ""){
      //alert("주소를 입력하세요.");
	  cantuse[5].style.display='block';
      addr.focus();
      return false;
   }else if(detailaddr.value == ""){
      //alert("상세주소를 입력하세요.");
	  cantuse[5].style.display='block';
      detailaddr.value == "";
      detailaddr.focus();
      return false;
   }else if(tel1.value == "" || tel2.value == "" || tel3.value == ""){
	  cantuse[6].style.display='block';
      //alert("전화번호를 입력하세요");
      tel1.focus();
      return false;
   }else if(!utel.test(tel)){
      //alert("전화번호를 형식에 맞게 입력하세요.");
	  cantuse[6].style.display='block';
      tel1.focus();
      return false;
   }
   document.getElementById("tel").value = tel;
   document.registerform.submit();


}