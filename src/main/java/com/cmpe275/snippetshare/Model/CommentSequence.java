package com.cmpe275.snippetshare.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CommentSequence")
public class CommentSequence {

	@Id
	private String commentCounterId;
	private long sequence;
	
//	----------------------------------------------------------------------------------------
	
	public String getCommentCounterId() {
		return commentCounterId;
	}
	public void setCommentCounterId(String commentCounterId) {
		this.commentCounterId = commentCounterId;
	}
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	
//	----------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		return "CommentSequence [commentCounterId=" + commentCounterId
				+ ", sequence=" + sequence + "]";
	}
}
