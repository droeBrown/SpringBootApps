package com.example.demo.Controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class HelloControllerUnitTest {

	@Test
	public void sayHello() {
		HelloController controller = new HelloController();
		Model model = new BindingAwareModelMap();
		String result = controller.sayHello("Hello", model);
		assertEquals("Hello", result);
		assertEquals("World", model.asMap().get("user"));
	}
}
