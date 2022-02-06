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
	
	
	//�Խñ� ����
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
	//�Խñ� ����
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
	//��� ����
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
	
	//��� ���� ��������
	public ArrayList<BoardData> getCommentList(int curbIdx) {
		ArrayList<BoardData> cmtArr = new ArrayList<BoardData>();

		// sql���� JOIN ���ؼ� �ϳ��� �����
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
				// model(BoardData)���� ���� �����ڿ� ���� idx, Ÿ��Ʋ, ��������, �Ҹ��� ���� �� �޾ƿ���
				int cidx = rs.getInt("cidx");
				int midx = rs.getInt("c_midx");
				int bidx = rs.getInt("c_bidx");
				String cmt = rs.getString("cmt");
				String username = rs.getString("username");
				boolean Mine = false;
//				System.out.println("getCommentList midx"+midx);
//				System.out.println("getCommentList bidx"+bidx);
				// ���� �α��� �� idx ���� MEMO_MEMBER idx ���� ������ Ȯ��
				if (midx == rs.getInt("c_midx")) {
					Mine = true;
				} else {
					Mine = false;
				}
				// �޾ƿ� �����͸� arraylist�� ���
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


	// ��� �ۼ�
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
	

	// ���� �ۼ��ϰ� ������� �޼ҵ�
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

	// detail �������� �ҷ�����
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
				// Ÿ��Ʋ�� ����Ʈ �������� midx ��������
				int b_midx = rs.getInt("b_midx");
				int bidx = rs.getInt("bidx");
				title = rs.getString("title");
				content = rs.getString("content");
				files = rs.getString("files");

//					System.out.println(title);
//					System.out.println(content);

				// �ۼ��ڸ� �������� -> sql������ ������� ��������, ������Ʈ��Ʈ, ����Ʈ�� ���ο�� ����
				sql = "SELECT username FROM MEMO_MEMBER WHERE midx=" + b_midx;
				Statement st2 = con.createStatement();
				ResultSet rs2 = st2.executeQuery(sql);
				// �����Ͱ� �ִ��� ������ Ȯ���ϱ� ���� ȣ��
				writer = rs2.next() ? rs2.getString("username") : "���� ����";
				
				boardData = new BoardData(bidx, title, content, writer, files);

				// while �� �ȿ��� Ŭ���� rs2, con2
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

	// �������� board ��������
	public ArrayList<BoardData> getBoardList(int curIdx, int page) {
		ArrayList<BoardData> boardArr = new ArrayList<BoardData>();

		int sContent = (page * 10) + 1;
		int eContent = sContent + 9;

//		System.out.println("service : ù -" + sContent + ", �� -" + eContent);

//		String sql = "SELECT t.* FROM"
//				+ "    ("
//				+ "    SELECT ROWNUM rnum, t2.* FROM "
//				+ "        ("
//				+ "        SELECT * FROM MEMO_BOARD ORDER BY bidx DESC"
//				+ "        )t2 "
//				+ "    )t"
//				+ " WHERE rnum BETWEEN "+sContent+" AND "+ eContent +"";

		// sql���� JOIN ���ؼ� �ϳ��� �����
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
				// model(BoardData)���� ���� �����ڿ� ���� idx, Ÿ��Ʋ, ��������, �Ҹ��� ���� �� �޾ƿ���
				int midx = rs.getInt("b_midx");
				int bidx = rs.getInt("bidx");
//				System.out.println("midx ��: "+midx);
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
				// ���� �α��� �� idx ���� MEMO_MEMBER idx ���� ������ Ȯ��
				if (curIdx == rs.getInt("b_midx")) {
					Mine = true;
				} else {
					Mine = false;
				}
				// �޾ƿ� �����͸� arraylist�� ���
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

	// �� ������ �� ��������
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