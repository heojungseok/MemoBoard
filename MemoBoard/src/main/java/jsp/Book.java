package jsp;

public class Book {
	
	public String writer;
	public String content;
	
	public Book(String writer, String content) {
		this.writer = writer;
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
