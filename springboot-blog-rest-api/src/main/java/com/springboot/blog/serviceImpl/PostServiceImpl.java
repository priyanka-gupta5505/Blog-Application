package com.springboot.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.Entity.Category;
import com.springboot.blog.Entity.Post;
import com.springboot.blog.Exception.ResourceNotFoundException;
import com.springboot.blog.Repository.CategoryRepository;
import com.springboot.blog.Repository.PostRepository;
import com.springboot.blog.dto.PostDto;
import com.springboot.blog.dto.PostResponse;
import com.springboot.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	
	private CategoryRepository categoryRepository;

	public PostServiceImpl(PostRepository postRepository,CategoryRepository categoryRepository) {
		super();
		this.postRepository = postRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		
	Category category =	categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() 
				-> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
		// convert DTO to entity
		Post post = mapToEntity(postDto);
		post.setCategory(category);

		Post newPost = postRepository.save(post);

		// convert entity to DTO

		PostDto postResponse = mapToDto(newPost);

		return postResponse;
	}

	@Override
	public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
						: Sort.by(sortBy).descending();
	
		org.springframework.data.domain.Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<Post> posts = postRepository.findAll(pageable);
		
		List<Post> listOfPosts = posts.getContent();
		
		List<PostDto> content =  listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;

	}

	// convert Entity to DTO
	private PostDto mapToDto(Post post) {

		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setContent(post.getContent());
		postDto.setDescription(post.getDescription());
		postDto.setTitle(post.getTitle());

		return postDto;
	}

	// Convert DTO to Entity
	private Post mapToEntity(PostDto postDto) {
		// convert DTO to entity
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());

		return post;
	}

	@Override
	public PostDto getPostById(Long id) {

		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		return mapToDto(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		
		Category category =	categoryRepository.findById(postDto.getCategoryId()).orElseThrow(() 
				-> new ResourceNotFoundException("Category", "id", postDto.getCategoryId()));
		// get post by id from the database
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		post.setCategory(category);

		Post updatePost = postRepository.save(post);
		return mapToDto(updatePost);
	}

	@Override
	public void deletePostById(long id) {
		
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		postRepository.delete(post);
		
	}

	@Override
	public List<PostDto> getPostsByCategory(Long categoryId) {
		Category category =	categoryRepository.findById(categoryId).orElseThrow(() 
				-> new ResourceNotFoundException("Category", "id", categoryId));
		List<Post> posts = postRepository.findByCategoryId(categoryId);
		

		return posts.stream().map((post) -> mapToDto(post) ).collect(Collectors.toList());
	}

}
