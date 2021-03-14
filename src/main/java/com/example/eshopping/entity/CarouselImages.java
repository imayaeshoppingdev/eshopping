package com.example.eshopping.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.eshopping.model.product.SliderImage;

@Document
public class CarouselImages {

	@Id
	private String id;
	private List<SliderImage> sliderImages;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<SliderImage> getSliderImages() {
		return sliderImages;
	}
	public void setSliderImages(List<SliderImage> sliderImages) {
		this.sliderImages = sliderImages;
	}
	

	
	
}
