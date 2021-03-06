package com.cmpe275.snippetshare.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Board")
public class Board {
	
	@Id
	private String boardId;
	private String boardName;
	private String ownerId;
	private String categoryId;
	private String description;
	private String type;
	private List<String> sharedWith;
	private List<Snippet> snippets;
	
//	----------------------------------------------------------------------------------------
	
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getSharedWith() {
		return sharedWith;
	}
	public void setSharedWith(List<String> sharedWith) {
		this.sharedWith = sharedWith;
	}
	public List<Snippet> getSnippets() {
		return snippets;
	}
	public void setSnippets(List<Snippet> snippets) {
		this.snippets = snippets;
	}
	
//	----------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", boardName=" + boardName
				+ ", ownerId=" + ownerId + ", categoryId=" + categoryId
				+ ", desciption=" + description + ", type=" + type
				+ ", sharedWith=" + sharedWith + ", snippets=" + snippets + "]";
	}
}
