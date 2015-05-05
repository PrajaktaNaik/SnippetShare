package com.cmpe275.snippetshare.VO;

import com.cmpe275.snippetshare.Model.Snippet;

public class SnippetVO {

	private String ownerId;
	private String description;
	private String image;
	private long snippetId;
	
	public long getSnippetId() {
		return snippetId;
	}
	public void setSnippetId(long l) {
		this.snippetId = l;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
