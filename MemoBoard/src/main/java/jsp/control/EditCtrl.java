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
//���� �޾ƿ��� , ������ ���� ������̼� (����)
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		//�� ������ �ִ� �뷮
		maxFileSize = 1024 * 1024 * 10,
		//��ü ������ ���� �뷮 ����
		maxRequestSize = 1024 * 1024 * 10 * 5
		)
@WebServlet("/edit")
public class EditCtrl extends HttpServlet{
	//bidx �޾Ƽ� ������ ��� ������ �̷����� ȭ������ ����
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bidx = request.getParameter("bidx");
		System.out.println("edit : "+bidx);
		
		BoardService boardService = new BoardService();
		request.setAttribute("b", boardService.getBoardData(bidx));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");		
		dispatcher.forward(request, response);
	}
	//�޾ƿ� �����͵�� �������� ���� ó���ϴ� �� 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int bidx = Integer.parseInt(String.valueOf(request.getParameter("bidx")));
		
		System.out.println("edit post :"+bidx+","+title+","+content);
		
		String files = "";
		
		//���� ������ �޾ƿ���
		Collection<Part> parts = request.getParts();
		for (Part p : parts) {
			//file �ϳ� �޾ƿ��� 
//		Part p = request.getPart("myFile");
			//file �޾ƿ� �� �׽�Ʈ ÷�������� ����� �޾� �Դ����� ���� ÷�� ���� ���� �ø� ��
			if (p.getName().equals("myFile") && p.getSize() > 0) {
				
				String path = request.getServletContext().getRealPath("up");
				String fileName = p.getSubmittedFileName(); //���� �̸� �ߺ� �ذ��ؾ� ��
				files += fileName  + ",";
//				System.out.println("fileName :"+fileName); 
				System.out.println("path :"+path);
				//������ ��� ��
				InputStream inputStream = p.getInputStream();
				File tempFile = new File(path);
				//������ ���� ���� üũ , ���� ���� �ʴ´ٸ� ���丮 ���� �����
				if(!tempFile.exists()) {
					tempFile.mkdirs();
				}
				//������ ������ �� �ü�� ���� ���� �����ڰ� �ٸ��⿡ File.separator �� ��
				FileOutputStream fileOutputStream = new FileOutputStream(path + File.separator +fileName);
				int d = inputStream.read();
				System.out.println("input d :"+d);
				
				int size = 0;
				byte [] buffer = new byte [1024];
				// -1 �� ������ �� �����԰ų� ������ ���� ���
				while ((size = inputStream.read(buffer)) != -1) {
					//������ ������ �� �뷮 ó������ ������ ���ǵ��� �ʰ� �������� ���ؼ�
					fileOutputStream.write(buffer, 0 , size);
					
				}
//				System.out.println("size :"+size);
				
				inputStream.close();
				fileOutputStream.close();
			}
		}
		
		if(files.length() > 1) {
			//���� �̸� 0 (ó������) files.length()-1 (, �������� ���ϸ�) �� �����ϱ� ���� 
			files = files.substring(0 , files.length()-1);
		}
		
		System.out.println("files name : "+files);
		BoardService boardService = new BoardService();
		boardService.editContent(title, content, files, bidx);
		response.sendRedirect("board");
	}
}
