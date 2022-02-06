package jsp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import jsp.model.BoardData;

public class BoardService {

	private String dbID = "MEGA";
	private String dbPW = "1234";
	private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	
	//게시글 수정
	public void editContent(String title, String content, String files, int bidx) {
		
		String sql = "UPDATE memo_board b "
				+ "set b.title = '"+title+"', b.content = '"+content+"' , b.files = '"+files+"' WHERE b.bidx ="+ bidx;
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			int result = st.executeUpdate(sql);
			
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
	//게시글 삭제
	public void deleteContents(int bidx) {
				
		String sql = "DELETE FROM memo_board WHERE bidx =" + bidx;
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			int result = st.executeUpdate(sql);
			
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
	//댓글 삭제
			public void deleteComment(int cidx, int bidx) {
						
				String sql = "delete from "
						+ "(select * from memo_comment c where c.c_bidx = " +bidx+ ")v "
						+ "where v.cidx = " + cidx;
				
				try {
					Class.forName(driver);
					Connection con = DriverManager.getConnection(url, dbID, dbPW);
					Statement st = con.createStatement();
					int result = st.executeUpdate(sql);
					
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
	
	//댓글 내용 보여지기
	public ArrayList<BoardData> getCommentList(int curbIdx) {
		ArrayList<BoardData> cmtArr = new ArrayList<BoardData>();

		// sql문을 JOIN 통해서 하나로 만들기
		String sql2 = "SELECT memo_member.username, t.c_bidx, t.cmt, t.b_midx FROM "
				+ "    ("
				+ "        SELECT memo_comment.cmt, memo_comment.c_bidx, memo_board.b_midx FROM memo_board RIGHT OUTER JOIN memo_comment "
				+ "        ON memo_comment.c_midx = memo_board.b_midx "
				+ "    )t RIGHT OUTER JOIN memo_member ON t.b_midx = memo_member.midx";
		
		sql2 = "SELECT memo_member.username, t.c_bidx, t.cmt, t.c_midx, t.cidx FROM "
				+ "       ("
				+ "            SELECT * FROM memo_board RIGHT OUTER JOIN memo_comment "
				+ "            ON memo_comment.c_bidx = memo_board.bidx "
				+ "        )t RIGHT OUTER JOIN memo_member ON t.c_midx = memo_member.midx "
				+ "			 WHERE t.c_bidx ="+curbIdx;		

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(sql2);

			BoardData bData = null;

			while (rs.next()) {
				// model(BoardData)에서 만든 생성자에 관해 idx, 타이틀, 유저네임, 불리언 생성 및 받아오기
				int cidx = rs.getInt("cidx");
				int midx = rs.getInt("c_midx");
				int bidx = rs.getInt("c_bidx");
				String cmt = rs.getString("cmt");
				String username = rs.getString("username");
				boolean Mine = false;
//				System.out.println("getCommentList midx"+midx);
//				System.out.println("getCommentList bidx"+bidx);
				// 지금 로그인 된 idx 값과 MEMO_MEMBER idx 값이 같은지 확인
				if (midx == rs.getInt("c_midx")) {
					Mine = true;
				} else {
					Mine = false;
				}
				// 받아온 데이터를 arraylist에 담기
				bData = new BoardData(cidx, cmt, username, Mine);
				cmtArr.add(bData);

			}
			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cmtArr;
	}


	// 댓글 작성
	public void insertComent(String cmt, int midx, int bidx) {
		
//		String testsql = "SELECT * FROM MEMO_MEMBER WHERE midx = "+midx;
		
		String sql = "INSERT INTO MEMO_COMMENT (c_midx, cmt ,wdate, c_bidx)" + "VALUES(?,?,SYSDATE,?)";
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, midx);
			pst.setString(2, cmt);
			pst.setInt(3, bidx);
			
			int result = pst.executeUpdate();
			System.out.println(result);
			
			con.close();
			pst.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	// 글을 작성하고 담아지는 메소드
	public void insertContent(String title, String content, String files, int midx) {

		String sql = "INSERT INTO MEMO_BOARD (b_midx, title, content ,wdate, files)" + "VALUES(?,?,?,SYSDATE,?)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, midx);
			pst.setString(2, title);
			pst.setString(3, content);
			pst.setString(4, files);
			
			int result = pst.executeUpdate();
//			System.out.println(result);
//			System.out.println(title);
//			System.out.println(content);

			con.close();
			pst.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// detail 상세페이지 불러오기
	public BoardData getBoardData(String idx) {
		BoardData boardData = null;

		String sql = "SELECT * FROM MEMO_BOARD WHERE bidx=" + idx;
		
		String sql2 = "SELECT * FROM memo_board b left join memo_comment c on b.bidx = c.c_bidx "
				+ "WHERE b.bidx ="+idx;
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql2);

			String title = "";
			String content = "";
			String writer = "";
			String files = "";
			
			
			while (rs.next()) {
				// 타이틀과 컨텐트 가져오기 midx 가져오기
				int b_midx = rs.getInt("b_midx");
				int bidx = rs.getInt("bidx");
				title = rs.getString("title");
				content = rs.getString("content");
				files = rs.getString("files");

//					System.out.println(title);
//					System.out.println(content);

				// 작성자명 가져오기 -> sql문으로 멤버에서 가져오기, 스테이트먼트, 리절트셋 새로운거 셋팅
				sql = "SELECT username FROM MEMO_MEMBER WHERE midx=" + b_midx;
				Statement st2 = con.createStatement();
				ResultSet rs2 = st2.executeQuery(sql);
				// 데이터가 있는지 없는지 확인하기 위해 호출
				writer = rs2.next() ? rs2.getString("username") : "유저 없음";
				
				boardData = new BoardData(bidx, title, content, writer, files);

				// while 문 안에서 클로즈 rs2, con2
				rs2.close();
				st2.close();

			}

			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return boardData;

	}

	// 브라우저에 board 보여지기
	public ArrayList<BoardData> getBoardList(int curIdx, int page) {
		ArrayList<BoardData> boardArr = new ArrayList<BoardData>();

		int sContent = (page * 10) + 1;
		int eContent = sContent + 9;

//		System.out.println("service : 첫 -" + sContent + ", 끝 -" + eContent);

//		String sql = "SELECT t.* FROM"
//				+ "    ("
//				+ "    SELECT ROWNUM rnum, t2.* FROM "
//				+ "        ("
//				+ "        SELECT * FROM MEMO_BOARD ORDER BY bidx DESC"
//				+ "        )t2 "
//				+ "    )t"
//				+ " WHERE rnum BETWEEN "+sContent+" AND "+ eContent +"";

		// sql문을 JOIN 통해서 하나로 만들기
//		String sql2 = "SELECT username, t.* FROM " 
//				+ "    (" 
//				+ "    SELECT ROWNUM rnum, t2.* FROM " 
//				+ "        ("
//				+ "        SELECT * FROM board_view ORDER BY bidx DESC " 
//				+ "        ) t2 "
//				+ "    ) t INNER JOIN memo_member ON memo_member.midx = t.b_midx " 
//				+ "WHERE rnum BETWEEN " + sContent
//				+ " AND " + eContent + "";
		
		String sql3 = "select t.*, m.username from ( "
				+ "    select rownum rnum, v.* from "
				+ "        ("
				+ "           select * from board_view order by bidx desc "
				+ "         )v "
				+ "    )t left join memo_member m on t.b_midx = m.midx "
				+ "WHERE rnum BETWEEN " + sContent
				+ " AND " + eContent + "order by t.bidx desc ";
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(sql3);

//			Statement subSt = con.createStatement();
//			ResultSet subRs = null;

			BoardData bData = null;

			while (rs.next()) {
				// model(BoardData)에서 만든 생성자에 관해 idx, 타이틀, 유저네임, 불리언 생성 및 받아오기
				int midx = rs.getInt("b_midx");
				int bidx = rs.getInt("bidx");
//				System.out.println("midx 값: "+midx);
				String title = rs.getString("title");
				String username = rs.getString("username");
				String files = rs.getString("files");
				Date wdate = rs.getDate("wdate");
				int count = rs.getInt("cmtCount");
				boolean Mine = false;

//				System.out.println(title+","+username);

//				String sql = "SELECT COUNT(*) MEMO_BOARD";

//				sql = "SELECT * FROM MEMO_MEMBER WHERE midx=" + midx;
//				subRs = subSt.executeQuery(sql);
//				while (subRs.next()) {
//					
//						username = (subRs.getString("username"));
//				
//				}
				// 지금 로그인 된 idx 값과 MEMO_MEMBER idx 값이 같은지 확인
				if (curIdx == rs.getInt("b_midx")) {
					Mine = true;
				} else {
					Mine = false;
				}
				// 받아온 데이터를 arraylist에 담기
				bData = new BoardData(bidx, title, username, files, count ,wdate, Mine);
				boardArr.add(bData);

			}
//			if(subRs !=null) {
//				subRs.close();
//			}
//			subSt.close();
			rs.close();
			st.close();
			con.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return boardArr;
	}

	// 총 컨텐츠 수 가져오기
	public int getCountContent() {
		int totalCount = 0;

		String sql = "SELECT COUNT(*) FROM MEMO_BOARD";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, dbID, dbPW);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				totalCount = rs.getInt(1);
			}

			con.close();
			st.close();
			rs.close();

//			System.out.println("total: " + totalCount);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalCount;

	}

}