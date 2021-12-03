package com.example.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.ThFieldTestForm;

@Controller
@RequestMapping("th-field-test")
public class ThFieldTestController {
	
	@ModelAttribute
	private ThFieldTestForm setUpForm() {
		ThFieldTestForm thFieldTestForm = new ThFieldTestForm();
		thFieldTestForm.setGender("2");
		return thFieldTestForm;
	}
	
	@RequestMapping("")
	public String index(Model model) {
		Map<Integer, String> genderMap = new LinkedHashMap<>();
		genderMap.put(1, "woman");
		genderMap.put(2, "man");
		
		model.addAttribute("genderMap", genderMap);
		model.addAttribute("gender", "1"); //${"gender"}で1を呼べるように
		
		return "th-field-test";
	}
	
	@RequestMapping("form-sent")
	public String formSent(Model model,@Validated ThFieldTestForm form ,BindingResult result) {
		if(result.hasErrors()) {
			return index(model);
		}
		model.addAttribute("received", "送信されたのは"+form.getGender()+"です");
		
		return index(model);
	}
}
