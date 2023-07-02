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

@WebServlet("/BoardUpdate")
public class BoardUpdate extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		BoardsDTO dto = new BoardsDTO();
		BoardsDDL ddl = new BoardsDDL();
		
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setWdate();
		dto.setBbstitle(req.getParameter("bbstitle"));
		dto.setCount(Integer.parseInt(req.getParameter("count")));
		if(req.getParameter("bbstitle").equals("qanda")) dto.setIschecked(Integer.parseInt(req.getParameter("ischecked")));
		else dto.setIschecked(0);
		
		boolean isSuccess = ddl.update(dto);
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("UTF-8");
		String script = null;
		if(isSuccess)
		{
			script = "<script>alert('수정했습니다.');"
					+"document.location.href='/board/index.jsp?fname=boards/"+dto.getBbstitle()+"';"
					+"</script>";			
		}
		else
		{
			script = "<script>alert('문제가 발생했습니다.');"
					+"document.location.href='/board/index.jsp?fname=boards/"+dto.getBbstitle()+"';"
					+"</script>";	
		}
		out.println("<html><head></head><body>");
		out.println(script);
		out.println("</body></html>");
		
		doGet(req, res);
	}

}
