package com.wos.relationships.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wos.relationships.models.StudentInfo;
import com.wos.relationships.repositories.StudentInfoRepository;

@Service
public class StudentInfoService {
	//dependency injection for repository
	private final StudentInfoRepository infoRepo;
	
	public StudentInfoService (StudentInfoRepository infoRepo) {
		this.infoRepo = infoRepo;
	}
	
	// read - all - show all students' info
	public List<StudentInfo> allInfo() {
		return infoRepo.findAll();
	}
	
	// read - one - show a specific student information
	public StudentInfo findInfo(Long studentId) {
		return infoRepo.findByStudentId(studentId);
	}
	
	// create student info to the db and map to student
	public StudentInfo create(StudentInfo info) {
		return infoRepo.save(info);
	}
	
	// update student info via studentId
	public StudentInfo update(
			Long studentId,
			String address,
			String city,
			String state
			) {
		StudentInfo info = infoRepo.findByStudentId(studentId);
		info.setAddress(address);
		info.setCity(city);
		info.setState(state);
		infoRepo.save(info);
		return infoRepo.findByStudentId(studentId);
	}
	
	// delete - by studentId
	public StudentInfo delete(Long studentId) {
		StudentInfo info = infoRepo.findByStudentId(studentId);
		infoRepo.delete(info);
		return info;
	}
	
}
