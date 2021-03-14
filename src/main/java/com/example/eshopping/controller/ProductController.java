package com.example.eshopping.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.eshopping.common.BaseResponse;
import com.example.eshopping.common.CommonConstant;
import com.example.eshopping.entity.Product;
import com.example.eshopping.entity.ProductApproval;
import com.example.eshopping.entity.ProductListing;
import com.example.eshopping.entity.User;
import com.example.eshopping.model.product.ApprovalProductListResponse;
import com.example.eshopping.model.product.ApprovalRequest;
import com.example.eshopping.model.product.FilterDataResponse;
import com.example.eshopping.model.product.ProductListResponse;
import com.example.eshopping.model.product.ProductListingResponse;
import com.example.eshopping.model.product.ProductRequest;
import com.example.eshopping.model.product.ProductResponse;
import com.example.eshopping.model.product.Suggesion;
import com.example.eshopping.model.product.UploadFileResponse;
import com.example.eshopping.repo.ProductRepository;
import com.example.eshopping.service.ProductService;
import com.example.eshopping.service.UserService;
import com.example.eshopping.util.DateFormat;
import com.example.eshopping.util.FileStorageProperties;
import com.example.eshopping.util.JSONUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductRepository productReository;
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveAndUpdateProduct")
	public ProductResponse saveAndUpdateProduct(@Valid @RequestBody Product request) {
		ProductResponse response = new ProductResponse();
		try {
			Product productApproval = new Product();
			if(request.getId() == null || request.getId().isEmpty()) {
				request.setCreatedDate(DateFormat.getDateAndTime());
				Product product = productService.findProductByName(request.getProductName());
				if(product != null) {
					response.setStatus(CommonConstant.ERROR);
					response.setStatusCode(01);
					response.setMessage("Product already present");
					return response;
				}
			}
//				ProductApproval productInApproval = productService.findProductApprovalByName(request.getProductName());
				
			
			
			request.setStatus("Pending");
			productApproval = productService.saveProduct(request); 
			response.setMessage("Product Added Successfully");
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("Product add failed");
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getProductById/{id}")
	public ProductResponse getProductById(@PathVariable String id) {
		ProductResponse response = new ProductResponse();
		try {
			if(id != null && !id.isEmpty()) {
				Product product = productService.getProductById(id);
				response.setProduct(product);
				response.setStatus(CommonConstant.SUCCESS);
			}
			else {
				response.setStatus(CommonConstant.ERROR);
				response.setMessage("Invalid");
				response.setStatusCode(01);
			}
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			response.setMessage("Get Product  failed");
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/getProducts")
	public ProductListResponse getProducts(@RequestBody ProductRequest request) {
		ProductListResponse response = new ProductListResponse();
		try {
			response = productService.getAllProducts(request.getCategory(), request.getSubCategory(), request.getPage(), 
					request.getRole(), request.getSort(), request.getDisplayLocation());
			System.out.println("product "+JSONUtil.toJson(response.getProducts()));
			if(response.getProducts().isEmpty()) {
				response.setMessage("No products found.");
			}
			response.setStatus(CommonConstant.SUCCESS);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/deleteProduct")
	public BaseResponse deleteProduct(@RequestBody ProductRequest request) {
		BaseResponse response = new BaseResponse();
		try {
			User user = userService.findUserById(request.getUserId());
			if(user.getRole().equalsIgnoreCase("Buyer & Seller")) {
				Product product = productService.getProductById(request.getId());
				System.out.println(JSONUtil.toJson(product));
				if(product.getUserId().equals(request.getUserId())) {
					int delete = productService.deleteProduct(request.getId());
					if(delete == 1) {
						response.setStatus(CommonConstant.SUCCESS);
						response.setMessage("Product Deleted");
					}else {
						response.setStatusCode(01);
						response.setStatus(CommonConstant.ERROR);
					}
				}
				else {
					response.setMessage("User has no permission to delete product");
					response.setStatus(CommonConstant.ERROR);
					response.setStatusCode(01);
				}
			}
			else {
				response.setMessage("User has no permission to delete product");
				response.setStatus(CommonConstant.ERROR);
				response.setStatusCode(02);
			}
			
		}catch(Exception e) {
			System.out.println(e);
			response.setStatus(CommonConstant.ERROR);
			response.setStatusCode(01);
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/filterProducts")
	public ProductListResponse filterProducts(@RequestBody ProductRequest request) {
		ProductListResponse response = new ProductListResponse();
		try {
			response = productService.getProductByFilter(request);
			response.setStatus(CommonConstant.SUCCESS);
			if(response.getProducts().isEmpty())
				response.setMessage("No Products found");
			else {
				response.setMessage("List of Products");
			}
		}
		catch(Exception e) {
			response.setStatusCode(01);
			response.setMessage("Filter failed");
			response.setStatus(CommonConstant.ERROR);
			System.out.println(e);
		}
		return response;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/suggestion")
	public ProductListResponse getProductForSuggestion(@RequestBody ProductRequest request) {
		ProductListResponse response = new ProductListResponse();
		try {
			List<Product> product = productService.getProductTitleForSuggestion(request.getCategory(), request.getSubCategory(), request.getTitle(), request.getDisplayLocation());
			List<Suggesion> suggesionList = new ArrayList<>();			
			if(!product.isEmpty()) {
				for(Product value : product) {
					Suggesion suggesion = new Suggesion();
					suggesion.setId(value.getId());
					suggesion.setCategoryId(value.getCategoryId());
					suggesion.setSubCategoryId(value.getSubCategoryId());
					suggesion.setProductListingId(value.getProductListingId());
					suggesion.setSuggesion(value.getTitle());
					suggesion.setSuggesionType("Title");
					suggesionList.add(suggesion);
				}
			}
			
//				response.setProducts(product);
				List<ProductListing> productListing = productService.getProductListingSuggesion(request.getTitle(),request.getCategory(), request.getSubCategory());
				for(ProductListing productSuggesion : productListing) {
					Suggesion suggesion = new Suggesion();
					suggesion.setId(productSuggesion.getId());
					suggesion.setCategoryId(productSuggesion.getCategoryId());
					suggesion.setSubCategoryId(productSuggesion.getSubCategoryId());
					suggesion.setProductListingId(productSuggesion.getId());
					suggesion.setSuggesion(productSuggesion.getProductListing());
					suggesion.setSuggesionType("Category");
					suggesionList.add(suggesion);
				}
			System.out.println(JSONUtil.toJson(suggesionList));
			response.setSuggesion(suggesionList);
			
		}
		catch(Exception e) {
			response.setStatusCode(01);
			System.out.println(e);
		}
		return response;
	}
	
	@PostMapping("/getFilterData")
	public FilterDataResponse getFilterData(@RequestBody ProductRequest request) {
		FilterDataResponse response = new FilterDataResponse();
		try {
			Map<String, Set<String>> filterdata = productService.getFilterData(request);
			response.setFilterData(filterdata);
		}
		catch(Exception e) {
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	@PostMapping("/getApprovalProducts")
	public ApprovalProductListResponse getApprovalProducts(@RequestBody ProductRequest request ) {
		ApprovalProductListResponse response = new ApprovalProductListResponse();
		try {
			List<Product> productApproval = productService.getApprovalList(request.getUserId(), request.getRole(), request.getStatus());
			response.setProductApproval(productApproval);
		}
		catch(Exception e) {
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	@PostMapping("/approvalProcess")
	public BaseResponse productApprovalProcess(@RequestBody ApprovalRequest request) {
		BaseResponse response = new BaseResponse();
		try {
//			String approvalId = request.getProduct().getId();
//			if(request.getStatus().equalsIgnoreCase("Approved")) {
//				request.getProduct().setIsActive(1);
				productService.saveProduct(request.getProduct());
//			}
//			ProductApproval productApproval = productService.findProductApprovalById(approvalId);
//			productApproval.setGst(request.getProduct().getGst());
//			productApproval.setImageVerified(request.getProduct().isImageVerified());
//			productApproval.setMaxValue(request.getProduct().getMaxValue());
//			productApproval.setMinValue(request.getProduct().getMinValue());
//			productApproval.setOfferPrice(request.getProduct().getOfferPrice());
//			productApproval.setReturnPolicy(request.getProduct().isReturnPolicy());
//			productApproval.setServiceAndRepair(request.getProduct().isServiceAndRepair());
//			productApproval.setDiscount(request.getProduct().getDiscount());
//			productApproval.setApprovedDate(DateFormat.getDateAndTime());
//			productApproval.setStatus(request.getStatus());
//			productService.saveProductApproval(productApproval); 
//			int deleteProductApproval = productService.deleteProductApproval(approvalId);
			
		}
		catch(Exception e) {
			System.out.println(e);
			response.setStatusCode(01);
		}
		return response;
	}
	
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile uploadfile,
			@RequestParam("userId") String userId) {
		UploadFileResponse response = new UploadFileResponse();		
        if (uploadfile.isEmpty()) {
        	response.setStatus(CommonConstant.ERROR);
        	response.setMessage("File is empty");
            return response;
        }
        try {
        	String[] fileName = uploadfile.getName().split(".");
        	String path = saveUploadedFiles(Arrays.asList(uploadfile)	, userId);
        	response.setUploadFilePath(path);
        } catch (IOException e) {
        	response.setStatus(CommonConstant.ERROR);
        	response.setMessage("File is empty");
        	System.out.println(e);
        }

        return response;

    }
	
	private String saveUploadedFiles(List<MultipartFile> files, String userId) throws IOException {
		FileStorageProperties fileStorageProperties = new FileStorageProperties();
		String finalPath = "";
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            byte[] bytes = file.getBytes();
            String uploadPath = fileStorageProperties.getUploadDir();
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            cretaeDirWithPermission("/var/www/exam/images/"+userId+"/"+timestamp.getTime());
            System.out.println("Path /var/www/exam/images/"+userId+"/" + file.getOriginalFilename());
            Path path = Paths.get("/var/www/exam/images/"+userId+"/"+timestamp.getTime()+"/" + file.getOriginalFilename());
            Files.write(path, bytes);
//            finalPath = "/var/www/exam/images/"+userId+"/" + file.getOriginalFilename();
            finalPath = "/images/"+userId+"/"+timestamp.getTime()+"/" + file.getOriginalFilename();
        }
        return finalPath;
    }
	
	private static boolean cretaeDirWithPermission(String path){
		 File file = new File(path);
		 if(!file.exists()){
			  file.mkdirs();
	    	  file.setExecutable(true,false);
	    	  file.setReadable(true,false);
	    	  file.setWritable(true,false);
		 }
		 return true;
	}
	
	@PostMapping("/adminFilter")
	public  ProductListResponse getAdminFilter(@RequestBody ProductRequest request) {
		ProductListResponse response = new ProductListResponse();
		try {
				List<Product> products = productService.getProductFilterAdmin(request);
				response.setProducts(products);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
        	response.setMessage("Filter error");
        	System.out.println(e);
		}
		return response;
	}
	
	@GetMapping("/bulkOrders/{location}")
	public ProductListResponse getBulkOrders(@PathVariable String location) {
		ProductListResponse response = new ProductListResponse();
		try {
				List<Product> products = productService.getBulkOrderProduct(location);
				response.setProducts(products);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
        	response.setMessage("Filter error");
        	System.out.println(e);
		}
		return response;
	}
	
	@PostMapping("/productSuggesion")
	public ProductListingResponse productListing(@RequestBody ProductRequest request) {
		ProductListingResponse response = new ProductListingResponse();
		try {
			List<Product> productListing = productService.getProductNameSuggesion(request.getTitle());
//			List<Product> products = productReository.findAll();
//			
//			for(Product productList : products) {
//				System.out.println("Name : "+productList.getProductName());
//				if(productList.getUserId() != null && (productList.getUserId().equals("601558081b110847c44346cb") || productList.getUserId().equals("6009431a4a58132dcc2774c5")) ) {
//					productList.setIsActive(1);
//				}else {
//				productList.setIsActive(0);
////					
//				}
//				productService.saveProduct(productList);
//			}
			response.setProduct(productListing);
		}
		catch(Exception e) {
			response.setStatus(CommonConstant.ERROR);
        	response.setMessage("Filter error");
        	System.out.println(e);
		}
		return response;
	}
}
