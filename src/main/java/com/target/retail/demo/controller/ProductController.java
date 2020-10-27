package com.target.retail.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.retail.demo.exception.ProductNotFoundException;
import com.target.retail.demo.model.Product;
import com.target.retail.demo.service.ProductService;
import com.target.retail.demo.utils.AppConfigProperties;
import com.target.retail.demo.utils.Constants;
import com.target.retail.demo.utils.RestTemplateResponseErrorHandler;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	

	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
	AppConfigProperties appConfigProperties;
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping(value = "/v1/products/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiImplicitParams({
			@ApiImplicitParam(name = "productId", value = "USER_CAPTURE", paramType = "path", required = true)
			
	})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 429, message = "Too Many Requests"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 503, message = "Service Unavailable") })
	public ResponseEntity<?> getProductDetails(@PathVariable(required = true, name = "id") int productId
			) throws ProductNotFoundException {
		ResponseEntity<?> response = null;
		Product externalProductDetails = getExternalProductDataForProductId(productId);
		//externalProductDetails is not returing result but if der is output of this is set and sent back
		Product product = productService.getProduct(productId);
		product.setProductName(externalProductDetails.getProductName());
		product.setProductId(externalProductDetails.getProductId());
		return new ResponseEntity<>(product, HttpStatus.OK);
		
		
	}
	
	@PutMapping("/v1/products/{id}")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "productId", value = "USER_CAPTURE", paramType = "path", required = true),
		@ApiImplicitParam(name = "productId",value = "USER_CAPTURE", paramType = "body", required = true)
		
	})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 405, message = "Method Not Allowed"),
			@ApiResponse(code = 429, message = "Too Many Requests"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 503, message = "Service Unavailable") })
	public ResponseEntity<?> updateProductDetails(@PathVariable(required = true, name = "id") String productId, @RequestBody Product product)
			 throws ProductNotFoundException {
		ResponseEntity<?> response = null;
		// we can throw exception if the path if and product id does not match here
		productService.updateProduct(product);
		return new ResponseEntity<>("Updated product details", HttpStatus.OK);
		
		
	}
	private Product getExternalProductDataForProductId(int productId) {
	
		Product product = new Product();
		Map<String, Map> map;
		String externalUrl = appConfigProperties.getProductExternalUrl();
		externalUrl = externalUrl+productId+ Constants.EXTERNAL_PRODUCT_URL;
		//This can be generic exception handler to handle all types of rest client exception
		restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
		ResponseEntity<String> response = restTemplate.getForEntity(externalUrl, String.class);
		String responseStr = response.getBody();
		ObjectMapper mapper = new ObjectMapper();
		try {
			//read the data from the json response and create the product 
			map = mapper.readValue(response.getBody(), Map.class);
		} catch (JsonProcessingException e) {
			
		}
	 return product;
		
	}

}
