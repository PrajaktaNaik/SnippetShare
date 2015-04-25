package com.cmpe275.snippetshare.Model;

import java.util.List;

public class Snippet {
	
	private String snippetId;
	private String ownerId;
	private String description;
	private String picture;
	private int noOfLikes;
	private List<Comment> comments;
	
	public String getSnippetId() {
		return snippetId;
	}
	public void setSnippetId(String snippetId) {
		this.snippetId = snippetId;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getNoOfLikes() {
		return noOfLikes;
	}
	public void setNoOfLikes(int noOfLikes) {
		this.noOfLikes = noOfLikes;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Snippet [snippetId=" + snippetId + ", ownerId=" + ownerId
				+ ", description=" + description + ", noOfLikes=" + noOfLikes
				+ "]";
	}
}
