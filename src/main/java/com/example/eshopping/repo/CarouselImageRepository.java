package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopping.entity.CarouselImages;

@Repository
public interface CarouselImageRepository extends CrudRepository<CarouselImages, Long>{

	CarouselImages findById(String id);
	
	List<CarouselImages> findAll();
}
