package com.example.bookstoreapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDTO {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("email")
	private String email;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
