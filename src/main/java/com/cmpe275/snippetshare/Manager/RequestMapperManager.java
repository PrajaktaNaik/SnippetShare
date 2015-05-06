package com.cmpe275.snippetshare.Manager;

import java.util.ArrayList;
import java.util.List;

import com.cmpe275.snippetshare.DAO.BoardDAO;
import com.cmpe275.snippetshare.DAO.RequestMapperDAO;
import com.cmpe275.snippetshare.Model.Board;
import com.cmpe275.snippetshare.Model.RequestMapper;
import com.cmpe275.snippetshare.Utility.ApplicationConstants;
import com.cmpe275.snippetshare.VO.RequestTracker;

public class RequestMapperManager {

	public static void saveRequest(RequestMapper mapper)throws Exception {
		RequestMapperDAO.saveRequest(mapper);
	}
	
	public static List<RequestTracker> getBoardsForAccess(String boardOwner, String currentUser)throws Exception{
		List<Board> privateBoards = BoardDAO.getBoardsByType("ownerId", boardOwner, ApplicationConstants.BOARD_TYPE_PRIVATE); 
		List<RequestTracker> mappers = new ArrayList<RequestTracker>();
		
		for(Board board : privateBoards){
			if(!board.getSharedWith().contains(currentUser)){
				
				RequestTracker mapping = new RequestTracker();
				mapping.setBoardId(board.getBoardId());
				mapping.setBoardDescription(board.getDescription());
				mapping.setBoardName(board.getBoardName());
				mapping.setOwnerId(boardOwner);
				mapping.setRequesterId(currentUser);
				
				RequestMapper mapper = RequestMapperDAO.checkForRequest(boardOwner, currentUser, board.getBoardId());
				if(mapper != null){
					System.out.println("Mapper Found------------>"+mapper.getStatus());
					mapping.setId(mapper.getId());
					mapping.setStatus(mapper.getStatus());
				}else{
					mapping.setStatus("");
				}
				
				mappers.add(mapping);
			}	
		}
		return mappers;
	}
	
	public static List<RequestTracker> getPendingRequests(String currentUser)throws Exception{
		List<RequestMapper> requestList =  RequestMapperDAO.getPendingRequests(currentUser, ApplicationConstants.REQUEST_PENDING);
		List<RequestTracker> pendingList = new ArrayList<RequestTracker>();
		
		for(RequestMapper mapper : requestList){
			RequestTracker tracker = new RequestTracker();
			tracker.setId(mapper.getId());
			tracker.setOwnerId(mapper.getOwnerId());
			tracker.setRequesterId(mapper.getRequesterId());
			
			String boardId = mapper.getBoardId();
			tracker.setBoardId(boardId);
			
			Board board = BoardDAO.getBoardById(boardId);
			if(board != null){
				tracker.setBoardDescription(board.getDescription());
				tracker.setBoardName(board.getBoardName());
			}
			
			pendingList.add(tracker);
		}
		return pendingList;
	}
	
	public static void updateRequest(String requestId, String boardId, String requesterId, String mode)throws Exception{
		if(mode.equalsIgnoreCase(ApplicationConstants.REQUEST_ACCEPTED)){
			RequestMapperDAO.updateRequest(requestId, ApplicationConstants.REQUEST_ACCEPTED);
			RequestMapperDAO.updateSharedWith(boardId, requesterId);
		}else if(mode.equalsIgnoreCase(ApplicationConstants.REQUEST_REJECTED)){
			RequestMapperDAO.updateRequest(requestId, ApplicationConstants.REQUEST_REJECTED);
		}
	}

}
