package jsp;

public class MemberInfo {
	
	public String userId;
	public String userPw;
	public String userNick;
	
	public MemberInfo(String userId, String userPw, String userNick) {
		this.userId = userId;
		this.userPw = userPw;
		this.userNick = userNick;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserNick() {
		return userNick;
	}

	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	
	
}
