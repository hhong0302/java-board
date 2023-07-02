package musecom.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.MemberDDL;
import board.MembersDTO;

/**
 * Servlet implementation class IdCheck
 */
@WebServlet("/IdCheck")
public class IdCheck extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		MemberDDL ddl = new MemberDDL();
		
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("UTF-8");
		Vector<MembersDTO> rs = ddl.getUserId();
		int size = rs.size();
		String str = "";
		for(int i=0;i<size;i++)
		{
			MembersDTO dt = (MembersDTO) rs.elementAt(i);
			str+=dt.getUserid()+" ";
		}
		out.print(str);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
