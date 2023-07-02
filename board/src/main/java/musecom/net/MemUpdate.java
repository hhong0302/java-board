package musecom.net;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.MemberDDL;
import board.MembersDTO;

@WebServlet("/memupdate")
public class MemUpdate extends HttpServlet {  //회원수정

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MembersDTO dto = new MembersDTO();
		MemberDDL ddl = new MemberDDL();
		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("user");
		
		dto.setUserid(req.getParameter("userid"));
		dto.setUserpass(req.getParameter("userpass"));
		dto.setUsername(req.getParameter("username"));
		dto.setUseremail(req.getParameter("useremail"));
		dto.setPostcode(Integer.parseInt(req.getParameter("postcode")));
		dto.setAddr(req.getParameter("addr"));
		dto.setDetailaddr(req.getParameter("detailaddr"));
		dto.setTel(req.getParameter("tel"));
		dto.setUip();
		dto.setWdate();
		boolean isSuccess = ddl.update(dto, user);
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("UTF-8");
		String script = null;
		if(isSuccess)
		{
			script = "<script>alert('수정했습니다.');"
					+"document.location.href='/board';"
					+"</script>";
		}
		else
		{
			script = "<script>alert('잘못된 입력입니다.');"
					+"document.location.href='/board';"
					+"</script>";
		}
		out.println("<html><head></head><body>");
		out.println(script);
		out.println("</body></html>");
		
		doGet(req, res);
	}

}
