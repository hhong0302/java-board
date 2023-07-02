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

/**
 * Servlet implementation class Comments
 */
@WebServlet("/Comments")
public class Comments extends HttpServlet {
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		BoardsDTO dto = new BoardsDTO();
		BoardsDDL ddl = new BoardsDDL();
		
		dto.setUserid(req.getParameter("cuserid"));
		dto.setUsername(req.getParameter("username"));
		dto.setCount(Integer.parseInt(req.getParameter("count")));
		dto.setComment(req.getParameter("comment"));
		dto.setUip();
		dto.setWdate();
		String bbstitle = req.getParameter("bbstitle");
		String table="";
		if(bbstitle.equals("notice"))table="ncomment";
		if(bbstitle.equals("information"))table="icomment";
		if(bbstitle.equals("free"))table="fcomment";
		if(bbstitle.equals("qanda"))table="qcomment";
		
		boolean isSuccess = ddl.comment(dto,table);
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("UTF-8");
		String script = null;
		if(isSuccess)
		{
			System.out.println("insert success");
			script = "<script>alert('작성 완료');"
					+"document.location.href='/board/index.jsp?fname=boards/bwatch&count="+dto.getCount()+"&bbstitle="+bbstitle+"'"
					+"</script>";
		}
		else
		{
			System.out.println("insert failed");
			script = "<script>alert('잘못된 입력입니다.');"
					+"document.location.href='/board/index.jsp?fname=boards/bwatch&count="+dto.getCount()+"&bbstitle="+bbstitle+"'"
					+"</script>";	
		}
		out.println("<html><head></head><body>");
		out.println(script);
		out.println("</body></html>");

		doGet(req, res);
	}

}
