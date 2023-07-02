/<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" import="java.util.* , board.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="mem" class="board.MemberDDL" scope="page" />
<jsp:useBean id="dto" class="board.MembersDTO" scope="page" />
<%
	String user = (String) session.getAttribute("user");
	Vector data = mem.getSelect(user);
	int size = data.size();
	for(int i=0;i<size;i++)
	{
		MembersDTO dt = (MembersDTO) data.elementAt(i);
		pageContext.setAttribute("dto", dt);
	}
%>

<h1 class="my-5 text-center">honglog 회원수정</h1>
<div class="container mb-5">
   <form name="edtregisterform" class="registerform" action="/board/memupdate" method="post">
      <div class="mb-3 mt-3">
         <div class="row">
            <label for="userid" class="col-md-2 form-label">
               아이디 :
            </label>
            <div class="col-md-5">
               <input type="text" class="form-control" id="userid" placeholder="아이디" name="userid"
               value="${dto.userid}" readonly />
            </div>
         </div>
         <div>
         	<span class="cantuse"></span>
         </div>
      </div>
      
      <div class="mb-3 mt-3">
         <div class="row">
            <label for="userpass" class="col-md-2 form-label">
               비밀번호 :
            </label>
            <div class="col-md-5">
               <input type="password" class="form-control" id="userpass" placeholder="비밀번호" name="userpass" />
            </div>
         </div>
         <div>
         	<span class="cantuse"></span>
         </div>
      </div>
      
      <div class="mb-3 mt-3">
         <div class="row">
            <label for="reuserpass" class="col-md-2 form-label">
               비밀번호 확인 :
            </label>
            <div class="col-md-5">
               <input type="password" class="form-control" id="reuserpass" placeholder="비밀번호 확인" name="reuserpass" />
            </div>
            <div class="col-md-5">
            
            </div>
         </div>
         <div>
         	<span class="cantuse">비밀번호가 일치하지 않습니다.</span>
         </div>
      </div>
      
      <div class="mb-3 mt-3">
         <div class="row">
            <label for="username" class="col-md-2 form-label">
               이름 :
            </label>
            <div class="col-md-5">
               <input type="text" class="form-control" id="username" placeholder="이름" maxlength="10" name="username"
               value="${dto.username}"  />
            </div>
            <div class="col-md-5">
            
            </div>
         </div>
         <div>
         	<span class="cantuse">이름은 2~10자 이고, 한글 또는 영어만 사용 가능합니다.</span>
         </div>
      </div>
      
      <div class="mb-3 mt-3">
         <div class="row">
            <label for="useremail" class="col-md-2 form-label">
               이메일 :
            </label>
            <div class="col-md-5">
               <input type="text" class="form-control" id="useremail" placeholder="이메일" name="useremail"
               value="${dto.useremail}" />
            </div>
            <div class="col-md-5">
            
            </div>
         </div>
         <div>
         	<span class="cantuse">이메일을 형식에 맞게 입력하세요.</span>
         </div>
      </div>
      
      <div class="mb-3 mt-3">
         <div class="row">
            <label for="postcode" class="col-md-2 form-label">
               주소 :
            </label>
            <div class="col-md-5">
               <input type="number" class="form-control" id="postcode" placeholder="우편번호" name="postcode" readonly
               value="${dto.postcode}"  />
            </div>
            <div class="col-md-5">
               <button type="button" class="btn btn-primary" onclick="sPostcode()">우편번호 검색</button>
            </div>
            <div class="col-md-2"></div>
            <div class="col-md-9 mt-3 col-md-offset-1">
               <input type="text" name="addr" id="addr" class="form-control" readonly 
               value="${dto.addr}" />
            </div>
            <div class="col-md-2"></div>
            <div class="col-md-9 mt-3 col-md-offset-1">
               <input type="text" name="detailaddr" id="detailaddr" class="form-control" placeholder="상세주소" 
               value="${dto.detailaddr}" />
            </div>
         </div>
         <div>
         	<span class="cantuse">주소를 입력하세요.</span>
         </div>
      </div>
      <input type="hidden" name = "postcpdeok"/>
      <div class="mb-3 mt-3">
         <div class="row">
            <label for="tel1" class="col-md-2 form-label">
               전화번호 :
            </label>
            
            <c:forTokens var="tels" items="${dto.tel}" delims="-" varStatus="n">
            <div class="col-md-3">
               <input type="text" class="form-control" id="tel${n.index+1}" name="tel${n.index+1}" value="${tels}"/>
            </div>
            </c:forTokens>
            
         </div>
         <div>
         	<span class="cantuse">전화번호를 형식에 맞게 입력하세요.</span>
         </div>
      </div>
         <input type="hidden" name="tel" id="tel" >
         
      <div class="mb-3 mt-3">
         	<div class="row">
         		<label for="photo" class="form-label col-md-2"> 사진 :</label>
         		<div class="col-md-8">
         			<input type="file" name="photo" id="photo" />
         		</div>
         	</div>
         </div>
         
      <div class="form-btn py-4 text-center">
         <button type="reset" class="btn btn-warning mx-3 px-4">취 소</button>
         <button type="button" class="btn btn-primary mx-3 px-4" onclick="edtregister()">전 송</button>
      </div>
      
   </form>
   <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
   <script src="resource/js/form.js"></script>
</div>














