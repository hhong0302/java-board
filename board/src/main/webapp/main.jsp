<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Vector , board.BoardsDTO"%>

<jsp:useBean id="ddl" class="board.BoardsDDL" scope="page" />

<%
	Vector ndata = ddl.getTitle("notice",0,5);
	int nsize = ndata.size();
	Vector rdata = ddl.getLists("recent");
	int rsize = rdata.size();
	Vector fdata = ddl.getTitle("free",0,5);
	int fsize = fdata.size();
	Vector idata = ddl.getTitle("information",0,5);
	int isize = idata.size();
	Vector bdata = ddl.getLists("best");
	int bsize = bdata.size();
%>

<div class="container">
   <div class="row">
      <div class="col-md-6 col-6 mb-3 p-5">
           <h2 class="text-center mb-4">공지사항</h2>
           <ul class="list-group mainul">
           <%
             for(int i=0; i< nsize; i++){
         		BoardsDTO dt= (BoardsDTO) ndata.elementAt(i);
         		int count = dt.getCount();
         		String title = dt.getTitle();
         		String name = dt.getUsername();
           %>
             <li class="list-group-item list-group-item-action d-flex row justify-content-between mainleftli">
                 <a href="?fname=boards/bwatch&count=<%=count%>&bbstitle=notice" class="col-md-9"> <%=title %> </a>
                 <span class="col-md-3" style="text-align:right;"><%=name%></span>
             </li>    
           <%
             }
           %>  
           </ul>
           
          <h2 class="text-center mb-4 mt-4">최신글</h2>
          <ul class="list-group mainul">
           <%
             for(int i=0; i< rsize; i++){
          		BoardsDTO dt= (BoardsDTO) rdata.elementAt(i);
         		int count = dt.getCount();
          		String title = dt.getTitle();
          		String bbstitle = dt.getBbstitle();
          		if(bbstitle.equals("information")) bbstitle = "정보게시판";
          		if(bbstitle.equals("notice")) bbstitle = "공지사항";
          		if(bbstitle.equals("free")) bbstitle = "자유게시판";
           %>
             <li class="list-group-item list-group-item-action d-flex row justify-content-between mainleftli">
                 <a href="?fname=boards/bwatch&count=<%=count%>&bbstitle=free" class="col-md-9"> <%=title %> </a>
                 <span class="col-md-3" style="text-align:right;"><%=bbstitle%></span>
             </li>   
           <%
             }
           %>  
          </ul>
           
          <h2 class="text-center mb-4 mt-4">자유게시판</h2>
          <ul class="list-group mainul">
           <%
             for(int i=0; i< fsize; i++){
          		BoardsDTO dt= (BoardsDTO) fdata.elementAt(i);
         		int count = dt.getCount();
          		String title = dt.getTitle();
          		String name = dt.getUsername();
           %>
             <li class="list-group-item list-group-item-action d-flex row justify-content-between mainleftli">
                 <a href="?fname=boards/bwatch&count=<%=count%>&bbstitle=free" class="col-md-9"> <%=title %> </a>
                 <span class="col-md-3" style="text-align:right;"><%=name%></span>
             </li>   
           <%
             }
           %>  
          </ul>
          
          <h2 class="text-center mb-4 mt-4">정보게시판</h2>
          <ul class="list-group mainul">
           <%
             for(int i=0; i< isize; i++){
          		BoardsDTO dt= (BoardsDTO) idata.elementAt(i);
         		int count = dt.getCount();
          		String title = dt.getTitle();
          		String name = dt.getUsername();
           %>
             <li class="list-group-item list-group-item-action d-flex row justify-content-between mainleftli">
                 <a href="?fname=boards/bwatch&count=<%=count%>&bbstitle=free" class="col-md-9"> <%=title %> </a>
                 <span class="col-md-3" style="text-align:right;"><%=name%></span>
             </li>   
           <%
             }
           %>  
          </ul>
      </div>
      <div class="col-md-6 col-6 my-5">
          <h2 class="text-center mb-4">인기글</h2>
          <div class="row justify-content-around">
          <%
          	for(int i=0;i<bsize;i++)
          	{
          		BoardsDTO dt = (BoardsDTO) bdata.elementAt(i);
          		String username = dt.getUsername();
          		String title = dt.getTitle();
          		String content = dt.getContent();
          		String wdate = dt.getWdate();
          		wdate = wdate.substring(wdate.indexOf("-")+1, wdate.lastIndexOf("-"))+"월"
          				+wdate.substring(wdate.lastIndexOf("-")+1, wdate.lastIndexOf("-")+3)+"일";
          		int views = dt.getViews();
          		String bbstitle = dt.getBbstitle();
          		int count = dt.getCount();
          		int comment = ddl.getAllComments(count,bbstitle);
          		
          %>
              <div class="col-md-5 col-5 m-2">        
					<h1 class="besth1 mt-2">인기글<%=i+1%> · <span>조회수 <%=views%></span></h1>
					<a href="?fname=boards/bwatch&count=<%=count%>&bbstitle=<%=bbstitle%>" class="bestcon text-center d-block">
						<span class="bestconbox mx-3 mt-3 d-flex justify-content-center
						align-items-center">
							<span class="py-5 d-inline-block text-truncate" style="width:50%;">
								<%=content%>
							</span>
						</span>
						<span class="besttitle d-flex align-items-left mx-4">
							<span class="d-inline-block text-truncate"
							style="width:50%; text-align:left;">
								<%=title%>
							</span>
						</span>
						<span class="fw-bold d-flex justify-content-between mx-4 mb-3" style="width:100%; font-size:13px;">
							<%=username%>
						</span>
						<span class="bestinf my-1 d-flex justify-content-between px-4" style="width:100%;">
							<span class="">
								<%=wdate%>
							</span>
							<span>
								댓글 <%=comment%>
							</span>
						</span>
						<span class="d-flex justify-content-left mx-4 mb-2 bestinf">
							<%
							if(bbstitle.equals("notice")){%>공지사항
							<%}if(bbstitle.equals("information")){%>정보게시판
							<%}if(bbstitle.equals("free")){%>자유게시판
							<%}%>
						</span>
					</a>
              </div>
              <%}%>
          </div>
      </div>
		<img src="upload/users/hero.jpg" alt="" />
   </div>
</div>


















