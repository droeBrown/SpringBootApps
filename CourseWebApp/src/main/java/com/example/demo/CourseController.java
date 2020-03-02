package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {
	@RequestMapping("courses")
//	@ResponseBody only for data 
	public ModelAndView courses(@RequestParam("cname") String coursename, HttpSession session) {

//		System.out.println("Course Name is: " + cname);
		ModelAndView mv = new ModelAndView();
		mv.addObject("cname", coursename);
		mv.setViewName("Courses");
		return mv;
	}
}

//HttpSession session = req.getSession();
//String cname = req.getParameter("cname");
////System.out.println("Course Name is: " + cname);
//session.setAttribute("cname", cname);
//return "Courses";