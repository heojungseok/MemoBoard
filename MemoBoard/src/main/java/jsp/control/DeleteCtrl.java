package jsp.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jsp.service.BoardService;

@WebServlet("/delete")
public class DeleteCtrl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardService boardService = new BoardService();
		
		if(request.getParameter("idx") != null) {
			
			String pageNbidx = request.getParameter("idx");
			String[] idxArr = pageNbidx.split(",");

			int page = Integer.parseInt(idxArr[0]);
			int bidx = Integer.parseInt(idxArr[1]);
			
			System.out.println("curPage : " + page + " bidx : " + bidx);


			boardService.deleteContents(bidx);
			//class 파일에서 jsp 명령어 사용하는 방식
//			PrintWriter printWriter = response.getWriter();
//			printWriter.println("<script type='text/javascript'>");
//			printWriter.println("alert('삭제 완료');");
//			printWriter.println("location.href= 'board?p='"+page);
//			printWriter.println("</script>");
//			printWriter.flush();
//			
//			response.sendRedirect("board?p=" + (page));
			//데이터를 jsp에 보내기 위해서는 dispatcher 사용
			request.setAttribute("page", page);
			RequestDispatcher dispatcher = request.getRequestDispatcher("delete.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("delete.jsp");

		}
		
		String cidxNbidx = request.getParameter("cidxNbidx");
		System.out.println(cidxNbidx);
		String[] idxArr = cidxNbidx.split(",");
		int cidx = Integer.parseInt(idxArr[0]);
		int bidx = Integer.parseInt(idxArr[1]);
		
		boardService.deleteComment(cidx,bidx);
		request.setAttribute("bidx", bidx);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("delete.jsp");
//		dispatcher.forward(request, response);
		response.sendRedirect("detail?bidx="+bidx);
		
	}
}
