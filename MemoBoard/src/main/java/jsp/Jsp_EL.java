package jsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsp_EL")
public class Jsp_EL extends HttpServlet{
	

	@Override
	protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", "«„¡§ºÆ");
		map.put("age", 229);
		
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("a");
		arr.add("b");
		arr.add("c");
		
		String str = "gjwjdtjr!";
		
		
		RequestDispatcher dispatcher = requset.getRequestDispatcher("jsp_EL2.jsp");
		requset.setAttribute("str", str);
		requset.setAttribute("arr", arr);
		requset.setAttribute("map", map);
		// > gt , < lt, >= ge, <= le 
		dispatcher.forward(requset, response);
		
		
	}
}	
