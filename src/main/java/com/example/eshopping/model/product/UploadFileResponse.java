package com.example.eshopping.model.product;

import com.example.eshopping.common.BaseResponse;

public class UploadFileResponse extends BaseResponse {
	
	private String uploadFilePath;

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
	
	
	
}
