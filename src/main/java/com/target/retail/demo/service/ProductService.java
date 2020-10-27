package com.target.retail.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.retail.demo.exception.ProductNotFoundException;
import com.target.retail.demo.model.Product;
import com.target.retail.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	/**
	 * This method is used to get the product details of the Id passed
	 * @param productId
	 * @return
	 * @throws ProductNotFoundException
	 */
	   public Product getProduct(int productId) throws ProductNotFoundException{

	       Product product = repository.findByProductId(productId);
	        if(product.getProductId() == 0 )
	            throw new ProductNotFoundException("ProductId is not found");

	        return product;

	    }
	   
	   public void updateProduct(Product product) throws ProductNotFoundException{
		  Product productExists = repository.findByProductId(product.getProductId());
		  if(productExists.getProductId() == 0 ) {
	            throw new ProductNotFoundException("ProductId is not found");
		  }
		  repository.updateByProductId(product.getCurrentValue(), product.getProductId());

	    }
		  
}
