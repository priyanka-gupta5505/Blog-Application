/**
 * 
 */
package com.springboot.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.springboot.blog.Entity.Comment;
import com.springboot.blog.Entity.Post;
import com.springboot.blog.Exception.BlogAPIException;
import com.springboot.blog.Exception.ResourceNotFoundException;
import com.springboot.blog.Repository.CommentRepository;
import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.service.CommentService;

/**
 * @author priya
 *
 */
@Service
public class CommentServiceImpl  implements CommentService {
	

	private CommentRepository commentRepository;
	
	private PostRepository postRepository;

	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}

	@Override
	public CommentDto createComment(long postId, CommentDto commentDto) {
		
		Comment comment = mapToEntity(commentDto);
		
		//retrieve post entity by id
		Post post = postRepository.findById(postId).orElseThrow(
				() -> new ResourceNotFoundException("Post", "id", postId));
		
		//set post to comment entity
		comment.setPost(post);
		
		Comment newComment = commentRepository.save(comment);
		
		return mapToDto(newComment);
	}
	
	private CommentDto mapToDto(Comment comment) {
		
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setEmail(comment.getEmail());
		commentDto.setName(comment.getName());
		commentDto.setBody(comment.getBody());
		
		return commentDto;
		
	}
	
	private Comment mapToEntity(CommentDto commentDto) {
		
		Comment comment = new Comment();
		comment.setId(commentDto.getId());
		comment.setEmail(commentDto.getEmail());
		comment.setName(commentDto.getName());
		comment.setBody(commentDto.getBody());
		
		return comment;
	}

	@Override
	public List<CommentDto> getCommentsByPostId(long postId) {
		
		List<Comment> comments = commentRepository.findByPostId(postId);
		
		return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
		
	}

	@Override
	public CommentDto getCommentById(Long postId, Long commentId) {

		// retrieve post entity by id
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		//retrieve comment by id
		
		Comment comment = commentRepository.findById(commentId).orElseThrow( () ->
		 new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		return mapToDto(comment);
	}

	@Override
	public CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest) {
		
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		
		Comment comment = commentRepository.findById(commentId).orElseThrow( () ->
		 new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		
		comment.setName(commentRequest.getName());
		comment.setEmail(commentRequest.getEmail());
		comment.setBody(commentRequest.getBody());
		
	Comment updateComment =	commentRepository.save(comment);
		return mapToDto(updateComment);
	}

	@Override
	public void deleteComment(Long postId, Long commentId) {
		
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));	
		
		Comment comment = commentRepository.findById(commentId).orElseThrow( () ->
		 new ResourceNotFoundException("Comment", "id", commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		
		commentRepository.delete(comment);
		
	}

}
