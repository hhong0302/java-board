package musecom.net;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.MemberDDL;
import board.MembersDTO;

public class MembersOk extends HttpServlet {  //회원가입


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		MembersDTO dto = new MembersDTO();
		MemberDDL ddl = new MemberDDL();
		String url = null;
		
		//업로드 경로
		String dir = req.getSession().getServletContext().getRealPath("/upload/users");
		
		//업로드 파일용량 설정 - 최대 10MB
		int max = 10 * 1024 * 1024;

		//System.out.println("포스트코드" + req.getParameter("postcode"));
		MultipartRequest m = new MultipartRequest(req,dir,max,"UTF-8",
		new DefaultFileRenamePolicy());
		
		try
		{
			
			dto.setUserid(m.getParameter("userid"));
			dto.setUserpass(m.getParameter("userpass"));
			dto.setUsername(m.getParameter("username"));
			dto.setUseremail(m.getParameter("useremail"));
			dto.setPostcode(Integer.parseInt(m.getParameter("postcode")));
			dto.setAddr(m.getParameter("addr"));
			dto.setDetailaddr(m.getParameter("detailaddr"));
			dto.setTel(m.getParameter("tel"));
			dto.setUip();
			dto.setPhoto(m.getFilesystemName("photo"));
		}
		catch(Exception e){}
		
		boolean isSuccess = ddl.insert(dto);
		int intSuccess = ddl.checkLogin(dto);
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		req.setCharacterEncoding("UTF-8");
		String script = null;
		if(isSuccess)
		{
			System.out.println("insert success");
			url = "index.jsp";
			HttpSession session = req.getSession();
			
			//접속한 아이디의 회원이름 가져옴
			String helloName = ddl.HelloName(dto);  //로그인 한 정보에서 username 값 리턴
			
			session.setAttribute("user", m.getParameter("userid"));  //세션에 id를 user로 저장
			session.setAttribute("username", helloName);  //세션에 이름을 username으로 저장
			session.setAttribute("level", intSuccess);  //관리자면 회원관리도 뜨게 하기 위해서
			script = "<script>alert('회원가입이 완료되었습니다.');"
					+"document.location.href='/board';"
					+"</script>";
		}
		else
		{
			System.out.println("insert failed");
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
