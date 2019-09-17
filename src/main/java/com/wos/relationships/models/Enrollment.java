package com.wos.relationships.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    // Establish a Many to One relationship and Join Column for each table reference: //
    
    // Many to One -- Course
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
    
    // Many to One -- Student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;
    
    // CONSTRUCTOR //
    
    public Enrollment() {
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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
	
	
	// END MODEL
}
