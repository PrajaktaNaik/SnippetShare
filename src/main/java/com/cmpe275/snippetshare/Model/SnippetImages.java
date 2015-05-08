package com.cmpe275.snippetshare.Model;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="SnippetImages")
public class SnippetImages {

	@Id
	private long imageId;
	private byte[] image;

//	----------------------------------------------------------------------------------------
	
	public long getImageId() {
		return imageId;
	}
	public void setImageId(long imageId) {
		this.imageId = imageId;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
//	----------------------------------------------------------------------------------------
	
	@Override
	public String toString() {
		return "SnippetImages [imageId=" + imageId + ", image="
				+ Arrays.toString(image) + "]";
	}
}
