package jsp.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.service.MemberService;
@WebServlet("/memberInfo")
public class MemberInfoCtl extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String address = request.getParameter("address");
		
		System.out.println("memctrl test: "+userid+","+passwd+","+email+","+username+","+address);
		
		MemberService memberService = new MemberService();
		memberService.insertMemberInfo(userid, passwd, email, username, address);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member_ok.jsp");
		dispatcher.forward(request, response);
		
//		response.sendRedirect("login.jsp");
	}
	
}
