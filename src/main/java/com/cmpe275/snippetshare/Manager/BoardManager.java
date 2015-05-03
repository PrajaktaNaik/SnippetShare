package com.cmpe275.snippetshare.Manager;

import java.util.List;

import com.cmpe275.snippetshare.DAO.BoardDAO;
import com.cmpe275.snippetshare.Model.Board;
import com.cmpe275.snippetshare.Model.User;

public class BoardManager {

	public static void createBoard(Board board) throws Exception {
		BoardDAO.saveBoard(board);		
	}

	public static void updateBoard(Board board) throws Exception{
		BoardDAO.updateBoard(board);
	}
	
	public static List<Board> getAllBoards(User user) throws Exception{
		return BoardDAO.getBoards(user);
	}

	public static String getSharedUser(String boardId)throws Exception {
		List<String> sharedList = BoardDAO.getSharedUser(boardId);
		String result = "";
		for(String sharedUser : sharedList){
			if(result.isEmpty())
				result = result+sharedUser;
			else
				result = result+" , "+sharedUser;
		}
		return result;
	}

}
