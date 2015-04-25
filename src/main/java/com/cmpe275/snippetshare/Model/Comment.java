package com.cmpe275.snippetshare.Model;

public class Comment {
	
	private String commentId;
	private String content;
	private String userId;
	
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", content=" + content
				+ ", userId=" + userId + "]";
	}
}
