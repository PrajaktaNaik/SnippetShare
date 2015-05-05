package com.cmpe275.snippetshare.Manager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.cmpe275.snippetshare.DAO.BoardDAO;
import com.cmpe275.snippetshare.Model.Board;
import com.cmpe275.snippetshare.Model.User;
import com.cmpe275.snippetshare.Utility.ApplicationConstants;

public class BoardManager {

	public  void createBoard(HttpSession session,Board board) throws Exception {
		BoardDAO.saveBoard(board);		
	}

	public  void updateBoard(HttpSession session,Board board) throws Exception{
		BoardDAO.updateBoard(board);
	}
	
	public  List<Board> getAllBoards(HttpSession session,User user) throws Exception{
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

	public  void deleteBoard(HttpSession session,String boardId)throws Exception {
		BoardDAO.deleteBoard(boardId);
	}

	public  void searchBoards(HttpSession session,String type, String searchValue,
			String currentUser,Model model) throws Exception{
		List<Board> publicBoards = new ArrayList<Board>();
		List<Board> privateBoards = new ArrayList<Board>();
		if(type.equalsIgnoreCase(ApplicationConstants.SEARCH_CATEGORY)){
			publicBoards = removeCurrentUsersBoard("categoryId", searchValue, currentUser, ApplicationConstants.BOARD_TYPE_PUBLIC);
			privateBoards = removeCurrentUsersBoard("categoryId", searchValue, currentUser, ApplicationConstants.BOARD_TYPE_PRIVATE);
			
			List<Board> sharedBoards = getSharedBoards(privateBoards, currentUser);
			
			model.addAttribute("publicBoards",publicBoards);
			model.addAttribute("privateBoards",sharedBoards);
			model.addAttribute("welcomeMsg", "Boards For: "+searchValue);
			
		}else if(type.equalsIgnoreCase(ApplicationConstants.SEARCH_USER)){
			publicBoards = removeCurrentUsersBoard("ownerId", searchValue, currentUser, ApplicationConstants.BOARD_TYPE_PUBLIC);
			privateBoards = removeCurrentUsersBoard("ownerId", searchValue, currentUser, ApplicationConstants.BOARD_TYPE_PRIVATE);
			
			List<Board> sharedBoards = getSharedBoards(privateBoards, currentUser);
			
			model.addAttribute("publicBoards",publicBoards);
			model.addAttribute("privateBoards",sharedBoards);
			model.addAttribute("welcomeMsg", "Boards For: "+searchValue);
		}
	}
	
	public static List<Board> removeCurrentUsersBoard(String key, String value, String currentUser, String boardType)throws Exception{
		List<Board> allBoards = BoardDAO.getBoardsByType(key, value, boardType);
		List<Board> updatedBoards = new ArrayList<Board>();
		for(Board board : allBoards){
			if(!board.getOwnerId().equalsIgnoreCase(currentUser)){
				updatedBoards.add(board);
			}
		}
		return updatedBoards;
	}
	
	public static List<Board> getSharedBoards(List<Board> privateBoards, String currentUser){
		List<Board> sharedBoards = new ArrayList<Board>();
		
		for(Board board : privateBoards){
			if(board.getSharedWith().contains(currentUser)){
				sharedBoards.add(board);
			}
		}
		return sharedBoards;
	}

}
