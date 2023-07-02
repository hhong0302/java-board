package musecom.net;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardsDDL;
import board.BoardsDTO;

@WebServlet("/QandA")
public class QandA extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BoardsDTO dto = new BoardsDTO();
		BoardsDDL ddl = new BoardsDDL();
		HttpSession session = req.getSession();
		
		String user="";
		int level=0;
		try
		{
			user = (String) session.getAttribute("user");
			level = (int) session.getAttribute("level");
		}
		catch(Exception e)
		{
			user = "";
			level = 0;
		}
		
		
		dto.setCount(Integer.parseInt(req.getParameter("count")));
		dto.setBbstitle(req.getParameter("bbstitle"));
		
		boolean isSuccess = ddl.watchqa(dto,user);
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("UTF-8");
		String script = "";
		
		if(level==99||isSuccess)
		{
			script = "<script>"
					+"document.location.href='/board/index.jsp?fname=boards/bwatch&count="+dto.getCount()+"&bbstitle="+dto.getBbstitle()+"';"
					+"</script>";
		}
		else
		{
			script = "<script>alert('접근 권한이 없습니다.');"
					+"document.location.href='/board/index.jsp?fname=boards/qanda';"
					+"</script>";
		}
		out.println("<html><head></head><body>");
		out.println(script);
		out.println("</body></html>");
		
	}

}
