package Project.WebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Project.WebApp.customvalidator.WebAppCustomValidator;
import Project.WebApp.model.Product;
import Project.WebApp.service.WebAppService;
import jakarta.validation.Valid;

@Controller
public class WebAppController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	WebAppService service;

	@Autowired
	WebAppCustomValidator customvalidator;

	@GetMapping("/product/{pid}")
	public ModelAndView viewProduct(@PathVariable long pid) {
		ModelAndView mv = new ModelAndView("view-product");
		mv.addObject("product", service.findProductById(pid));

		return mv;
	}

	@GetMapping("/product-byCategory/{category}")
	public ModelAndView viewProductByCategoryName(@PathVariable String category) {
		ModelAndView mv = new ModelAndView("view-product-bycategory");
		mv.addObject("products", service.findProductByCategory(category));

		return mv;
	}

	@GetMapping("/product/new")
	public ModelAndView newProduct() {
		ModelAndView mv = new ModelAndView("new-product");
		mv.addObject("product", new Product());

		return mv;
	}

	@GetMapping("/product/edit/{id}")
	public ModelAndView editProduct(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("view-product");
		Product product = service.findProductById(id);
		mv.addObject("product", product);

		return mv;
	}

	@PostMapping("/product/add")
	public ModelAndView addProduct(@Valid @ModelAttribute Product product, BindingResult result, Integer page) {

		ModelAndView mv = new ModelAndView();
		mv.addObject("product", product);
		customvalidator.validate(product, result);

		if (result.hasErrors())
			mv.setViewName("new-product");
		else {
			mv.setViewName("product-list");
			service.saveProduct(product);
			if (page == null || page < 1)
				page = 1;
			Page<Product> resultpage = service.findProducts(PageRequest.of(page - 1, 5));
			mv.addObject("products", resultpage.toList());
			mv.addObject("page_count", resultpage.getTotalPages());
			mv.addObject("current_page", page);
		}
		return mv;
	}

	@GetMapping("/show-product-list")
	public ModelAndView getProducts(@RequestParam(required = false) Integer page) {
		ModelAndView mv = new ModelAndView("product-list");

		if (page == null || page < 1)
			page = 1;
		
		
		
		Page<Product> result = service.findProducts(PageRequest.of(page - 1, 5));
		
		mv.addObject("products", result.toList());
		mv.addObject("page_count", result.getTotalPages());
		mv.addObject("current_page", page);

		return mv;
	}

	@GetMapping("/product/delete/{id}")
	public ModelAndView deleteProduct(@PathVariable long id, Integer page) {
		 ModelAndView mv = new ModelAndView("product-list");
		service.deleteProductById(id);
		
		
		if (page == null || page < 1)
			page = 1;
			Page<Product> resultpage = service.findProducts(PageRequest.of(page - 1, 5));
			mv.addObject("products", resultpage.toList());
			mv.addObject("page_count", resultpage.getTotalPages());
			mv.addObject("current_page", page);
			return mv;
}}
