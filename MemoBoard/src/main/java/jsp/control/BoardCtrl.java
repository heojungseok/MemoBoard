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

//board�� ����
@WebServlet("/board")
public class BoardCtrl extends HttpServlet {
//board.jsp �� ��ȯ�ϱ� ���� �����͸� �ѹ� �� ������ �ʿ䰡 ���� �� ����ϴ� �뵵
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession mSession = request.getSession();
//		System.out.println("board���� �޾ƿ� ��:"+mSession.getAttribute("midx"));
		
		if (mSession.getAttribute("midx")!=null) {
			int curIdx = Integer.parseInt(String.valueOf(mSession.getAttribute("midx")));
			BoardService boardService = new BoardService();
			//page �� �޾ƿ��� ������ ���� ���� ��� �ڵ� (ù ȭ�� null ���) 
			int page = request.getParameter("p") == null ? 
					0 : Integer.parseInt(String.valueOf(request.getParameter("p")));

			request.setAttribute("curUser", mSession.getAttribute("username"));
			request.setAttribute("board", boardService.getBoardList(curIdx, page));
			request.setAttribute("total", boardService.getCountContent());
//			request.setAttribute("totalComment", boardService.getCountComment());
			//Ư�� �������� �̵�
			RequestDispatcher dispatcher = request.getRequestDispatcher("board.jsp");
			dispatcher.forward(request, response);
			
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member_ok.jsp");
			dispatcher.forward(request, response);
		}
		
//		//�Խñ� ���� �κ�
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
//		//class ���Ͽ��� jsp ��ɾ� ����ϴ� ���
////		PrintWriter printWriter = response.getWriter();
////		printWriter.println("<script type='text/javascript'>");
////		printWriter.println("alert('���� �Ϸ�');");
////		printWriter.println("location.href= 'board?p='"+page);
////		printWriter.println("</script>");
////		printWriter.flush();
////		
////		response.sendRedirect("board?p=" + (page));
//		//�����͸� jsp�� ������ ���ؼ��� dispatcher ���
//		request.setAttribute("page", page);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("delete.jsp");
//		dispatcher.include(request, response);
////		response.sendRedirect("delete.jsp");
//		}
	}
	//�α��� â���� �ٷ� �Ѿ�� �� �ִ� �޼ҵ�
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession mSession = request.getSession();
		
		System.out.println("loginOk ���� �޾ƿ� ��:"+mSession.getAttribute("midx"));
		if (mSession.getAttribute("midx")!=null) {
			int curIdx = Integer.parseInt(String.valueOf(mSession.getAttribute("midx")));
			BoardService boardService = new BoardService();
			//page �� �޾ƿ��� ������ ���� ���� ��� �ڵ� (ù ȭ�� null ���) 
			int page = request.getParameter("p") == null ? 
					0 : Integer.parseInt(String.valueOf(request.getParameter("p")));
			
			request.setAttribute("curUser", mSession.getAttribute("username"));
			request.setAttribute("board", boardService.getBoardList(curIdx, page));
			request.setAttribute("total", boardService.getCountContent());
			//Ư�� �������� �̵�
			RequestDispatcher dispatcher = request.getRequestDispatcher("board.jsp");
			dispatcher.forward(request, response);
			
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("member_ok.jsp");
			dispatcher.forward(request, response);
		}
	
	}
	

}