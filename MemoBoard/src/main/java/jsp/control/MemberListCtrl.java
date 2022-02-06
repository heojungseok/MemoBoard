package jsp.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.service.BoardService;
import jsp.service.MemberService;

@WebServlet("/member_list")
public class MemberListCtrl extends HttpServlet {
//jsp ���Ͽ��� a�±׷� �̵��� ���̱⿡ doGet���� �޾ƿ�
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession mSession = request.getSession();
		RequestDispatcher dispatcher = null;
		//�α��� ó���� ���� 
		if(mSession.getAttribute("midx") != null) {
			String mode = request.getParameter("mode");
			MemberService memberService = new MemberService();
			//���(��, Ż��)�� ���¿� üũ�� ���� �ʾ��� ��
			if(mode == null) {
				
				int myIdx = Integer.parseInt(String.valueOf(mSession.getAttribute("midx")));
				
				int page = request.getParameter("p") == null ? 
						0 : Integer.parseInt(String.valueOf(request.getParameter("p")));	
				
				request.setAttribute("memberList", memberService.getList(page));
				request.setAttribute("total", memberService.getTotalCount());			
				dispatcher = request.getRequestDispatcher("memberList.jsp");
				dispatcher.forward(request, response);
				
			//��� ���¿� üũ ���� ���
			}else {
				System.out.println(mode);
				String modeBtn = request.getParameter("modeBtn");
				String [] blacks = request.getParameterValues("blacks");
				for(int i = 0; i < blacks.length; i++) {
					
					System.out.println(modeBtn+blacks[i]);
					
				}
				
			}
		//�α��� ó�� �ȵ� ���	
		}else {
			dispatcher = request.getRequestDispatcher("member_ok.jsp");		
			dispatcher.forward(request, response);
		}
		
		
	}

}
