package com.springboot.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.blog.Entity.Category;
import com.springboot.blog.Exception.ResourceNotFoundException;
import com.springboot.blog.Repository.CategoryRepository;
import com.springboot.blog.dto.CategoryDto;
import com.springboot.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {

		Category category = modelMapper.map(categoryDto, Category.class);
		Category saveCategory = categoryRepository.save(category);
		return modelMapper.map(saveCategory, CategoryDto.class);

	}

	@Override
	public CategoryDto getCategory(Long categoryId) {

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {

		List<Category> categories = categoryRepository.findAll();

		return categories.stream().map((category) -> modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) {

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		category.setName(categoryDto.getName());
		category.setDescription(categoryDto.getDescription());
		category.setId(categoryId);

		Category updatedCategory = categoryRepository.save(category);

		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Long categoryId) {

		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		categoryRepository.delete(category);
	}

}
