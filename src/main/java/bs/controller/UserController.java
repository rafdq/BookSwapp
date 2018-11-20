package bs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bs.entity.User;
import bs.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController 
{
	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public List<User> getUsers()
	{
		return userService.listAllUsers();
		
	}
	
	
}
