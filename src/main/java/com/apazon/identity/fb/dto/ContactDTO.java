package com.apazon.identity.fb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactDTO {

	private String pk;
	private String username;
	private String full_name;
	private boolean is_private;
	
	
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public boolean isIs_private() {
		return is_private;
	}
	public void setIs_private(boolean is_private) {
		this.is_private = is_private;
	}
	
}
