package jsp;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/memo")
public class MemoCtrl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		String memo = "";

		Cookie[] cookies = request.getCookies();
		// cookies �� null ó��
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("memo")) {
					memo = cookies[i].getValue();
				}
			}
		}

		if (writer != null && content != null && !writer.equals(" ") && !content.equals(" ")) {
			// ��Ű�� ����Ǵ� �� �����
			System.out.println(writer);
			System.out.println(content);
			// �ۼ��� �� ���뿡 �����̽� ��, ���� Ű�� ���� ��
			content = content.replace(" ", "-");
			content = content.replace("\r\n", "<br>");
			writer = writer.replace(" ", "-");
			writer = writer.replace("\r\n", "<br>");

			/** memo �� �����̸� �ۼ��ڿ� ������ ���� **/
			if (memo.equals(" ")) {
				memo = writer + "!" + content;
				/**
				 * memo �� ������ �ƴϸ� ��ü�� ���� �� �ְ� *�� �����ϰ� �������� �� ���� �ֽ����� ���̰� �ڿ� memo �� ����
				 **/
			} else {
				memo = writer+ "!" + content + "*" + memo;
			}
//		 	�� ������ �ڵ�
//		  	memo =  memo.equals("") ? writer+"!"+content : writer+"!"+content+"*"+memo;

			Cookie mCookie = new Cookie("memo", memo);
//			mCookie.setMaxAge(60);
			response.addCookie(mCookie);
			System.out.println(memo + "ddd");
		}
		// arraylist Ȱ��
//		ArrayList<String> firArr = new ArrayList<>();
//		ArrayList<String> secArr = new ArrayList<>();
		ArrayList<Book> bArr = new ArrayList<Book>();
		// memo �ȿ� !�� �����ϴ°� � ������ �������
		if (memo.contains("!")) {
			String[] totalArr = memo.split("\\*");
			for (int i = 0; i < totalArr.length; i++) {
				String[] devArr = totalArr[i].split("!");
				devArr[0] = devArr[0].replace("-", " ");
//				firArr.add(devArr[0]);
				System.out.println(devArr[0] + "##2");
				System.out.println(devArr[1] + "@@1");
				devArr[1] = devArr[1].replace("-", " ");
//				secArr.add(devArr[1]);
				System.out.println(devArr[1] + "@@2");
				bArr.add(new Book(devArr[0],devArr[1]));
				System.out.println(bArr.get(i).getWriter()+" test!");
			}

		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("MemoCtrl.jsp");
		request.setAttribute("bArr", bArr);
		dispatcher.forward(request, response);
	}
}
