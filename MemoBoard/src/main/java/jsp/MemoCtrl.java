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
		// cookies 의 null 처리
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("memo")) {
					memo = cookies[i].getValue();
				}
			}
		}

		if (writer != null && content != null && !writer.equals(" ") && !content.equals(" ")) {
			// 쿠키와 저장되는 곳 만들기
			System.out.println(writer);
			System.out.println(content);
			// 작성자 및 내용에 스페이스 바, 엔터 키를 위한 곳
			content = content.replace(" ", "-");
			content = content.replace("\r\n", "<br>");
			writer = writer.replace(" ", "-");
			writer = writer.replace("\r\n", "<br>");

			/** memo 가 공백이면 작성자와 내용을 넣음 **/
			if (memo.equals(" ")) {
				memo = writer + "!" + content;
				/**
				 * memo 가 공백이 아니면 전체를 끊을 수 있게 *를 포함하고 마지막에 쓴 글이 최신으로 보이게 뒤에 memo 를 붙임
				 **/
			} else {
				memo = writer+ "!" + content + "*" + memo;
			}
//		 	더 간단한 코드
//		  	memo =  memo.equals("") ? writer+"!"+content : writer+"!"+content+"*"+memo;

			Cookie mCookie = new Cookie("memo", memo);
//			mCookie.setMaxAge(60);
			response.addCookie(mCookie);
			System.out.println(memo + "ddd");
		}
		// arraylist 활용
//		ArrayList<String> firArr = new ArrayList<>();
//		ArrayList<String> secArr = new ArrayList<>();
		ArrayList<Book> bArr = new ArrayList<Book>();
		// memo 안에 !를 포함하는건 어떤 내용이 들어있음
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
