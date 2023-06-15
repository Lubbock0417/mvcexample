package com.cibertec.edu.dawi.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.edu.dawi.models.Student;
import com.cibertec.edu.dawi.models.StudentReport;
import com.cibertec.edu.dawi.repositories.StudentDao;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRSaver;



@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentRepository;
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return this.studentRepository.findAll();
	}
	@Override
	public InputStream getReportStudents() throws JRException {
		
		try {
			List<Student> listaEstudiante = this.getAllStudents();
			List<StudentReport> listaData = new ArrayList<StudentReport>();
			listaData.add(new StudentReport());
			listaData.get(0).setEstudiantesList(listaEstudiante);
			listaData.get(0).setAula("404");
			listaData.get(0).setSeccion("T5MA");
			JRBeanCollectionDataSource dts = new JRBeanCollectionDataSource(listaData);
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("IMAGE_PATH", "https://www.logolynx.com/images/logolynx/2d/2d0872912b6970a81b093aaf1bd027c7.jpeg");
			JasperReport jasperReportObj = getJasperReportCompiled();
			JasperPrint jPrint = JasperFillManager.fillReport(jasperReportObj, parameters, dts);
			InputStream result = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jPrint));
			return result;
		} catch (JRException ex) {
			throw ex;
		}
	}
	
	private JasperReport getJasperReportCompiled() {
		try {
			InputStream studentReportStream = getClass().getResourceAsStream("/jasper/students_report.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(studentReportStream);
			JRSaver.saveObject(jasperReport, "students_report.jasper");
			return jasperReport;
		} catch (Exception e) {
			return null;
		}		
	}	
}
