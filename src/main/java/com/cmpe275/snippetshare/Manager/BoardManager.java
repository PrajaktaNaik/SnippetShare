package com.cmpe275.snippetshare.Manager;

import com.cmpe275.snippetshare.DAO.BoardDAO;
import com.cmpe275.snippetshare.Model.Board;

public class BoardManager {

	public static void createBoard(Board board) throws Exception {
		BoardDAO.saveBoard(board);		
	}

	public static void updateBoard(Board board) throws Exception{
		BoardDAO.updateBoard(board);
	}

}
