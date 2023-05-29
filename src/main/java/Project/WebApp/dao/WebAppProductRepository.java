package Project.WebApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Project.WebApp.model.Product;

public interface WebAppProductRepository extends JpaRepository<Product, Long> {
	

	List<Product> findByCategory(String category);
	
	@Query(value = "select * from product where lower(category) = lower(:category) order by product.id", nativeQuery = true)
	public List<Product> findByQuery(@Param("category") String category);
	
//	List<Product> findById(long id);
	

}
