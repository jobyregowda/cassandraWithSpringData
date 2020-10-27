package com.target.retail.demo.repository;

	

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.target.retail.demo.model.Product;

@Repository
public interface ProductRepository extends CassandraRepository<Product,Integer> {
	
	@Query("select * from retaildemo.products_by_id where product_id in(?0)")
	Product findByProductId(final int prodiuctId);
	
	@Query("update products_by_id set current_value = ?0 where product_id= ?1")
	 void updateByProductId(String current_value , int product_Id);
	
}
