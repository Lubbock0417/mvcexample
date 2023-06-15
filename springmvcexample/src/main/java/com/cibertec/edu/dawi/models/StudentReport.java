package com.cibertec.edu.dawi.models;

import java.util.ArrayList;
import java.util.List;

public class StudentReport {
	
	private List<Student> estudiantesList;
	private String seccion;
	private String aula;
		
	public StudentReport() {
		super();
		this.estudiantesList = new ArrayList<>();
	}
	public List<Student> getEstudiantesList() {
		return estudiantesList;
	}
	public void setEstudiantesList(List<Student> estudiantesList) {
		this.estudiantesList = estudiantesList;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public String getAula() {
		return aula;
	}
	public void setAula(String aula) {
		this.aula = aula;
	}
	
}
