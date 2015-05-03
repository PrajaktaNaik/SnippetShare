package com.cmpe275.snippetshare.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cmpe275.snippetshare.Model.Board;
import com.cmpe275.snippetshare.Model.User;
import com.cmpe275.snippetshare.Utility.MongoConfig;

public class BoardDAO {

	public static void saveBoard(Board board) throws Exception{
		MongoConfig.getMongoOperationsObj().save(board);
		System.out.println("Board Created Successfully");
	}

	public static void updateBoard(Board board)throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("boardId").is(board.getBoardId()));
		
		Update update = new Update();
		update.set("boardName", board.getBoardName());
		update.set("description", board.getDescription());
		update.set("type", board.getType());
		update.set("sharedWith", board.getSharedWith());
		update.set("categoryId", board.getCategoryId());
		
		MongoConfig.getMongoOperationsObj().findAndModify(query, update, Board.class);
	}
	
	public static List<Board> getBoards(User user) throws Exception{
		Query query=new Query();
		query.addCriteria(Criteria.where("ownerId").is(user.getEmail()));
		query.fields().exclude("snippets");

		List<Board> allBoards=(List<Board>) MongoConfig.getMongoOperationsObj().find(query, Board.class);
		return allBoards;
		
	}

	public static List<String> getSharedUser(String boardId)throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("boardId").is(boardId));
		query.fields().include("sharedWith");
		
		Board board = MongoConfig.getMongoOperationsObj().findOne(query, Board.class);
		return (board.getSharedWith() != null ? board.getSharedWith() : new ArrayList<String>());
	}
	


}
