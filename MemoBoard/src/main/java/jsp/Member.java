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

@WebServlet("/member")
public class Member extends HttpServlet{
	
//	response.setCharacterEncoding("utf-8");
//	response.setContentType("text/html; charset=utf-8");
	@Override
	protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = requset.getParameter("userId");
		String userPw = requset.getParameter("userPw");
		String userNick = requset.getParameter("userNick");
		
		
		System.out.println("아이디:"+userId + "비밀번호:"+ userPw + "닉네임:"+ userNick);
		
		String userInfo = "";

		
		Cookie [] cookies = requset.getCookies();
		// cookies 의 null 처리
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("userInfo")) {
					userInfo = cookies[i].getValue();
				}
			}
		}
		
		if (userId != null && userPw != null && userNick != null && !userId.equals(" ") && !userPw.equals(" ") && !userNick.equals(" ")) {
			//띄어쓰기 변환
			userId = userId.replace(" ", "-");
			userPw = userPw.replace(" ", "-");
			userNick = userNick.replace(" ", "-");
			
			if(userInfo.equals(" ")) {
				userInfo = userId + "!" + userPw + "!" + userNick;
			}else {
				userInfo = userId + "!" + userPw + "!" + userNick + "*" + userInfo;
			}
			
			Cookie memCookie = new Cookie("userInfo", userInfo);
//			memCookie.setMaxAge(60);
			response.addCookie(memCookie);
			System.out.println(userInfo + ": test");
		}
		
		ArrayList<MemberInfo> infoArr = new ArrayList<MemberInfo>();
		if (userInfo.contains("!")) {
			String [] totalArr = userInfo.split("\\*");
			for (int i = 0; i < totalArr.length; i++) {
				String [] userArr = totalArr[i].split("!");
				userArr[0] = userArr[0].replace("-", " ");
				userArr[1] = userArr[1].replace("-", " ");
				userArr[2] = userArr[2].replace("-", " ");
				System.out.println(" ID : "+userArr[0]+" PW : "+userArr[1]+" NICk : "+userArr[2]);
				infoArr.add(new MemberInfo(userArr[0],userArr[1],userArr[2]));
			}
		}
		if(!infoArr.isEmpty()) {
			for (int i = 0; i < infoArr.size(); i++) {
				System.out.println(infoArr.get(i).getUserNick());
			}
		}else {
			System.out.println("empty");
		}
		System.out.println();
		RequestDispatcher dispatcher = requset.getRequestDispatcher("Member.jsp");
		requset.setAttribute("infoArr", infoArr );
		dispatcher.forward(requset, response);
		
	}
}	
