package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.eshopping.entity.GridImages;

@Repository
public interface GridImageRepository extends CrudRepository<GridImages, Long>{

	GridImages findById(String id);
	
	List<GridImages> findByType(String type);
}
