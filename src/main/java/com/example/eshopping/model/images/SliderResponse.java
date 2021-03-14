package com.example.eshopping.model.images;

import java.util.List;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.CarouselImages;
import com.example.eshopping.entity.Slider;

public class SliderResponse extends BaseResponse {

	List<CarouselImages> carouselImages;
	List<Slider> sliderList;

	public List<CarouselImages> getCarouselImages() {
		return carouselImages;
	}

	public void setCarouselImages(List<CarouselImages> carouselImages) {
		this.carouselImages = carouselImages;
	}

	public List<Slider> getSliderList() {
		return sliderList;
	}

	public void setSliderList(List<Slider> sliderList) {
		this.sliderList = sliderList;
	}
	
	
}
