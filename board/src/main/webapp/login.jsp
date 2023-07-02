<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>

<%!
	String message = null;
%>

<%
	message = (String) request.getAttribute("message");
	if(message != null)
	{
%>

	<script>
	alert("<%=message %>");
	</script>

<%
	}
%>

<h1 class="my-5 text-center">MY BBS 로그인</h1>
<div class="container mb-5">

	<form name="loginform" class="loginform" action="/board/memberlogin" method="post">
		<div class="mb-3 mt-3">
			<div class="row">
				<label for="userid" class="col-md-3 form-label">
					아이디 :
				</label>
				<div class="col-md-9">
					<input type="text" class="form-control" id="userid" placeholder="아이디" name="userid" onkeyup="press(event)" />
				</div>
			</div>
		</div>
		
		<div class="mb-3 mt-3">
			<div class="row">
				<label for="userpass" class="col-md-3 form-label">
					비밀번호 :
				</label>
				<div class="col-md-9">
					<input type="password" class="form-control" id="userpass" placeholder="비밀번호" name="userpass" onkeyup="press(event)" />
				</div>
			</div>
		</div>
		<div class="mb-3 mt-3">
			<div class="row">
				<div class="col-md-4 text-start">
					<label>
						<input type="checkbox" id="huid" name="huid" value="ok" onclick="isChecked()" />
						아이디 저장
					</label>
				</div>
			</div>
		</div>
		<div class="mb-3 mt-3 mt-4">
			<div class="row justify-content-center align-items-center">
				<button type="button" class="btn btn-primary mx-2 py-2" onclick="loginSubmit()"> 로그인 </button>
			</div>
		</div>
		<div class="mb-1 mt-3">
			<div class="row">
				<div class="col-md-7 mt-3">
					<a href="?fname=member/register" class="me-3">회원가입</a>
					<a href="javascript:void(0)" onclick="findIdPw()">아이디/패스워드 찾기</a>
				</div>
			</div>
		</div>
		
	</form>
</div>
<div class="container wh600">
	<div class="row">
		<div class="col-md-6">
			<div class="d-grid">
				<a href="#" class="btn btn-block naver-btn">
					네이버로그인
				</a>
			</div>
		</div>
		<div class="col-md-6">
			<div class="d-grid">
				<a href="#" class="btn btn-block kakao-btn">
					카카오로그인
				</a>
			</div>
		</div>
	</div>
</div>

<script src="resource/js/login.js"></script>
<script src="resource/js/script.js"></script>
<script src="resource/js/cookie.js"></script>

























