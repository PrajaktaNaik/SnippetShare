package com.cmpe275.snippetshare.Manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cmpe275.snippetshare.DAO.BoardDAO;
import com.cmpe275.snippetshare.DAO.RequestMapperDAO;
import com.cmpe275.snippetshare.Model.Board;
import com.cmpe275.snippetshare.Model.RequestMapper;
import com.cmpe275.snippetshare.Utility.ApplicationConstants;
import com.cmpe275.snippetshare.Utility.MongoConfig;
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
	
	public static List<RequestMapper> getPendingRequests(String currentUser)throws Exception{
		return RequestMapperDAO.getPendingRequests(currentUser, ApplicationConstants.REQUEST_PENDING);
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
