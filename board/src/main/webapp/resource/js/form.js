//정규식
//아이디, 비밀번호 영문 대소문자, 숫자, 최소 4자이상 16자 이하
const uidPw = /^[a-zA-Z0-9]{4,16}$/;

//이름
const uname= /^[가-힣a-zA-Z]{2,10}$/;

//이메일
const ueamil = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

const utel = /^01([0|1|6|7|8|9/])-?([0-9]{3,4})-?([0-9]{4})$/;


function sPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var address = ''; // 주소 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    address = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    address = data.jibunAddress;
                }
                var extraRoadAddr = ""; //참고항목 
                //도로명에 동,로,가가 있는 경우 추가
                if(data.bname !== '' &&/[동|로|가]$/g.test(data.bname)){
               extraRoadAddr += data.bname;
            }
            //건물명, 공동주택 추가
            if(data.bname !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== ''?','+data.buildingName : data.buildingName);
            }
                //표시할 참고항목이 있을 경우
                if(extraRoadAddr !== ''){
               extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

                 // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("addr").value = address;
                document.getElementById("addr").value += extraRoadAddr;
                // 커서를 상세주소 필드로 이동한다.
                //document.getElementById("detailaddr").focus();
            }
        }).open();
}

const cantuse = document.getElementsByClassName('cantuse');
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


userpass.addEventListener('keyup',()=>
{
    if(userpass.value == "") cantuse[1].style.display='block';
    else cantuse[1].style.display='none';
    
    if(reuserpass.value == userpass.value) cantuse[2].style.display='none';
   	else cantuse[2].style.display='block';
});

reuserpass.addEventListener('keyup',()=>
{
    if(reuserpass.value != userpass.value) cantuse[2].style.display='block';
    else cantuse[2].style.display='none';
});

username.addEventListener('keyup',()=>
{
    if(!uname.test(username.value)) cantuse[3].style.display='block';
    else cantuse[3].style.display='none';
});

useremail.addEventListener('keyup',()=>
{
    if(!ueamil.test(useremail.value)) cantuse[4].style.display='block';
    else cantuse[4].style.display='none';
});

detailaddr.addEventListener('keyup',()=>
{
    if(detailaddr.value=="") cantuse[5].style.display='block';
    else cantuse[5].style.display='none';
});

//전화번호 3 4 4개 입력하면 다음거로 넘어가기
tel1.addEventListener('keyup',()=>
{
    if(tel1.value.length>=3)
    {
        document.getElementById('tel2').focus();
    }
    else
    {
		cantuse[6].style.display='block';
	}
});
tel2.addEventListener('keyup',()=>
{
    if(tel2.value.length>=4)
    {
        document.getElementById('tel3').focus();
    }
    else
    {
		cantuse[6].style.display='block';
	}
});

tel3.addEventListener('keyup',()=>
{
    if(tel3.value.length>=4)
    {
        cantuse[6].style.display='none';
    }
});

function edtregister(){
   //변수 정의
   //const userid = document.getElementById("userid");
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
   //console.log(tel1.value+" "+tel2.value+" "+tel3.value);
   const tel = tel1.value + "-" + tel2.value + "-" + tel3.value;
   //const postcodeok = document.getElementById("postcodeok");
   //console.log(userpass.value);
   /*else if(userpass.value == ""){
      alert("비밀번호를 입력하세요.");
      userpass.value = "";
      userpass.focus();
      return false;
   }*/if(reuserpass.value != userpass.value){
      //alert("비밀번호가 다릅니다. 비밀번호를 다시 확인하세요.");
      reuserpass.focus();
      return false;
   }
   else if(userpass.value !="" && !uidPw.test(userpass.value)){
      //alert("비밀번호는 영문, 숫자 4자리 이상 8자리 이하 입니다.");
      userpass.focus;
      return false;
   }
   else if(username.value == ""){
      //alert("이름을 입력하세요.");
      username.focus();
      return false;
   }else if(!uname.test(username.value)){
      //console.log(username.value);
      //alert("이름은 한글 또는 영문으로 2자 이상 적어주세요.");
      return false;
   }else if(useremail.value == ""){
      //alert("이메일을 입력하세요.");
      useremail.focus();
      return false;
   }else if(!ueamil.test(useremail.value)){
      //alert("이메일 형식이 맞지 않습니다. 다시 확인 하세요.");
      useremail.focus();
      return false;
   }else if(postcode.value == ""){
      //alert("우편번호를 입력하세요.");
      postcode.focus();
      return false;
   }else if(addr.value == ""){
      //alert("주소를 입력하세요.");
      addr.focus();
      return false;
   }else if(detailaddr.value == ""){
      //alert("상세주소를 입력하세요.");
      detailaddr.focus();
      return false;
   }else if(tel1.value == "" || tel2.value == "" || tel3.value == ""){
      //alert("전화번호를 입력하세요");
      tel1.focus();
      return false;
   }else if(!utel.test(tel)){
      //alert("전화번호를 형식에 맞게 입력하세요.");
      tel1.focus();
      return false;
   }
   document.getElementById("tel").value = tel;
   document.edtregisterform.submit();

}
