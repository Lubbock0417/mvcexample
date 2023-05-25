package com.cibertec.edu.dawi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.edu.dawi.models.Student;
import com.cibertec.edu.dawi.services.StudentServiceImpl;



@Controller
@RequestMapping("/home")
public class IndexController {
	
	@Autowired
	private StudentServiceImpl studentService;
	
	@Value("${index.titulo.text}")
	private String title;
	
	@GetMapping({"/index","/", "","/home"})
	public String index(Model model) {
		model.addAttribute("titulo",title);
		return "index";
	}
	
	@ModelAttribute("estudiantes")
	public List<Student> obtenerEstudiantes(){
		List<Student> estudiantes= studentService.getAllStudents();
		return estudiantes;
	}

}
