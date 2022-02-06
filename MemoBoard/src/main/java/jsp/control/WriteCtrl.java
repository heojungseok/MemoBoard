package jsp.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import jsp.service.BoardService;
//���� ������ ���� ������̼� (����)
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024,
		//�� ������ �ִ� �뷮
		maxFileSize = 1024 * 1024 * 10,
		//��ü ������ ���� �뷮 ����
		maxRequestSize = 1024 * 1024 * 10 * 5
		)
@WebServlet("/writeOk")
public class WriteCtrl extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String files = "";
		
//		System.out.println("title : "+title);
//		System.out.println("content : "+content);
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
		
		HttpSession mySession = request.getSession();
		String username = String.valueOf(mySession.getAttribute("username"));
		int midx = Integer.parseInt(String.valueOf(mySession.getAttribute("midx")));
		
//		System.out.println(username);
//		System.out.println(midx);
		//BoardService ��ü ����
		BoardService boardService = new BoardService();
		//������ ����
		boardService.insertContent(title, content, files, midx);
//		
//		for (int i = 0; i < 15; i++) {
//			boardService.insertContent(title+i, content+i, files, midx);
//		}
//		
		response.sendRedirect("board");
		
	}
}