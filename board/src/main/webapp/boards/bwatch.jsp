<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.* , board.*"%>

<jsp:useBean id="ddl" class="board.BoardsDDL" scope="page" />
<jsp:useBean id="dto" class="board.BoardsDTO" scope="page" />
<%! 
String user="",username="",table="",userid="",title="",content="",name="",wdate="",uip=""; 
int level=0,listNum=10,pageNum=10,views=0; 
BoardsDTO dt = null;%>
<%
	try
	{
		user = (String)session.getAttribute("user");
		username = (String) session.getAttribute("username");
		level = (int) session.getAttribute("level");
	}
	catch(Exception e)
	{
		user = "";
		level=0;
		dto.setUip();
		uip = dto.getUip();
	}

	int count = Integer.parseInt(request.getParameter("count"));
	String bbstitle = request.getParameter("bbstitle");

	if(user==""||user==null||user=="null")
	{
		ddl.isview(uip,count,bbstitle);
	}
	else
	{
		ddl.isview(user,count,bbstitle);
	}

	String pg = request.getParameter("page");
	int mypg = 1;
	
	try
	{
		mypg = Integer.parseInt(pg);
		if(mypg<1)mypg=1;
	}
	catch(Exception e)
	{
		mypg = 1;
	}
	int limitNum = (mypg-1)*listNum;
	
	Vector data = ddl.getTitle(bbstitle,count);
	
	if(bbstitle.equals("notice"))table="ncomment";
	if(bbstitle.equals("information"))table="icomment";
	if(bbstitle.equals("free"))table="fcomment";
	if(bbstitle.equals("qanda"))table="qcomment";
	
	Vector cdata = ddl.getComment(count,limitNum,listNum,table);
	int bnum = ddl.before(bbstitle,count);
	int anum = ddl.after(bbstitle, count);
	int maxColumn = ddl.getAllComments(count,bbstitle);
	int csize = cdata.size();
	
	//전체 페이지 수, 전체 블럭 수, 현재 블럭번호, 블럭 당 시작번호, 블럭 당 마지막 번호
	
	int totalPage = (int)Math.ceil((double)maxColumn / listNum);
	int totalBlock = (int)Math.ceil((double)totalPage / pageNum);
	int nowBlock = (int)Math.ceil((double)mypg / pageNum);
	int startNum = (nowBlock-1)*pageNum+1;
	int endNum = nowBlock*pageNum;
	if(endNum>totalPage) endNum = totalPage;


	try
	{
		dt = (BoardsDTO) data.elementAt(0);
		userid = dt.getUserid();
		title = dt.getTitle();
		content = dt.getContent();
		name = dt.getUsername();
		wdate = dt.getWdate();
		wdate = wdate.substring(0,wdate.lastIndexOf(":"));
		views = dt.getViews();
	}
	catch(Exception e)
	{
		%>
			<script>
				alert("삭제되었거나 잘못된 글 입니다.");
				history.go(-1);
			</script>
		<%		
	}
	
%>


<div class="container">
	<div class="row board-form">
		<h1 class="mb-5 bbsh1">
			<%if(bbstitle.equals("notice")){%>공지사항<%}%>
			<%if(bbstitle.equals("information")){%>정보게시판<%}%>
			<%if(bbstitle.equals("free")){%>자유게시판<%}%>
			<%if(bbstitle.equals("qanda")){%>Q&A<%}%>
		</h1>
		<h1 class="bform-h"><%=title%></h1>
		<div class="wnameday my-3">
			<span class="wname"><%=name%></span> · <span class="wday"><%=wdate%></span> · <span class="wview">조회수 <%=views%></span>
		</div>
		<textarea class="wcontent mt-3" readonly><%=content%></textarea>
	<%if(userid.equals(user)){ %>
	<div class="row my-3">
		<div class="col-md-10"></div>
		<div class="col-md-1 tright">
			<a href="?fname=boards/bmodify&count=<%=count%>&bbstitle=<%=bbstitle%>"
			class="modifya">
				수 정
			</a>
		</div>
		<div class="col-md-1 tright">
			<a class="modifya" style="color:#EB6769;cursor:pointer;" onclick="deleteBoard(<%=count%>,'<%=bbstitle%>');">
				삭 제
			</a>
		</div>
	</div>
	<%} %>
			<%-- <div class="watchlist">
			
				<a href="
				<%if(bnum>0){ 
				%>?fname=boards/bwatch&count=<%=bnum%>&bbstitle=<%=bbstitle%>
				<%}else{ 
				%>javascript:void(0)
				<%}%>">이 전 글</a>
				
			</div> --%>
			<%-- <div class="watchlist">
			<a href="
				<%if(anum>0){ 
				%>?fname=boards/bwatch&count=<%=anum%>&bbstitle=<%=bbstitle%>
				<%}else{ 
				%>javascript:void(0)
				<%}%>">다 음 글</a>
			</div> --%>
		
		<h1 class="pt-2 mt-3 commenth">댓글쓰기</h1>
		<%if((bbstitle.equals("qanda")&&(level==99||userid.equals(user)))||(!bbstitle.equals("qanda")&&level>0)){ %>
		<form name="commentform" id="commentform" action="/board/Comments" method="post">
			<div class="commentbox text-center">
				<input type="hidden" id="cuserid" name="cuserid" value="<%=user%>" />
				<input type="hidden" id="username" name="username" value="<%=username%>" />
				<input type="hidden" id="count" name="count" value="<%=count%>" />
				<input type="hidden" name="bbstitle" id="bbstitle" value="<%=bbstitle %>" />
				<textarea name="comment" id="comment" cols="30" rows="4" placeholder="댓글은 200자 내로 작성 가능합니다." maxlength="200" ></textarea>
			</div>
			<div class="btnbox my-3 text-end">
				<button class="commentbtn" onclick="comSubmit()">댓글 작성</button>
			</div>
		</form>
		<%}else if(!bbstitle.equals("qanda")){ %>
		<div class="commentbox mb-4">
			<span>댓글 작성 권한이 없습니다. <a href="index.jsp?fname=login">로그인</a> 해 주세요.</span>
		</div>
		<%}else{ %>
		<div class="commentbox mb-4">
			<span>댓글 작성 권한이 없습니다.</span>
		</div>
		<%} %>
		<h1 class="commenth pb-3" style="border-bottom:1px solid #eee;">댓글 <%=maxColumn%></h1>
		<ul class="commentlist">
			<%
			for(int i=0;i<csize;i++)
			{
				BoardsDTO cdt= (BoardsDTO) cdata.elementAt(i);
				int num = cdt.getNum();
				String cuserid = cdt.getUserid();
				String comment = cdt.getComment();
				String cname = cdt.getUsername();
				String cwdate = cdt.getWdate();
			%>
				<li>
					<div class="comname d-flex align-items-center">
						<span class="name"><%=cname %></span>&nbsp;·&nbsp;<span class="date"><%=cwdate %></span>
						<%if(cuserid.equals(user)){ %>
							&nbsp;|&nbsp;<button class="mbutton" onclick="modChange(<%=i%>)">수정</button><button class="dbutton" onclick="deleteComm(<%=num%>,'<%=bbstitle%>','<%=count%>')">삭제</button>
						<%} %>
					</div>
					<div class="comcontent px-2"><%=comment %></div>
					<div class="commodify">
						<form name="commodifyform<%=i%>" id="commodifyform<%=i%>" action="/board/Commodify" method="post">
							<input type="hidden" name="bbstitle" id="bbstitle<%=i%>" value="<%=bbstitle %>" />
							<input type="hidden" name="num" id="num<%=num%>" value="<%=num%>" />
							<input type="hidden" name="count" id="count<%=num%>" value="<%=count%>" />
							<textarea class="modcomment" name="modcomment" id="modcomment<%=i%>" cols="30" rows="4" placeholder="댓글 작성. 댓글은 200자 내로 작성 가능합니다." maxlength="200" ><%=comment %></textarea>
							<div class="modbtn mt-1">
							<button type="button" style="background-color:#EB6769" onclick="modCancel(<%=i%>)">취 소</button>
							<button type="button" style="background-color:#0d6efd" onclick="modSubmit(document.commodifyform<%=i%>)">확 인</button>
							</div>
						</form>
					</div>
				</li>
			<%} %>
		</ul>
		<div class="mt-3 mb-5">
		<%if(endNum==0){ %>
		<div class="py-5 text-center nocomment">
			댓글이 없습니다.
		</div>
		<%}else{ %>
		<ul class="pagination justify-content-center mb-2">
			<%
				//이전 페이지
				if(startNum>1)
				{
					int prevPage = startNum - 1;
					out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"?fname=boards/notice&page="+prevPage+"\">이전</a></li>");
				}
				else
				{
					out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"javascript:void(0)\">이전</a></li>");
				}
				//페이지 출력
				for(int i=startNum;i<=endNum;i++)
				{
					String act = "";
					if(mypg==i) act="active";
						
				%>
					<li class="page-item <%=act%>"><a class="page-link" href="?fname=boards/notice&page=<%=i%>"><%=i%></a></li>
				<%
				}
				
				//다음 페이지
				if(endNum<totalPage)
				{
					int nextPage = endNum + 1;
					out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"?fname=boards/notice&page="+nextPage+"\">다음</a></li>");
				}
				else
				{
					out.print("<li class=\"page-item\"><a class=\"page-link\" href=\"javascript:void(0)\">다음</a></li>");
				}
				%>
				
		</ul>
		<%} %>
	</div>
	
	<div class="watchlist my-1 text-center">
		<a href="?fname=boards/<%=bbstitle%>" class="mb-4">목록으로 돌아가기</a>
	</div>
	</div>
</div>

<script src="resource/js/bbs.js"></script>
<script src="resource/js/comment.js"></script>