package Project.WebApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Project.WebApp.dao.WebAppProductRepository;
import Project.WebApp.model.Product;

@Service
@Transactional
public class WebAppService {
	
	@Autowired
	private WebAppProductRepository productRepository;
	

	public Page<Product> findProducts(Pageable  pageRequest) {
		return productRepository.findAll(pageRequest); 		
	}
	
	public List<Product> findProducts(){
		return productRepository.findAll();
	}
	
	public Product findProductById(long id) {
		return productRepository.findById(id).get(); 		
	}
	
	public List<Product> findProductByCategory(String category){
		return productRepository.findByQuery(category);
	}
	
	public void saveProduct(Product product) {
		productRepository.save(product);
	}
	
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}
	
	

//	public Category findCategoryByProductId(long id) {
//		
//		return categoryRepository.findCategoryBy;
//	}
}
