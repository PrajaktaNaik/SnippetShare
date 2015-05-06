package com.cmpe275.snippetshare.VO;

public class RequestTracker {
	
	private String id; 
	private String ownerId;
	private String requesterId;
	private String boardId;
	private String status;
	private String boardName;
	private String boardDescription;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getRequesterId() {
		return requesterId;
	}
	public void setRequesterId(String requesterId) {
		this.requesterId = requesterId;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardDescription() {
		return boardDescription;
	}
	public void setBoardDescription(String boardDescription) {
		this.boardDescription = boardDescription;
	}
	@Override
	public String toString() {
		return "RequestMapping [id=" + id + ", ownerId=" + ownerId
				+ ", requesterId=" + requesterId + ", boardId=" + boardId
				+ ", status=" + status + ", boardName=" + boardName
				+ ", boardDescription=" + boardDescription + "]";
	}
}
