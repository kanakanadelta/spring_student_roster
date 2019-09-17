package com.wos.relationships.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="StudentInfos")
public class StudentInfo {
	// Generate Student Info ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable=false, unique=true)
    private Long id;
    
    // Generate Address
    @Size(min=3, max=40)
    private String address;
    
    // Generate City field
    @Size(min=2, max=40)
    private String city;
    
    // Generate State field
    @Size(min=2, max=20)
    private String state;
    
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
	@OneToOne(
			fetch=FetchType.LAZY,
			optional=false
			)
	//@JoinColumn --> Defines mapping for composite foreign keys. 
	//It indicates that the corresponding table to this entity has a foreign_key to the referenced table.
	@JoinColumn(
			name="student_id",
			nullable=false	
			) 
	private Student student;
	
	public StudentInfo(
			String address,
			String city,
			String state
			) {
		this.address = address;
		this.city = city;
		this.state = state;
	}
	
	public StudentInfo() {
		
	}
	
	//////////////////////////
	// GETTERS AND SETTERS //

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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
	
	
}
