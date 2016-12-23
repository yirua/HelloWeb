package com.tutorialspoint;
//import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import javax.validation.constraints.*;
import org.hibernate.validator.constraints.*;
public class Student {
	 @NotNull(message = "Please enter your age..")
//	 @Size(min =18, max = 100, message ="Your age should be over 18.")
	   private Integer age;
//	 @NotNull
	 @NotEmpty(message = "Please enter your name.")
	   private String name;
	   private Integer id;

	   public void setAge(Integer age) {
	      this.age = age;
	   }
	   public Integer getAge() {
	      return age;
	   }

	   public void setName(String name) {
	      this.name = name;
	   }
	   public String getName() {
	      return name;
	   }

	   public void setId(Integer id) {
	      this.id = id;
	   }
	   public Integer getId() {
	      return id;
	   }
	   public String toString() {
	        return "Student(Name: " + this.name + ", Age: " + this.age + ", Id: "+ this.id +")";
	    }
	}
