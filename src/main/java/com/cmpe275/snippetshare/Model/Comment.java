package com.cmpe275.snippetshare.Model;

public class Comment {
	
	private long commentId;
	private String content;
	private String userName;
	
	public long getCommentId() {
		return commentId;
	}
	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", content=" + content
				+ ", userId=" + userName + "]";
	}
}
