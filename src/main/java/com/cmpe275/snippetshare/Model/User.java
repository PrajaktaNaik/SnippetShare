package com.cmpe275.snippetshare.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
	
	@Id
	private String email;
	private String password;
	private List<String> followers;
	private List<String> following;
	private String profilePicture;
	private String fullName;
	
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getFollowers() {
		return followers;
	}
	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}
	public void setFollowing(List<String> following) {
		this.following = following;
	}
	public List<String> getFollowing() {
		return following;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password
				+ ", followers=" + followers + ", following=" + following
				+ ", profilePicture=" + profilePicture + ", fullName="
				+ fullName + "]";
	}
}
