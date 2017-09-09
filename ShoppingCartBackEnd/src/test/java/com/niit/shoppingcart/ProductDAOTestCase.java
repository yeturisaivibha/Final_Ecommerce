package com.niit.shoppingcart;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Product;
import com.niit.shoppingcart.domain.Supplier;

public class ProductDAOTestCase {

	@Autowired
	static AnnotationConfigApplicationContext context;

	@Autowired
	static ProductDAO productDAO;
	@Autowired
	static Product product;
	
	@BeforeClass
	public static void initialize()
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		//get the categoryDAO from context
		productDAO =  (ProductDAO) context.getBean("productDAO");
		
		//get the category from context
		product = (Product)context.getBean("product");
		
	}
	
	@Test
	public void createProductTestCase()
	{
		
		product.setId("Apple_01");
		product.setCategory_id("Apple_01");
		product.setDescription("Apple ipad pro 64GB 10.5inch with Wi-fi only");
		product.setName("Apple ipad");
		product.setPrice(49900);
		product.setQuantity(5);
		product.setSupplier_id("Apple_01");
		
		boolean flag =  productDAO.saveOrUpdate(product);

		assertEquals("createProductTestCase",true,flag);
		
	}
	@Test
	public void updateCategoryTestCase()
	{
		product.setId("Moto");
		product.setName("Moto G4 Plus");
		product.setDescription("Brand new Moto G4 32gb black");
		product.setCategory_id("ELECTRONICS");
		product.setPrice(14999);
		product.setSupplier_id("SP_Sangeetha");
		product.setQuantity(4);
		boolean flag = productDAO.saveOrUpdate(product);
		assertEquals("update Product TestCase",true,flag);
	}
	@Test
	public void listAllProductTestCase()
	{
		int actualSize = productDAO.list().size();
		assertEquals(2, actualSize);
	} 
}
