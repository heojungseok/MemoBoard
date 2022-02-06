package jsp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import jsp.model.MemberData;

public class MemberService {
	
	private String dbID ="MEGA";
	private String dbPW ="1234";
	private String url ="jdbc:oracle:thin:@localhost:1521/xepdb1";
	private String driver ="oracle.jdbc.driver.OracleDriver";
	//블랙리스트 가져오기 
	
	//멤버 총 인원 수 불러오기
	public int getTotalCount() {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM MEMO_MEMBER";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();			

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {	
				totalCount = rs.getInt(1);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalCount;
	}
	
	//멤버 목록 불러오기 BoardService 에서 getBoardList 코드 가져와서 맞게 수정
	public ArrayList<MemberData> getList(int page){
		ArrayList<MemberData> memArr = new ArrayList<MemberData>();		
		int sContent = page * 10 + 1;
		int eContent = sContent + 9;
		
		String sql = "SELECT t.* FROM "
				+ "    ( "
				+ "    SELECT ROWNUM rnum, t2.* FROM "
				+ "        ("
				+ "        SELECT * FROM MEMO_MEMBER ORDER BY midx DESC"
				+ "        ) t2"
				+ "    ) t "
				+ "    WHERE rnum BETWEEN "+sContent+" AND "+eContent;
		
//		System.out.println(sContent + ", "+eContent);
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();			

			ResultSet rs = st.executeQuery(sql);			
			while (rs.next()) {
				int midx = rs.getInt("midx");
				String userid = rs.getString("userid");				
				String username = rs.getString("username");
				String age = rs.getString("age");
				String email = rs.getString("email");
				String address = rs.getString("address");
				int stat = rs.getInt("stat");
				Date wdate = rs.getDate("wdate");
				
				MemberData tempMember = new MemberData(midx, userid, userid, email, username, address, age, stat, wdate);				
				memArr.add(tempMember);
			}	
			rs.close();
			st.close();
			con.close();			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return memArr;
	}
	//midx 값과 username 담기
	public int getIdx(String userid) {
		
		int getIdx = 0;
		
		String sql = "";
		sql = "SELECT * FROM MEMO_MEMBER WHERE userid = " + "'" + userid + "'";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int midx = rs.getInt("midx");
				getIdx = midx;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return getIdx;
	}
	//회원 정보 저장
	public void insertMemberInfo(String userid, String passwd, String email, String username, String address) {
		
		String sql = "";
		sql = "INSERT INTO MEMO_MEMBER (userid, passwd, email, username, address, wdate) VALUES (" 
		+"'"+userid+"'"+",'"+passwd+"'"+",'"+email+"'"+",'"+username+"'"+",'"+address+"',SYSDATE)";
	
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			
			int result = st.executeUpdate(sql);
			System.out.println(result+"-member_ok"); 
		

			System.out.println("insertMemService : "+result);
			
			con.close();
			st.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//로그인 확인
	public int memberChk (String userid, String passwd ) {
		
		int memberChk = 0;
				
		String sql = "";
		sql = "SELECT * FROM MEMO_MEMBER WHERE userid = " + "'" + userid + "'";
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			boolean isLogin = false;
			while (rs.next()) {
			if(passwd.equals(rs.getString("passwd"))){
			isLogin = true;
			int midx = rs.getInt("midx");
			String username = rs.getString("username");
		
			}else {
				System.out.println("비밀번호 불일치");
			}
			System.out.println(rs.getString("userid")+","+rs.getString("passwd")+","+rs.getInt("midx"));

			}
			if(isLogin){
				memberChk = 1;
				System.out.println("로그인");
				
//				response.sendRedirect("board");
			}else{
				memberChk = -1;
				System.out.println("로그인 실패");
//				response.sendRedirect("member_ok.jsp");
			}

			con.close();
			st.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memberChk;
		
	}
}
