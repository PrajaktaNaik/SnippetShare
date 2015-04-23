package com.cmpe275.snippetshare.Model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
	
	@Id
	private String userId;
	private String userName;
	private String password;
	private String email;
	private ArrayList<User> followers;
	private ArrayList<User> following;
	private String picture;
	private String fullname;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public ArrayList<User> getFollowers() {
		return followers;
	}
	public void setFollowers(ArrayList<User> followers) {
		this.followers = followers;
	}
	public ArrayList<User> getFollowing() {
		return following;
	}
	public void setFollowing(ArrayList<User> following) {
		this.following = following;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + userId + ", userName=" + userName + ", password="
				+ password + "]";
	}
}
