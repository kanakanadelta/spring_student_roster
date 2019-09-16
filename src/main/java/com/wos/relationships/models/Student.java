package com.wos.relationships.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="students")
public class Student {
	// Generate ID:
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Generate firstName:
	@Size(min=1, max=20)
	private String firstName;
	
	// Generate lastName
	@Size(min=1, max=20)
	private String lastName;
	
	// Generate age Integer
	@Min(1)
	@Max(120)
	private Integer age;
	
	// Generate dates //
	
	//Generate createdAt date
    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    
    //Generate updatedAt date upon request
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    //establish relationship to a student info
	@OneToOne(mappedBy="student", //map the info attribute in the Student class to the student attr. in info class
			cascade=CascadeType.ALL, //operations must be cascadedto the target of the assoc. referential integrity
			fetch=FetchType.LAZY // LAZY --> association is fetched when needed
			)
	private StudentInfo studentInfo;
	
	public Student(
			String firstName,
			String lastName,
			Integer age
			) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	public Student() {
		
	}
	
	//////////////////////////
	// GETTERS AND SETTERS //

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
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
