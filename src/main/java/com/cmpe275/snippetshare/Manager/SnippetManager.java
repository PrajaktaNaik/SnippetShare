package com.cmpe275.snippetshare.Manager;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import com.cmpe275.snippetshare.DAO.SnippetDAO;
import com.cmpe275.snippetshare.Model.Snippet;

public class SnippetManager {

	public static void addSnippet(String boardId, Snippet snippet) throws Exception {
	/*	String filename = "C://Users//Kunal//Google Drive//Docs//General//Leaving Certificate.jpg";
        FileInputStream f = new FileInputStream(new File(filename));
*/
       /* byte[] image = new byte[f.available()];
        f.read(image);*/
        SnippetDAO.addSnippet(boardId, snippet);
     //   f.close();
		
	}
	
	public static List<Snippet> getAllSnippet(String boardId) throws Exception{
		return SnippetDAO.getAllSnippets(boardId);
	}
	
	public static void deleteSnippet(String snippetId,String boardId) throws Exception{
		SnippetDAO.deleteSnippet(snippetId,boardId);
	}

}
