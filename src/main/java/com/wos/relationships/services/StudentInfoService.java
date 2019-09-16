package com.wos.relationships.services;

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
	
	// create student info to the db and map to student
	public StudentInfo create(StudentInfo info) {
		return infoRepo.save(info);
	}
	
}
