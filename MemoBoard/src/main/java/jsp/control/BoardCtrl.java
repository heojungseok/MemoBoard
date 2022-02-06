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

import jsp.model.BoardData;
import jsp.service.BoardService;

//board로 맵핑
@WebServlet("/board")
public class BoardCtrl extends HttpServlet {
//board.jsp 로 반환하기 전에 데이터를 한번 더 가공할 필요가 있을 때 사용하는 용도
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession mSession = request.getSession();
//		System.out.println("board에서 받아온 값:"+mSession.getAttribute("midx"));
		
		if (mSession.getAttribute("midx")!=null) {
			int curIdx = Integer.parseInt(String.valueOf(mSession.getAttribute("midx")));
			BoardService boardService = new BoardService();
			//page 를 받아오지 못했을 때를 위한 대비 코드 (첫 화면 null 대비) 
			int page = request.getParameter("p") == null ? 
					0 : Integer.parseInt(String.valueOf(request.getParameter("p")));

			request.setAttribute("curUser", mSession.getAttribute("username"));
			request.setAttribute("board", boardService.getBoardList(curIdx, page));
			request.setAttribute("total", boardService.getCountContent());
//			request.setAttribute("totalComment", boardService.getCountComment());
			//특정 페이지로 이동
			RequestDispatcher dispatcher = request.getRequestDispatcher("board.jsp");
			dispatcher.forward(request, response);
			
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member_ok.jsp");
			dispatcher.forward(request, response);
		}
		
//		//게시글 삭제 부분
//		if(request.getParameter("idx") != null) {
//			
//		String pageNbidx = request.getParameter("idx");
//		String[] idxArr = pageNbidx.split(",");
//
//		int page = Integer.parseInt(idxArr[0]);
//		int bidx = Integer.parseInt(idxArr[1]);
//		
//		System.out.println("curPage : " + page + " bidx : " + bidx);
//
//
//		BoardService boardService = new BoardService();
//		boardService.deleteContents(bidx);
//		//class 파일에서 jsp 명령어 사용하는 방식
////		PrintWriter printWriter = response.getWriter();
////		printWriter.println("<script type='text/javascript'>");
////		printWriter.println("alert('삭제 완료');");
////		printWriter.println("location.href= 'board?p='"+page);
////		printWriter.println("</script>");
////		printWriter.flush();
////		
////		response.sendRedirect("board?p=" + (page));
//		//데이터를 jsp에 보내기 위해서는 dispatcher 사용
//		request.setAttribute("page", page);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("delete.jsp");
//		dispatcher.include(request, response);
////		response.sendRedirect("delete.jsp");
//		}
	}
	//로그인 창에서 바로 넘어올 수 있는 메소드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession mSession = request.getSession();
		
		System.out.println("loginOk 에서 받아온 값:"+mSession.getAttribute("midx"));
		if (mSession.getAttribute("midx")!=null) {
			int curIdx = Integer.parseInt(String.valueOf(mSession.getAttribute("midx")));
			BoardService boardService = new BoardService();
			//page 를 받아오지 못했을 때를 위한 대비 코드 (첫 화면 null 대비) 
			int page = request.getParameter("p") == null ? 
					0 : Integer.parseInt(String.valueOf(request.getParameter("p")));
			
			request.setAttribute("curUser", mSession.getAttribute("username"));
			request.setAttribute("board", boardService.getBoardList(curIdx, page));
			request.setAttribute("total", boardService.getCountContent());
			//특정 페이지로 이동
			RequestDispatcher dispatcher = request.getRequestDispatcher("board.jsp");
			dispatcher.forward(request, response);
			
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member_ok.jsp");
			dispatcher.forward(request, response);
		}
	
	}
	

}