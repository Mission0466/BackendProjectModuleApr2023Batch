package dev_kc.productsservices;

import dev_kc.productsservices.repositories.ProductRepository;
import dev_kc.productsservices.repositories.projections.ProductWithIdAndTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductsservicesApplicationTests {

	private ProductRepository productRepository;
	@Test
	void contextLoads() {
	}

	@Test
	void testingQueries(){
		//productRepository.findByIdIs(1L);

//		List<ProductWithIdAndTitle> pros = productRepository.getTitleOfProductsOfGivenCategory(52);
//		System.out.println(pros.get(0).getId());
//		System.out.println(pros.get(0).getTitle());
	}

}
