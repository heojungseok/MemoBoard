package jsp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/PracMemberOkCtl")
public class PracMemberOkCtl extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		String phone = request.getParameter("phone");
		String nickname = request.getParameter("nickname");
		
		String[] chk = request.getParameterValues("chk");
		
		System.out.println("prac id: "+userid);
		System.out.println("prac pw: "+passwd);
		System.out.println("prac phone: "+phone);
		System.out.println("prac nick: "+nickname);
		
		for (int i = 0; i < chk.length; i++) {
			
			System.out.println("prac ch: "+chk[i]);
		}
		
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("prac_member_ok.jsp");
		request.setAttribute("userid", userid);
		dispatcher.forward(request, response);
		
	}

}
