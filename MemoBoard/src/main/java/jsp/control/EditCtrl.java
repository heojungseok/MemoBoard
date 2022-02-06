package jsp.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import jsp.service.BoardService;
//파일 받아오고 , 전송을 위한 어노테이션 (맵핑)
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		//한 파일의 최대 용량
		maxFileSize = 1024 * 1024 * 10,
		//전체 파일의 갯수 용량 제한
		maxRequestSize = 1024 * 1024 * 10 * 5
		)
@WebServlet("/edit")
public class EditCtrl extends HttpServlet{
	//bidx 받아서 데이터 담고 수정이 이뤄지는 화면으로 가게
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bidx = request.getParameter("bidx");
		System.out.println("edit : "+bidx);
		
		BoardService boardService = new BoardService();
		request.setAttribute("b", boardService.getBoardData(bidx));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");		
		dispatcher.forward(request, response);
	}
	//받아온 데이터들로 실질적인 서비스 처리하는 곳 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int bidx = Integer.parseInt(String.valueOf(request.getParameter("bidx")));
		
		System.out.println("edit post :"+bidx+","+title+","+content);
		
		String files = "";
		
		//파일 여러개 받아오기
		Collection<Part> parts = request.getParts();
		for (Part p : parts) {
			//file 하나 받아오기 
//		Part p = request.getPart("myFile");
			//file 받아온 거 테스트 첨부파일이 제대로 받아 왔는지와 파일 첨부 없이 글을 올릴 때
			if (p.getName().equals("myFile") && p.getSize() > 0) {
				
				String path = request.getServletContext().getRealPath("up");
				String fileName = p.getSubmittedFileName(); //파일 이름 중복 해결해야 함
				files += fileName  + ",";
//				System.out.println("fileName :"+fileName); 
				System.out.println("path :"+path);
				//파일을 담는 것
				InputStream inputStream = p.getInputStream();
				File tempFile = new File(path);
				//폴더의 존재 유무 체크 , 존재 하지 않는다면 디렉토리 까지 만들기
				if(!tempFile.exists()) {
					tempFile.mkdirs();
				}
				//파일을 가져올 때 운영체제 마다 파일 구분자가 다르기에 File.separator 를 씀
				FileOutputStream fileOutputStream = new FileOutputStream(path + File.separator +fileName);
				int d = inputStream.read();
				System.out.println("input d :"+d);
				
				int size = 0;
				byte [] buffer = new byte [1024];
				// -1 은 파일을 다 가져왔거나 파일이 없는 경우
				while ((size = inputStream.read(buffer)) != -1) {
					//파일을 가져올 때 용량 처음부터 끝까지 유실되지 않고 가져오기 위해서
					fileOutputStream.write(buffer, 0 , size);
					
				}
//				System.out.println("size :"+size);
				
				inputStream.close();
				fileOutputStream.close();
			}
		}
		
		if(files.length() > 1) {
			//파일 이름 0 (처음부터) files.length()-1 (, 전까지의 파일명) 를 저장하기 위해 
			files = files.substring(0 , files.length()-1);
		}
		
		System.out.println("files name : "+files);
		BoardService boardService = new BoardService();
		boardService.editContent(title, content, files, bidx);
		response.sendRedirect("board");
	}
}
