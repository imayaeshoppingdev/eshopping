package com.example.eshopping.model.images;

import java.util.List;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.entity.GridImages;

public class GridImagesResponse extends BaseResponse {

	List<GridImages> gridImages;

	public List<GridImages> getGridImages() {
		return gridImages;
	}

	public void setGridImages(List<GridImages> gridImages) {
		this.gridImages = gridImages;
	}
	
	
}
