package musecom.net;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardsDDL;
import board.BoardsDTO;

@WebServlet("/Commodify")
public class Commodify extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BoardsDTO dto = new BoardsDTO();
		BoardsDDL ddl = new BoardsDDL();
		dto.setNum(Integer.parseInt(req.getParameter("num")));
		dto.setComment(req.getParameter("modcomment"));
		dto.setWdate();
		int count = Integer.parseInt(req.getParameter("count"));
		String bbstitle = req.getParameter("bbstitle");
		String table="";
		if(bbstitle.equals("notice"))table="ncomment";
		if(bbstitle.equals("information"))table="icomment";
		if(bbstitle.equals("free"))table="fcomment";
		if(bbstitle.equals("qanda"))table="qcomment";
		
		boolean isSuccess = ddl.comupdate(dto,table);
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("UTF-8");
		String script = null;
		if(isSuccess)
		{
			script = "<script>alert('수정했습니다.');"
					+"document.location.href='/board/index.jsp?fname=boards/bwatch&count="+count+"&bbstitle="+bbstitle+"';"
					+"</script>";			
		}
		else
		{
			script = "<script>alert('문제가 발생했습니다.');"
					+"document.location.href='/board/index.jsp?fname=boards/bwatch&count="+count+"&bbstitle="+bbstitle+"';"
					+"</script>";	
		}
		out.println("<html><head></head><body>");
		out.println(script);
		out.println("</body></html>");
		
		doGet(req, res);
	}

}
