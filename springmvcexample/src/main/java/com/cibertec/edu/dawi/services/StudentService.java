package com.cibertec.edu.dawi.services;

import java.io.InputStream;
import java.util.List;

import com.cibertec.edu.dawi.models.Student;

import net.sf.jasperreports.engine.JRException;

public interface StudentService {
	public List<Student> getAllStudents();
	
	public InputStream getReportStudents() throws JRException;
}
