package bs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import bs.entity.User;


public interface UserService
{

	public Page<User> listAllUsers(Pageable pageable);

	public void saveOrUpdateUser(User user);

	public User getUserById(int id);

	public void deleteUser(int id);

}
