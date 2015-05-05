package com.cmpe275.snippetshare.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cmpe275.snippetshare.Model.Board;
import com.cmpe275.snippetshare.Model.Snippet;
import com.cmpe275.snippetshare.Model.SnippetSequence;
import com.cmpe275.snippetshare.Utility.MongoConfig;

public class SnippetDAO {

	public static void addSnippet(String boardId, Snippet snippet)throws Exception {
		/*List<Snippet> snippetList = getAllSnippets(boardId);
		snippetList.add(snippet);*/
		
		Query query = new Query();
		query.addCriteria(Criteria.where("boardId").is(boardId));
		
		Update update = new Update();
		update.push("snippets",snippet);
		
		MongoConfig.getMongoOperationsObj().findAndModify(query, update, Board.class);
		
	}
	
	public static List<Snippet> getAllSnippets(String boardId) throws Exception{
		Query query = new Query();
		query.addCriteria(Criteria.where("boardId").is(boardId));
		query.fields().include("snippets");
		
		Board board = MongoConfig.getMongoOperationsObj().findOne(query, Board.class);
		
		List<Snippet> snippetList = board.getSnippets();
		
		return (snippetList!=null ? snippetList : new ArrayList<Snippet>());
	}
	
	public static long getNextSnippetId(String key)throws Exception{
		  Query query = new Query(Criteria.where("_id").is(key));
		  
		  Update update = new Update();
		  update.inc("sequence", 1);
	 
		  FindAndModifyOptions options = new FindAndModifyOptions();
		  options.returnNew(true);
	 
		  SnippetSequence seq = MongoConfig.getMongoOperationsObj().findAndModify(query, update, options, SnippetSequence.class);
	 
		  if (seq == null) {
			throw new Exception("Unable to get sequence id for key : " + key);
		  }
	 
		  return seq.getSequence();
	}

	public static void deleteSnippet(String snippetId,String boardId) throws Exception {
		// TODO Auto-generated method stub
		List<Snippet> allSnippets=getAllSnippets(boardId);
		List<Snippet> newList=new ArrayList<Snippet>();
		for(int i=0;i<allSnippets.size();i++){
			if(allSnippets.get(i).getSnippetId()!=Long.valueOf(snippetId)){
				newList.add(allSnippets.get(i));
			}
		}
		Query query=new Query();
		query.addCriteria(Criteria.where("boardId").is(boardId));
		Update update=new Update();
		update.set("snippets",newList);
		MongoConfig.getMongoOperationsObj().updateFirst(query, update, Board.class);
	}

}
