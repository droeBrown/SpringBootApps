package com.youtube.course;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {
	@RequestMapping("/")
	public String edureka() {
		return "edureka.jsp";
	}

}
