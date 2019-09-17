package com.wos.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wos.relationships.models.Dorm;
import com.wos.relationships.repositories.DormRepository;

@Service
public class DormService {
	//dependency injection
	@Autowired
	private DormRepository dormRepo;
	
	public DormService(DormRepository dormRepo) {
		this.dormRepo = dormRepo;
	}
	
	// GET all dorms
	public List<Dorm> getAll() {
		List<Dorm> dorms = dormRepo.findAll();
		return dorms;
	}
	
	// GET a dorm
	public Dorm getOne(Long id) {
		Optional<Dorm> optDorm = dormRepo.findById(id);
		if(optDorm.isPresent()) {
			return optDorm.get();
		} else {
			return null;
		}
	}
	
	//CREATE dorms
	public Dorm createOrUpdateDorm(Dorm dorm) {
		return dormRepo.save(dorm);
	}
	
}
