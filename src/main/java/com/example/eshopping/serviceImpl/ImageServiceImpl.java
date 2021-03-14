 package com.example.eshopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.eshopping.entity.CarouselImages;
import com.example.eshopping.entity.GridImages;
import com.example.eshopping.entity.Slider;
import com.example.eshopping.repo.CarouselImageRepository;
import com.example.eshopping.repo.GridImageRepository;
import com.example.eshopping.repo.SliderReposiotry;
import com.example.eshopping.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService{

	@Autowired
	CarouselImageRepository carouselImageRepository;
	
	@Autowired
	GridImageRepository gridImageRepository;
	
	@Autowired
	SliderReposiotry sliderReposiotry;
	
	public CarouselImages saveCarouselImages(CarouselImages images) {
		return carouselImageRepository.save(images);
	}
	
	public CarouselImages findCarouselImageById(String id) {
		return carouselImageRepository.findById(id);
	}
	
	public List<CarouselImages> findCarouselImages() {
		return carouselImageRepository.findAll();
	}
	
	public GridImages saveGridImages(GridImages images) {
		return gridImageRepository.save(images);
	}
	
	public GridImages findGridImagesById(String id) {
		return gridImageRepository.findById(id);
	}
	
	public List<GridImages> findGridImagesByType(String type){
		return gridImageRepository.findByType(type);
	}
	
	public Slider saveSlider(Slider slider) {
		return sliderReposiotry.save(slider);
	}
	
	public List<Slider> getSlider(){
		return sliderReposiotry.findAll();
	}
	
}
