package com.cmpe275.snippetshare.Model;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="image")
public class Image {
	
	@Id
	private String id;
	private String empName;
	private byte[] image;
	
//	----------------------------------------------------------------------------------------
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
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
		return "Image [id=" + id + ", empName=" + empName + ", image="
				+ Arrays.toString(image) + "]";
	}
}
