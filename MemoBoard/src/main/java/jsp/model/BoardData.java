package jsp.model;

import java.util.Date;

public class BoardData {
	//DB 컬럼명과 동일하게 
	private int idx;
	private String title;
	private String content;
	private String writer;
	private Date wdate;
	private boolean Mine;
	private String cmt;
	private int count;
	private String files;
	
	
	//comment 위한
	public BoardData(int idx, String cmt, String writer, boolean Mine) {
		
		this.idx = idx;
		this.cmt = cmt;
		this.writer = writer;
		this.Mine = Mine;
		
	}
	
	//board 목차를 위한
	public BoardData(int idx,  String title, String writer, String files, int count, Date wdate, boolean Mine) {
		
		this.idx = idx;
		this.title = title;
		this.writer = writer;
		this.files = files;
		this.count = count;
		this.wdate = wdate;
		this.Mine = Mine;
		
	}
	
	
	//detail 상세 페이지를 위한
	public BoardData(int idx, String title, String content, String writer, String files) {
		
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.files = files;
		
	}
	
	//프라이빗 했기에 getter setter 생성해야 불러올 수 있음
	
	public int getIdx() {
		return idx;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public boolean isMine() {
		return Mine;
	}
	public void setMine(boolean isMine) {
		this.Mine = Mine;
	}
	
	public Date getWdate() {
		return wdate;
	}


	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public String getCmt() {
		return cmt;
	}

	public void setCmt(String cmt) {
		this.cmt = cmt;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	//설명해주기 어떤 클래스에 어떤 데이터가 들어있는지 (필요에 따라 만들기)
	@Override
	public String toString() {
		return "BoardData [idx=" + idx + ", title=" + title + ", content=" + content + ", writer=" + writer + "]";
	}
	
	
	
}
