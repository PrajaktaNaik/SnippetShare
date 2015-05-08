package com.cmpe275.snippetshare.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cmpe275.snippetshare.Model.Board;
import com.cmpe275.snippetshare.Model.Comment;
import com.cmpe275.snippetshare.Model.CommentSequence;
import com.cmpe275.snippetshare.Model.Snippet;
import com.cmpe275.snippetshare.Utility.MongoConfig;

public class CommentDAO {

//	----------------------------------------------------------------------------------------------------
	
	public static void addComment(String boardId, long snippetId,
			Comment comment)throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("boardId").is(boardId));
		query.addCriteria(Criteria.where("snippets.snippetId").is(snippetId));
		
		Update update = new Update();
		update.push("snippets.comments",comment);
		
		MongoConfig.getMongoOperationsObj().findAndModify(query, update, Board.class);
		
	}

//	----------------------------------------------------------------------------------------------------
	
	public static List<Comment> getAllComments(String boardId, long snippetId) throws Exception{
		Query query = new Query();
		query.addCriteria(Criteria.where("boardId").is(boardId));
		query.addCriteria(Criteria.where("snippets.snippetId").is(snippetId));
		query.fields().include("snippets.comments");
		
		Board board = MongoConfig.getMongoOperationsObj().findOne(query, Board.class);
		
		List<Snippet> snippetList = board.getSnippets();
		
		List<Comment> commentList = new ArrayList<Comment>();
		if(snippetList != null && snippetList.size() > 0){
			Snippet snippet = snippetList.get(0);
			commentList = snippet.getComments(); 
		}
		
		return (commentList!=null ? commentList : new ArrayList<Comment>());
	}
	
//	----------------------------------------------------------------------------------------------------
	
	public static long getNextCommentId(String key)throws Exception{
		  Query query = new Query(Criteria.where("_id").is(key));
		  
		  Update update = new Update();
		  update.inc("sequence", 1);
	 
		  FindAndModifyOptions options = new FindAndModifyOptions();
		  options.returnNew(true);
	 
		  CommentSequence seq = MongoConfig.getMongoOperationsObj().findAndModify(query, update, options, CommentSequence.class);
	 
		  if (seq == null) {
			throw new Exception("Unable to get sequence id for key : " + key);
		  }
	 
		  return seq.getSequence();
	}
}
