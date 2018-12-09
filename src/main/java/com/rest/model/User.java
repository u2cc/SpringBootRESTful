package com.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * This class is a model class for carrying user info
 * @author James Chen
 *
 */

@ApiModel(value="User Model",description="Holds user criteria")
public class User {
	
	//@JsonInclude(Include.NON_NULL) having this Jackson annotation will make the id 
	//attribute disappear in case of null if instance is used as response object
	private Long id = null;
	
	private String firstName;
	private String lastName;
	
	
	public User(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	//dataType can override the derived return type, not mandatory and used only if deemed necessary
	@ApiModelProperty(value = "User id",dataType = "long",required=false)
	public Long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	@ApiModelProperty(value = "User's first name",dataType="String",required=true )
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@ApiModelProperty(value = "User's last name",dataType="String",required=true)
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String toString() {
		return "[ User Id : " + id + " ] " + "[ First Name : " + firstName + ", Last Name : " + lastName + " ]";		
	}
	
}
