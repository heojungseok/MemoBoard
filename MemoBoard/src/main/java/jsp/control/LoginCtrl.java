package jsp.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.service.MemberService;
@WebServlet("/loginOk")
public class LoginCtrl extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		
		MemberService memberService = new MemberService();
		System.out.println("member_ok.jps 에서 midx 받아온 값:"+memberService.getIdx(userid));
	
		System.out.println("loginOk: "+userid+","+passwd);
		
		System.out.println("memberService :"+memberService.memberChk(userid, passwd));
		
		if(memberService.memberChk(userid, passwd) == 1) {
			
			String midx = String.valueOf(memberService.getIdx(userid));
			HttpSession mSession = request.getSession();
			mSession.setAttribute("midx", midx);
			
			response.sendRedirect("board");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("board");
//			dispatcher.forward(request, response);
			
		}else {
			response.sendRedirect("login.jsp");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
//			dispatcher.forward(request, response);
		}
		
	}
}
