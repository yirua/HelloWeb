package com.tutorialspoint;

import java.util.List;

import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

@Controller
public class StudentController {
	ApplicationContext context = 
            new ClassPathXmlApplicationContext("Beans.xml");
	StudentJDBCTemplate studentJDBCTemplate = 
		      (StudentJDBCTemplate)context.getBean("studentJDBCTemplate");
	
   @RequestMapping(value = "/student", method = RequestMethod.GET)
   public ModelAndView student() {
	   
	   ModelAndView aModelAndView = new ModelAndView("student", "command", new Student()); 
	   
      return aModelAndView;
   }
  /*@RequestMapping(value = "/", method = RequestMethod.GET)
   public String indexPage(@Valid Student student, BindingResult bindingResult) {
		return "student";
   }*/
   
   @RequestMapping(value = "/redirect", method = RequestMethod.GET)
   public String redirect() {
     
      return "redirect:student";
   }
   @RequestMapping(value = "/student", method = RequestMethod.POST)
   public String addStudent(@Valid @ModelAttribute("command")Student student, 
		   BindingResult result, ModelMap model ) {
	   
     /* model.addAttribute("name", student.getName());
      model.addAttribute("age", student.getAge());
      model.addAttribute("id", student.getId());*/
	   if (result.hasErrors()) {
           return "student";
       }
	   
		 //empty all the records in the table Student..
			/*studentJDBCTemplate.truncate();
			System.out.println("------Records Creation--------" );
		      studentJDBCTemplate.create("Zara", 11);
		      studentJDBCTemplate.create("Nuha", 2);
		      studentJDBCTemplate.create("Ayan", 15);*/
	      studentJDBCTemplate.create(student.getName(), student.getAge());
	      
	      Student stored_student = studentJDBCTemplate.get_last_student();
	      model.addAttribute("name", stored_student.getName());
	      model.addAttribute("age", stored_student.getAge());
	      model.addAttribute("id", stored_student.getId());
	      System.out.println("------Listing Multiple Records--------" );
	      List<Student> students = studentJDBCTemplate.listStudents();
	      for (Student record : students) {
	         System.out.print("ID : " + record.getId() );
	         System.out.print(", Name : " + record.getName() );
	         System.out.println(", Age : " + record.getAge());
	      }
	      return "result";
      
   }
   
   
}