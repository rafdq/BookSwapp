package bs.dao;

import java.util.List;

import bs.entity.User;


public interface UserDAO
{

	public List<User> listAllUsers();

	public void saveOrUpdateUser(User user);

	public User getUserById(int id);

	public void deleteUser(int id);

}
