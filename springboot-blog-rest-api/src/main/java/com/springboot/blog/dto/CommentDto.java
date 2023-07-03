package com.springboot.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

private long id;
	@NotEmpty(message = "Email should not be null or empty")
	@Email
	private String email;
	@NotEmpty(message = "Name should not be null or empty")
	private String name;
	
	@NotEmpty
	@Size(min = 10, message = "Comment body must be minimum 10 characters")
	private String body;

}
