package com.example.eshopping.service;

import java.util.List;

import com.example.eshopping.entity.CarouselImages;
import com.example.eshopping.entity.GridImages;
import com.example.eshopping.entity.Slider;

public interface ImageService {

	public CarouselImages saveCarouselImages(CarouselImages images);
	
	public CarouselImages findCarouselImageById(String id);
	
	public List<CarouselImages> findCarouselImages();
	
	public GridImages saveGridImages(GridImages images);
	
	public GridImages findGridImagesById(String id);
	
	public List<GridImages> findGridImagesByType(String type);
	
	public Slider saveSlider(Slider slider);
	
	public List<Slider> getSlider();
}
