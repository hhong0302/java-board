<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.* , board.*"%>

<jsp:useBean id="ddl" class="board.BoardsDDL" scope="page" />
<jsp:useBean id="dto" class="board.BoardsDTO" scope="page" />

<%!
	int listNum = 10;
	int pageNum = 10;
	int level=0;
%>


<%
	try
	{
		level = (int) session.getAttribute("level");
	}
	catch(Exception e)
	{
		level = 0;
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

	String bbstitle = "notice";
	Vector data = ddl.getTitle(bbstitle,limitNum,listNum);
	int maxColumn = ddl.getAllTitle(bbstitle);
	int size = data.size();
	
	//전체 페이지 수, 전체 블럭 수, 현재 블럭번호, 블럭 당 시작번호, 블럭 당 마지막 번호
	
	int totalPage = (int)Math.ceil((double)maxColumn / listNum);
	int totalBlock = (int)Math.ceil((double)totalPage / pageNum);
	int nowBlock = (int)Math.ceil((double)mypg / pageNum);
	int startNum = (nowBlock-1)*pageNum+1;
	int endNum = nowBlock*pageNum;
	if(endNum>totalPage) endNum = totalPage;

%>

<div class="container">
	<h1 class="text-center">공지 사항</h1>
	<div class="row">
		<table class="table table-striped">
			<thead>
				<tr>
					<th></th>
					<th class="text-center">제목</th>
					<th class="text-center">내용</th>
					<th class="text-center">작성자</th>
					<th class="text-center">작성일(수정일)</th>
					<th class="text-center">조회수</th>
					<td class="text-center">댓글수</td>
				</tr>
			</thead>
			<tbody>
<%
	for(int i=0;i<size;i++)
	{
		BoardsDTO dt= (BoardsDTO) data.elementAt(i);
		String title = dt.getTitle();
		String content = dt.getContent();
		String name = dt.getUsername();
		String wdate = dt.getWdate();
		wdate = wdate.substring(0,wdate.lastIndexOf(":"));
		int count = dt.getCount();
		int views = dt.getViews();
		int allcomment = ddl.getAllComments(count,bbstitle);
%>
				<tr>
					<td><%-- <%=count%> --%></td>
					<td class="col-md-2 tdtitle"><%=title%></td>
					<td class="content-td col-md-5">
						<a href="?fname=boards/bwatch&count=<%=count%>&bbstitle=notice" class="contentlink">
							<%=content%>
						</a>
					</td>
					<td class="text-center col-md-1"><%=name%></td>
					<td class="text-center col-md-2"><%=wdate%></td>
					<td class="text-center col-md-1"><%=views%></td>
					<td class="text-center col-md-1"><%=allcomment%></td>
				</tr>
<%  }%>
			</tbody>
		</table>
	</div>
	
	<div class="mt-3 mb-5 row">
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
		<%if(level==99){ %>
		<div class="d-flex justify-content-end newboard">
			<a href="?fname=boards/bwrite&bbstitle=notice">새 글 작성</a>
		</div>
		<%}else{} %>
	</div>
</div>








