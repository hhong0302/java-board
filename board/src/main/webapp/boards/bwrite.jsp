<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%! String username = "",userid="";int level=0; %>
<% 
	userid = (String) session.getAttribute("user");
	username = (String) session.getAttribute("username");
	String bbstitle = request.getParameter("bbstitle");
	try
	{
		level = (int) session.getAttribute("level");
	}
	catch(Exception e)
	{
		
	}
%>

<div class="container text-center">
	<form name="bwriteform" class="bwriteform text-center" action="/board/NoticeWrite" method="post">
	<div class="row">
		<div class="col-md-3">
			<select id="bbsselect" onChange="bbsTitle(this)">
			<%
				String s1="",s2="",s3="",s4="";
				switch(bbstitle)
				{
				case "notice":s1="selected";break;
				case "free":s2="selected";break;
				case "information":s3="selected";break;
				case "qanda":s4="selected";break;
				}
			%>
			<%if(level==99){ %>
				<option value="notice" <%=s1%>>공지사항</option>
			<%} %>
				<option value="free" <%=s2%>>자유게시판</option>
				<option value="information" <%=s3%>>정보게시판</option>
				<option value="qanda" <%=s4%>>Q&A</option>
			</select>
			<input type="hidden" name="userid" id="userid" value="<%=userid%>" />
			<input type="hidden" name="username" id="username" value="<%=username%>" />
			<input type="hidden" name="bbstitle" id="bbstitle" value="<%=bbstitle%>" />
		</div>
		<input type="text" name="title" id="title" class="col-md-9" maxlength="200" placeholder="제목을 입력하세요." />
	</div>
	<div class="writething">
		제목을 입력하세요.
	</div>
	<div class="row mt-5">
		<textarea name="content" id="content" cols="30" rows="10"></textarea>
	</div>
	<div class="writething">
		내용을 입력하세요.
	</div>
	<!-- <div class="row">
	파일 들어올 곳
	</div> -->
	<%if(bbstitle.equals("qanda")){ %>
	<div class="mt-2 qandabox" style="text-align:left">
		<label><input type="checkbox" name="ckbox" id="ckbox" onclick="qandacheck()" /> 나만보기</label>
		<input type="hidden" name="ischecked" id="ischecked" value="0" />
	</div>
	<%} %>
	<div class="form-btn py-3 text-center">
         <button type="button" class="btn btn-primary mx-3 px-4" onclick="bbsSave();">저 장</button>
      </div>
	</form>
</div>

<script src="resource/js/bbs.js"></script>
<script src="resource/js/boardwrite.js"></script>









