package com.cmpe275.snippetshare.DAO;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.cmpe275.snippetshare.Model.RequestMapper;
import com.cmpe275.snippetshare.Utility.MongoConfig;

public class RequestMapperDAO {

	public static void saveRequest(RequestMapper mapper)throws Exception {
		MongoConfig.getMongoOperationsObj().save(mapper);
	}
	
	public static RequestMapper checkForRequest(String ownerId, String requesterId, String boardId)throws Exception{
		System.out.println("--------------->>>>>>"+ownerId+":"+requesterId+":"+boardId);
		Query query = new Query();
		query.addCriteria(Criteria.where("ownerId").is(ownerId));
		query.addCriteria(Criteria.where("requesterId").is(requesterId));
		query.addCriteria(Criteria.where("boardId").is(boardId));
		
		List<RequestMapper> mappersList = MongoConfig.getMongoOperationsObj().find(query, RequestMapper.class);
		System.out.println("mappers size---->"+mappersList.size());
		RequestMapper mapper = MongoConfig.getMongoOperationsObj().findOne(query, RequestMapper.class);
				
		System.out.println("Mapper's value"+mapper);
		
		return mapper;
	}
}
