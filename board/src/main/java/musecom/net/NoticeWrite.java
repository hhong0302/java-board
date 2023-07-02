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

@WebServlet("/NoticeWrite")
public class NoticeWrite extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		BoardsDTO dto = new BoardsDTO();
		BoardsDDL ddl = new BoardsDDL();
		String url = null;
		
		dto.setUserid(req.getParameter("userid"));
		dto.setUsername(req.getParameter("username"));
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setBbstitle(req.getParameter("bbstitle"));
		if(dto.getBbstitle().equals("qanda"))
		dto.setIschecked(Integer.parseInt(req.getParameter("ischecked")));
		int count = ddl.countck(dto);
		
		dto.setUip();
		dto.setCount(count);
		boolean isSuccess = ddl.insert(dto);
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("UTF-8");
		String script = null;
		if(isSuccess)
		{
			System.out.println("insert success");
			script = "<script>alert('작성이 완료되었습니다.');"
					+"document.location.href='/board/index.jsp?fname=boards/"+dto.getBbstitle()+"';"
					+"</script>";
		}
		else
		{
			System.out.println("insert failed");
			script = "<script>alert('잘못된 입력입니다.');"
					+"document.location.href='/board/index.jsp?fname=boards/"+dto.getBbstitle()+"';"
					+"</script>";	
		}
		out.println("<html><head></head><body>");
		out.println(script);
		out.println("</body></html>");

		doGet(req, res);
	}

}
