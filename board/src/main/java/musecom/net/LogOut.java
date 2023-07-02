package musecom.net;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOut extends HttpServlet {  //로그아웃

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//로그아웃을 위해 session 을 지운다.
		HttpSession session = req.getSession();
		session.invalidate();
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("UTF-8");
		String script = "<script>alert('로그아웃 되었습니다');"
						+"document.location.href='/board';"
						+"</script>";
		out.println("<html><head></head><body>");
		out.println(script);
		out.println("</body></html>");
		
		
	}

}
