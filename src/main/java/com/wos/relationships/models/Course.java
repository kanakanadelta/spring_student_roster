package com.wos.relationships.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=20)
    private String name;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    //Establish Many to Many relationship
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name = "enrollments",
    		joinColumns = @JoinColumn(name = "course_id"),
    		inverseJoinColumns = @JoinColumn(name = "student_id")
    		)
    private List<Student> students;
    
    public Course() {
    	// constructor
    }
    
    //////////////////////////
    // GETTERS AND SETTERS //

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
    
	// PERSITENCE 
	// // runs the method right before the object is created
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    // // runs a method when the object is modified
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    //END MODEL
}
