 package com.example.eshopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.CarouselImages;
import com.example.eshopping.entity.GridImages;
import com.example.eshopping.entity.Slider;
import com.example.eshopping.model.images.GridImagesRequest;
import com.example.eshopping.model.images.GridImagesResponse;
import com.example.eshopping.model.images.SliderRequest;
import com.example.eshopping.model.images.SliderResponse;
import com.example.eshopping.model.images.UpdateSliderRequest;
import com.example.eshopping.service.ImageService;

@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImagesController {

	@Autowired
	ImageService imageService;
	
	@PostMapping("/addandUpdateSlider")
	public BaseResponse saveAndUpdateSlider(@RequestBody UpdateSliderRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			imageService.saveCarouselImages(request.getSliderImages());
			response.setMessage("Images Saved");
		}
		catch(Exception e) {
			response.setErrorCode("01");
			response.setMessage("Image Update Failed");
			response.setStatusCode(1);
			System.out.println(e);
		}
		return response;
	}
	
	@GetMapping("/getSliders")
	public SliderResponse getSliders() {
		SliderResponse response = new SliderResponse();
		try {
			List<CarouselImages> carouselImages = imageService.findCarouselImages();
			response.setCarouselImages(carouselImages);
		}
		catch(Exception e) {
			response.setErrorCode("01");
			response.setMessage("Image get Failed");
			response.setStatusCode(1);
		}
		return response;
	}
	
	@PostMapping("/updateGridImages")
	public BaseResponse addAndSaveGridImages(@RequestBody GridImagesRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			GridImages gridImages = imageService.saveGridImages(request.getGridImages());
			response.setMessage("Grid images updated");
		}
		catch(Exception e) {
			response.setErrorCode("01");
			response.setMessage("Image update Failed");
			response.setStatusCode(1);
		}
		return response;
	}
	
	@GetMapping("/getGridImages/{type}")
	public GridImagesResponse getGridImages(@PathVariable String type) {
		GridImagesResponse response = new GridImagesResponse();
		try {
			List<GridImages> gridImages = imageService.findGridImagesByType(type);
			response.setGridImages(gridImages);
		}
		catch(Exception e) {
			response.setErrorCode("01");
			response.setMessage("Get Grid Image Failed");
			response.setStatusCode(1);
		}
		return response;
	}
	
	@PostMapping("/saveAndUpdateSlider")
	public BaseResponse saveAndUpdateSlider(@RequestBody SliderRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			Slider slider = imageService.saveSlider(request.getSliders());
			response.setMessage("Save slider");
		}
		catch(Exception e) {
			response.setErrorCode("01");
			response.setMessage("Slider Failed");
			response.setStatusCode(1);
		}
		return response;
	}
	
	@GetMapping("/getSlider")
	public SliderResponse getSlider() {
		SliderResponse response = new SliderResponse();
		try {
			List<Slider> sliderList = imageService.getSlider();
			response.setSliderList(sliderList);
		}
		catch(Exception e) {
			response.setErrorCode("01");
			response.setMessage("Get Slider Failed");
			response.setStatusCode(1);
		}
		return response;
	}
}
