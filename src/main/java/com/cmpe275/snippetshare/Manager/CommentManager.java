package com.cmpe275.snippetshare.Manager;

import com.cmpe275.snippetshare.DAO.CommentDAO;
import com.cmpe275.snippetshare.Model.Comment;

public class CommentManager {

	public static void addComment(String boardId, long snippetId,
			Comment comment)throws Exception {
		CommentDAO.addComment(boardId, snippetId, comment);
	}

}
