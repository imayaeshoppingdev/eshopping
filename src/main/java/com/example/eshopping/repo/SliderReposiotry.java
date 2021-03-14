package com.example.eshopping.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.eshopping.entity.Slider;

public interface SliderReposiotry extends CrudRepository<Slider, Long>{

	List<Slider> findAll();
}
