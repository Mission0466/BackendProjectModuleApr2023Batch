package dev_kc.productsservices;

import dev_kc.productsservices.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductsservicesApplicationTests {

	private ProductRepository productRepository;
	@Test
	void contextLoads() {
	}

	@Test
	void testingQueries(){
		productRepository.findByIdIs(1L);
	}

}
