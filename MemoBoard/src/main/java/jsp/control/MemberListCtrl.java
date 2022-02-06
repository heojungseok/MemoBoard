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
//jsp 파일에서 a태그로 이동된 것이기에 doGet으로 받아옴
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession mSession = request.getSession();
		RequestDispatcher dispatcher = null;
		//로그인 처리된 상태 
		if(mSession.getAttribute("midx") != null) {
			String mode = request.getParameter("mode");
			MemberService memberService = new MemberService();
			//모드(블랙, 탈퇴)의 상태에 체크가 되지 않았을 때
			if(mode == null) {
				
				int myIdx = Integer.parseInt(String.valueOf(mSession.getAttribute("midx")));
				
				int page = request.getParameter("p") == null ? 
						0 : Integer.parseInt(String.valueOf(request.getParameter("p")));	
				
				request.setAttribute("memberList", memberService.getList(page));
				request.setAttribute("total", memberService.getTotalCount());			
				dispatcher = request.getRequestDispatcher("memberList.jsp");
				dispatcher.forward(request, response);
				
			//모드 상태에 체크 됐을 경우
			}else {
				System.out.println(mode);
				String modeBtn = request.getParameter("modeBtn");
				String [] blacks = request.getParameterValues("blacks");
				for(int i = 0; i < blacks.length; i++) {
					
					System.out.println(modeBtn+blacks[i]);
					
				}
				
			}
		//로그인 처리 안된 경우	
		}else {
			dispatcher = request.getRequestDispatcher("member_ok.jsp");		
			dispatcher.forward(request, response);
		}
		
		
	}

}
