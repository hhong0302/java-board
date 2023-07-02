<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.* , board.*"%>
<jsp:useBean id="ddl" class="board.BoardsDDL" scope="page" />
<jsp:useBean id="dto" class="board.BoardsDTO" scope="page" />
<%! String username = "",userid="";int level=0; %>
<% 

	String bbstitle = request.getParameter("bbstitle");
	
	int count = Integer.parseInt(request.getParameter("count"));
	Vector data = ddl.getTitle(bbstitle,count);


	BoardsDTO dt = (BoardsDTO) data.elementAt(0);
	String userid = dt.getUserid();
	String title = dt.getTitle();
	String content = dt.getContent();
	String name = dt.getUsername();
	int ischecked = dt.getIschecked();
%>

<div class="container text-center">
	<form name="bmodifyform" class="bmodifyform text-center" action="/board/BoardUpdate" method="post">
	<div class="row">
		<div class="col-md-3">
			<select id="bbsselect" onChange="bbsTitle(this)">
			<%
				switch(bbstitle)
				{
				case "notice":
			%>
				<option value="notice">공지사항</option>
					<%
				break;
				case "free":
					%>
					<option value="free">자유게시판</option>
					<%
				break;
				case "information":
					%>
					<option value="information">정보게시판</option>
					<%
				break;
				case "qanda":
					%>
					<option value="qanda">Q&A</option>
					<%
				break;
				}
					%>
				
			</select>
			<%-- <input type="hidden" name="userid" id="userid" value="<%=userid%>" /> --%>
			<input type="hidden" name="count" id="count" value="<%=count%>" />
			<input type="hidden" name="bbstitle" id="bbstitle" value="<%=bbstitle%>" />
		</div>
		<input type="text" name="title" id="title" class="col-md-9" maxlength="200" placeholder="제목을 입력하세요." 
		value="<%=title%>" />
	</div>
	<div class="writething">
		제목을 입력하세요.
	</div>
	<div class="row mt-5">
		<textarea name="content" id="content" cols="30" rows="10" ><%=content%></textarea>
	</div>
	<div class="writething">
		내용을 입력하세요.
	</div>
	<!-- <div class="row">
	파일 들어올 곳
	</div> -->
	<%if(bbstitle.equals("qanda")){ %>
	<div class="mt-2 qandabox" style="text-align:left">
		<label><input type="checkbox" name="ckbox" id="ckbox" onclick="qandacheck()" <%if(ischecked==1){%>checked<%}%> /> 나만보기</label>
		<input type="hidden" name="ischecked" id="ischecked" value="<%if(ischecked==1){%>1<%}else{%>0<%}%>" />
	</div>
	<%} %>
	<div class="form-btn py-4 text-center">
         <button type="button" class="btn btn-primary mx-3 px-4" onclick="bbsModify();">저 장</button>
      </div>
	</form>
</div>

<script src="resource/js/bbs.js"></script>
<script src="resource/js/boardwrite.js"></script>









