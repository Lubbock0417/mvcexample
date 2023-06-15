package com.cibertec.edu.dawi.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibertec.edu.dawi.models.Student;
import com.cibertec.edu.dawi.services.StudentServiceImpl;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping(value = "/estudiante")
public class StudentController {

	@Autowired
	private StudentServiceImpl studentService;
		
	@GetMapping({"/index","/", "","/home"})
	public String index(Model model) {
		model.addAttribute("titulo","JASPER REPORT + SPRING BOOT");
		return "index";
	}
	
	@ModelAttribute("estudiantes")
	public List<Student> obtenerEstudiantes(){
		List<Student> estudiantes= studentService.getAllStudents();
		return estudiantes;
	}
	
	@GetMapping(value = "/reporte", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> reporteEstudiantes(HttpServletRequest request,
			HttpServletResponse response) throws IOException, JRException {
	    try {
	        InputStream report = this.studentService.getReportStudents();
	        byte[] data = report.readAllBytes();
	        report.close();
	        HttpHeaders header = new HttpHeaders();
	        header.setContentType(MediaType.APPLICATION_PDF);
	        header.set(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"reporte_estudiantes.pdf\"");
	        header.setContentLength(data.length);
	        return new ResponseEntity<byte[]>(data,header,HttpStatus.CREATED);	      
	    } catch (IOException ex) {
	        throw new RuntimeException("IOError retornando archivo");
	    }
	}
}
