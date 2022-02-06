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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.model.BoardData;
import jsp.service.BoardService;

@WebServlet("/detail")
public class DetailCtrl extends HttpServlet {

	//board.jsp 에서 get 방식으로 데이터를 받아오기 때문에 사용
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idx = String.valueOf(request.getParameter("bidx"));
		int bidx = Integer.parseInt(idx);
		
		if(bidx != -1) {
			
			BoardService boardService = new BoardService();
			request.setAttribute("b", boardService.getBoardData(idx));
			request.setAttribute("cmt", boardService.getCommentList(bidx));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("detail.jsp");		
			dispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("board");		
			dispatcher.forward(request, response);
		}

		
		
	}
	//댓글 데이터 받아와서 처리하는 부분
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		HttpSession mySession = request.getSession();
		int midx = Integer.parseInt(String.valueOf(mySession.getAttribute("midx")));
		String cmt = request.getParameter("cmt");
		int bidx = Integer.parseInt(String.valueOf(request.getParameter("bidx")));
		
		System.out.println("cmt CTRL :"+cmt+","+midx+","+bidx);
		
		BoardService boardService = new BoardService();
		boardService.insertComent(cmt, midx, bidx);
		
		HttpSession boardSession = request.getSession();
		boardSession.setAttribute("bidx", bidx);
		
		response.sendRedirect("detail?bidx="+bidx);
	
	}

	


}