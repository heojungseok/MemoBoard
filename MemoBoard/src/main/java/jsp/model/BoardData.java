package jsp.model;

import java.util.Date;

public class BoardData {
	//DB �÷���� �����ϰ� 
	private int idx;
	private String title;
	private String content;
	private String writer;
	private Date wdate;
	private boolean Mine;
	private String cmt;
	private int count;
	private String files;
	
	
	//comment ����
	public BoardData(int idx, String cmt, String writer, boolean Mine) {
		
		this.idx = idx;
		this.cmt = cmt;
		this.writer = writer;
		this.Mine = Mine;
		
	}
	
	//board ������ ����
	public BoardData(int idx,  String title, String writer, String files, int count, Date wdate, boolean Mine) {
		
		this.idx = idx;
		this.title = title;
		this.writer = writer;
		this.files = files;
		this.count = count;
		this.wdate = wdate;
		this.Mine = Mine;
		
	}
	
	
	//detail �� �������� ����
	public BoardData(int idx, String title, String content, String writer, String files) {
		
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.files = files;
		
	}
	
	//�����̺� �߱⿡ getter setter �����ؾ� �ҷ��� �� ����
	
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

	//�������ֱ� � Ŭ������ � �����Ͱ� ����ִ��� (�ʿ信 ���� �����)
	@Override
	public String toString() {
		return "BoardData [idx=" + idx + ", title=" + title + ", content=" + content + ", writer=" + writer + "]";
	}
	
	
	
}
