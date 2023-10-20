package com.example.Employee.Mangement.Project;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService es;
	@Autowired
	private EmployeeRepository er;
	 
	@GetMapping("/")
	public String menu()
	{
		return "menu";
	}
	@GetMapping("/employeeMenu")
	public String employeeMenu() 
	{
		return"employeeMenu";    
	} 
@GetMapping("/add") 
public String add(Model model)  
{ 
	// model.addAttribute("errorMessage", "An error occurred. Please try again later.");
	return "add";
}
@PostMapping("/add")
public String saveDetails(@ModelAttribute("employeeMangement") EmployeeMangement employee, Model model)
{  
	if(isValidName(employee.getFirstName()))
{
		if(isValidName(employee.getLastName()))
  {
			
			if(isValid(employee.getEmail()))
			{
			//	List<EmployeeMangement> data=er.findAll();
		EmployeeMangement em=er.findByEmail(employee.getEmail());
				if(em==null)
				{
					long num=employee.getNumber();
					int count=0;
					while(num>0)
					{
						num=num/10;
						count++;
					}
					if(count==10)
					{
						
						EmployeeMangement em1=new EmployeeMangement();
						em1.setFirstName(employee.getFirstName());
						em1.setLastName(employee.getLastName());
						em1.setEmail(employee.getEmail());
						em1.setNumber(employee.getNumber());
						er.save(em1);
						return "employeeMenu";
					}
					else
					{
						model.addAttribute("errorMessage5", "Number Must Contain 10 numbers ");
						return "add";	
					}
				}
				else{
					if(employee.getEmail().equals(em.getEmail()))
					{
						model.addAttribute("errorMessage4","This Email Alreay Exist..");
						return "add";
					}
					long num=employee.getNumber();
					int count=0;
					while(num>0)
					{
						num=num/10;
						count++;
					}
					if(count==10)
					{
						
						EmployeeMangement em1=new EmployeeMangement();
						em1.setFirstName(employee.getFirstName());
						em1.setLastName(employee.getLastName());
						em1.setEmail(employee.getEmail());
						em1.setNumber(employee.getNumber());
						er.save(em1);
						model.addAttribute("errorMessage7"," Successfully Saved..");
						return "employeeMenu";
					}
					else
					{
						model.addAttribute("errorMessage5", "Number Must Contain 10 numbers ");
						return "add";	
					}				}

        	 }
			else
			{
				model.addAttribute("errorMessage2", "Email must contain @ symbol and .com ");
				return "add";
			}
				 
  }
		else
		{
			model.addAttribute("errorMessage1", "Last Name not allowed to Numbers and Special Charecters");
			return "add";
		}
	

}
	else
	{
		model.addAttribute("errorMessage", "First Name not allowed to Numbers and Special Charecters");
	return "add";
	}
	
}

private boolean isValidName(String name) {
    return name.matches("^[a-zA-Z.]*$");
}
public boolean isValid(String email) {
    return email != null && email.matches(".*@.*\\.com");
}
	@GetMapping("/display")
	public String diaplsy(Model model)
	{
		 List<EmployeeMangement> data =er.findAll();

	        // Add data to the model
	        model.addAttribute("dataList", data);
		return "display";
	}
	@GetMapping("edit")
	public String edit()
	{
		return "edit";
	}
	@GetMapping("/update")
	public String update()
	{
		return "update"; 
	}
	@PostMapping("/update")
	
		public String updateDetails(@RequestParam String firstName,@RequestParam String lastName,@RequestParam String email,@RequestParam long number,Model model,EmployeeMangement employee)
		{
		EmployeeMangement li=er.findByEmail(email);
		if(li!=null)
		{

			if(isValidName(employee.getFirstName()))
			{
					if(isValidName(employee.getLastName()))
			  {
						
						if(isValid(employee.getEmail()))
						{
						//	List<EmployeeMangement> data=er.findAll();
					//EmployeeMangement em=er.findByEmail(employee.getEmail());
							//if(em==null)
							//{
								long num=employee.getNumber();
								int count=0;
								while(num>0)
								{
									num=num/10;
									count++;
								}
								if(count==10)
								{
									
									li.setFirstName(firstName);
									li.setLastName(lastName);
									li.setNumber(number);
									er.save(li); 
									return "employeeMenu";
								}
								else
								{
									model.addAttribute("errorMessage5", "Number Must Contain 10 numbers ");
									return "update";	
								}
							//}
											

			        	 }
						else
						{
							model.addAttribute("errorMessage2", "Email must contain @ symbol and .com ");
							return "update";
						}
							 
			  }
					else
					{
						model.addAttribute("errorMessage1", "Last Name not allowed to Numbers and Special Charecters");
						return "update";
					}
				

			}
				else
				{
					model.addAttribute("errorMessage", "First Name not allowed to Numbers and Special Charecters");
				return "update";
				}
				
			
//			return"employeeMenu";
		}
		else
		{
			model.addAttribute("errorMessage8","Invali Email... please Enter Already Register Email..");
			return "update"; 	
		}
		
			
		}
	
	@GetMapping("/delete")
	public String delete()
	{
		return "delete";
	} 
	@PostMapping("/delete")
	public String deleteDetails(@RequestParam String email,Model model)
	{
		System.out.println("ok");
		EmployeeMangement em=er.findByEmail(email);
		if(em==null)
		{
			model.addAttribute("errorMessage6","Inavalid Email..");
			return "delete";
			
		}
		else
		{
			er.delete(em);
			return "menu";
		}
		
	}
}

