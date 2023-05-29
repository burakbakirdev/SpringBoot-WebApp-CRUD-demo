package Project.WebApp.customvalidator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import Project.WebApp.model.Product;

@Component
public class WebAppCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	 public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        if (product.getStock() == 1) {
            Double unitPrice = product.getUnitPrice();
            Double totalPrice = product.getTotalPrice();

            if (unitPrice != null && totalPrice != null && !unitPrice.equals(totalPrice)) {
                errors.rejectValue("totalPrice", "custom.err");
            }
        }
    }
	

}
