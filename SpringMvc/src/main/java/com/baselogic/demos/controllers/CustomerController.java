package com.baselogic.demos.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.baselogic.demos.domain.Customer;

@Controller
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private Validator validator;
    
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @RequestMapping(value = "/customer.html", method = RequestMethod.GET)
    public ModelMap get() {
        
		Customer customer = new Customer();
		System.out.println(customer.toString());
        // Because we're not specifying a logical view name, the
        // DispatcherServlet's DefaultRequestToViewNameTranslator kicks in.
        return new ModelMap("CustomerForm", customer);
    }

    @RequestMapping(value = "/customerForm", method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("customerForm") Customer customerForm,
            			   BindingResult result) {   

        validator.validate(customerForm, result);
        if (result.hasErrors()) { 
        	return "CustomerForm"; 
        }
        
        // Use the redirect-after-post pattern to reduce double-submits.
        return "CustomerSuccess";
	}
	

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		Customer cust = new Customer();
		//Make "Spring MVC" as default checked value
		cust.setFavFramework(new String []{"Spring MVC"});
		
		//Make "Make" as default radio button selected value
		cust.setSex("M");
		
		//make "Hibernate" as the default java skills selection
		cust.setJavaSkills("Hibernate");
		
		//initilize a hidden value
		cust.setSecretValue("I'm hidden value");
		
		return cust;
	}
	
	//@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		
		Map referenceData = new HashMap();
		
		//Data referencing for web framework checkboxes
		List<String> webFrameworkList = new ArrayList<String>();
		webFrameworkList.add("Spring MVC");
		webFrameworkList.add("Velocity");
		webFrameworkList.add("Tiles");
		webFrameworkList.add("JSF");
		webFrameworkList.add("Apache Wicket");
		referenceData.put("webFrameworkList", webFrameworkList);
		
		//Data referencing for number radiobuttons
		List<String> numberList = new ArrayList<String>();
		numberList.add("Number 1");
		numberList.add("Number 2");
		numberList.add("Number 3");
		numberList.add("Number 4");
		numberList.add("Number 5");
		referenceData.put("numberList", numberList);
		
		//Data referencing for country dropdown box
		Map<String,String> country = new LinkedHashMap<String,String>();
		country.put("US", "United Stated");
		country.put("CHINA", "China");
		country.put("SG", "Singapore");
		country.put("MY", "Malaysia");
		referenceData.put("countryList", country);
		
		//Data referencing for java skills list box
		Map<String,String> javaSkill = new LinkedHashMap<String,String>();
		javaSkill.put("Hibernate", "Hibernate");
		javaSkill.put("Spring", "Spring");
		javaSkill.put("Apache Wicket", "Apache Wicket");
		javaSkill.put("Velocity", "Velocity");
		referenceData.put("javaSkillsList", javaSkill);
		
		return referenceData;
	}
}