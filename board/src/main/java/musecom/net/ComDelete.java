package musecom.net;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardsDDL;

/**
 * Servlet implementation class ComDelete
 */
@WebServlet("/ComDelete")
public class ComDelete extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		BoardsDDL ddl = new BoardsDDL();
		int num = Integer.parseInt(req.getParameter("num"));
		String bbstitle = req.getParameter("bbstitle");
		String table="";
		if(bbstitle.equals("notice"))table="ncomment";
		if(bbstitle.equals("information"))table="icomment";
		if(bbstitle.equals("free"))table="fcomment";
		if(bbstitle.equals("qanda"))table="qcomment";
				
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("UTF-8");

		int rs = ddl.comdelete(num,table);
		out.print(rs);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
