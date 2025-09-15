package com.JFSD.SDP;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.JFSD.SDP.Services.*;
import com.JFSD.SDP.Miscellaneous.ContactEmailSender;
import com.JFSD.SDP.Miscellaneous.PasswordSalting;
import com.JFSD.SDP.Model.BankAccount;
import com.JFSD.SDP.Model.User;
import com.JFSD.SDP.Services.UserService;
import com.JFSD.SDP.Services.BankAccountService;
import com.JFSD.SDP.Services.UserRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class LandingController {
	
	@Autowired
	UserService userService;
	@Autowired
	BankAccountService bankAccountService;

	@Autowired
    private  UserRepository userRepository;
	
	
	@RequestMapping("userDetails")
	public String userDetails(HttpServletRequest request)
	{
		Boolean loggedIn = (Boolean) request.getSession().getAttribute("loggedIn");
		if (loggedIn != null && loggedIn) {
	        return "details";
	    } else {
	        return "AccessDenied";
	    }
	}
	@RequestMapping("contact")
	public String contact(@RequestParam("name") String name,@RequestParam("subject") String subject,@RequestParam("email") String email,@RequestParam("message") String mes)
	{
		ContactEmailSender em=new ContactEmailSender();
		em.sendEmail();
		 System.out.println("Sent");	
		return "index";
	}
	
	@GetMapping("registerForm")
	public String regForm()
	{
		return "register";
	}
	
	@PostMapping("insertuser")
	public String insertUser(HttpServletRequest request)
	{
		PasswordSalting s=new PasswordSalting();
		ModelAndView mv = new ModelAndView();
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String Password = request.getParameter("password");
		User u = new User();
		u.setEmail(email);
		u.setName(name);
		u.setPassword(s.hash(Password));
		userService.addUser(u);
		return "register";
	}
	@PostMapping("login")
	public ModelAndView Login(HttpServletRequest request)
	{
		PasswordSalting s=new PasswordSalting();
		ModelAndView mv = new ModelAndView();
		String email = request.getParameter("email");
		String Password = s.hash(request.getParameter("password"));
		User u = userService.userLogin(email, Password);
		if(u!=null)
		{
			request.getSession().setAttribute("loggedIn", true);
			mv.setViewName("index");
			mv.addObject("user", u);
		}
		else
		{
			mv.setViewName("register");
		}
		return mv;
	}
	@RequestMapping("addBank")
	@ResponseBody
	public String insertBankUser()
	{
		BankAccount b=new BankAccount();
		b.setId((long)1);
		b.setAccountHolderName("Nivas");
		b.setBalance(9999.93);
		b.setAccountNumber("1234567");
		User user = userRepository.findById(1)
		        .orElseThrow(() -> new RuntimeException("User with ID 1 not found"));
		b.setUser(user);
		bankAccountService.createBankAccount(b, 1);
		return "register";
	}
}


