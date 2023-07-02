package musecom.net;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.MemberDDL;
import board.MembersDTO;

public class MembersLogin extends HttpServlet {  //로그인

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		MembersDTO dto = new MembersDTO();
		MemberDDL ddl = new MemberDDL();
		String url = null;
		
		//MembersDTO에 setter로 파라미터 정보를 보낸 후 db에서 getter로 받을 수 있도록 함
		dto.setUserid(req.getParameter("userid"));
		dto.setUserpass(req.getParameter("userpass"));
		
		//MemberDDL의 매개변수로 MembersDTO를 받아 getter를 통해 select 해서 조사.
		//결과를 true / false 로 출력
		int isSuccess = ddl.checkLogin(dto);  //로그인 한 정보에서 level 값을 가져옴(checkLogin return)
		if(isSuccess>0)  //등록되어있는 회원이면서 탈퇴 회원이 아닌 경우
		{
			//session 등록
			url = "index.jsp";
			HttpSession session = req.getSession();
			
			//접속한 아이디의 회원이름 가져옴
			String helloName = ddl.HelloName(dto);  //로그인 한 정보에서 username 값 리턴
			
			session.setAttribute("user", req.getParameter("userid"));  //세션에 id를 user로 저장
			session.setAttribute("username", helloName);  //세션에 이름을 username으로 저장
			session.setAttribute("level", isSuccess);  //관리자면 회원관리도 뜨게 하기 위해서
			res.sendRedirect(url);  //성공 시 index로 이동
		}
		
		else  //등록되어있지 않거나 탈퇴 회원인 경우
		{
			url="?fname=login";  //다시 로그인 하도록 url 설정
			req.setAttribute("message", "아이디 또는 비밀번호를 확인하세요.");
			//forward 를 통해 실패 변수를 전달시킨다.
			req.getRequestDispatcher(url).forward(req, res);
		}
		
		doGet(req, res);
	}

}
