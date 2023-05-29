package Project.WebApp.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Size(min = 3, max = 60)
	@Column(name = "productname")
	private String productName;

	@NotEmpty
	@Size(min = 3, max = 50)
	@Column(name = "brand")
	private String brand;

	@NotEmpty
	@Size(max = 250)
	@Column(name = "description")
	private String description;

	@NotNull
	@Positive
	@Column(name = "unitprice")
	private Double unitPrice;

	@NotNull
	@Positive
	@Column(name = "totalprice")
	private Double totalPrice;

	@NotNull
	@Positive
	@Column(name = "stock")
	private int stock = 1;

	@NotNull
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@Column(name = "recorddate")
	private LocalDate recordDate;

	@NotEmpty
	@Size(max = 50)
	@Column(name = "category")
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public LocalDate getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(LocalDate recordDate) {
		this.recordDate = recordDate;
	}

}
