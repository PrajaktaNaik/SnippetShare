package com.cmpe275.snippetshare.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cmpe275.snippetshare.Model.Board;
import com.cmpe275.snippetshare.Model.RequestMapper;
import com.cmpe275.snippetshare.Utility.MongoConfig;

public class RequestMapperDAO {

//	----------------------------------------------------------------------------------------------------
	
	public static void saveRequest(RequestMapper mapper)throws Exception {
		MongoConfig.getMongoOperationsObj().save(mapper);
	}
	
//	----------------------------------------------------------------------------------------------------
	
	public static RequestMapper checkForRequest(String ownerId, String requesterId, String boardId)throws Exception{
		Query query = new Query();
		query.addCriteria(Criteria.where("ownerId").is(ownerId));
		query.addCriteria(Criteria.where("requesterId").is(requesterId));
		query.addCriteria(Criteria.where("boardId").is(boardId));
		
		RequestMapper mapper = MongoConfig.getMongoOperationsObj().findOne(query, RequestMapper.class);
				
		return mapper;
	}
	
//	----------------------------------------------------------------------------------------------------
	
	public static List<RequestMapper> getPendingRequests(String currentUser, String requestStatus)throws Exception{
		Query query = new Query();
		query.addCriteria(Criteria.where("ownerId").is(currentUser));
		query.addCriteria(Criteria.where("status").is(requestStatus));
		
		List<RequestMapper> requestList = MongoConfig.getMongoOperationsObj().find(query, RequestMapper.class);
		return (requestList != null ? requestList : new ArrayList<RequestMapper>());
	}
	
//	----------------------------------------------------------------------------------------------------
	
	public static void updateRequest(String requestId, String updatedStatus)throws Exception{
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(requestId));
		
		Update update = new Update();
		update.set("status", updatedStatus);
		
		MongoConfig.getMongoOperationsObj().updateFirst(query, update, RequestMapper.class);
	}
	
//	----------------------------------------------------------------------------------------------------
	
	public static void updateSharedWith(String boardId, String sharedUser){
		Query query = new Query(Criteria.where("boardId").is(boardId));
		
		Update update = new Update();
		update.push("sharedWith",sharedUser);
		
		MongoConfig.getMongoOperationsObj().findAndModify(query, update, Board.class);
	}
}
