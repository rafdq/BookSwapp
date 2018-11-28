package bs.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public Page<User> getUsersPaginated(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber, 
			@RequestParam(value = "pageSize",required = false, defaultValue = "5") int pageSize) 
	{
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<User> page = userService.listAllUsers(pageable);
		return page;
	}
	
	
	
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId)
	{
		User user = userService.getUserById(userId);

		return user;

	}

	@PostMapping("/users")
	public User addUser(@RequestBody User user)
	{
		user.setId(0);

		userService.saveOrUpdateUser(user);

		return user;

	}

	@PutMapping("/users")
	public User updateUser(@RequestBody User user)
	{
		userService.saveOrUpdateUser(user);

		return user;

	}
	
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable int userId)
	{

		userService.deleteUser(userId);
		
		return "User ("+ userId +") deleted!";

	}
	
}
