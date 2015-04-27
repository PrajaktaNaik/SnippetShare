package com.cmpe275.snippetshare.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SnippetSequence")
public class SnippetSequence {
	
	@Id
	private String snipCounterId;
	private long sequence;
	
	public String getSnipCounterId() {
		return snipCounterId;
	}
	public void setSnipCounterId(String snipCounterId) {
		this.snipCounterId = snipCounterId;
	}
	public long getSequence() {
		return sequence;
	}
	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
}
