package com.cmpe275.snippetshare.Utility;

import java.util.Arrays;
import java.util.List;

public class ApplicationConstants {
	
	public static final String DATABASE = "snippetshare";
	public static final String URL = "mongodb://kunal:cmpe275@ds061757.mongolab.com:61757/snippetshare";
	public static final String SNIPPET_SEQ_KEY = "SnippetSeq";
	public static final String COMMENT_SEQ_KEY = "CommentSeq";
	
	public static final String USER_ID_SESSION = "userId";
	
	public static final List<String> BOARD_TYPES = Arrays.asList("Public", "Private");
	
	public static final String SEARCH_USER = "USER";
	public static final String SEARCH_CATEGORY = "CATEGORY";
	
	public static final String BOARD_TYPE_PUBLIC = "Public";
	public static final String BOARD_TYPE_PRIVATE = "Private";
}
