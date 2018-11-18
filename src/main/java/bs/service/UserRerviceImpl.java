package bs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bs.dao.UserDAO;
import bs.entity.User;

@Service
@Transactional
public class UserRerviceImpl implements UserService
{
	@Autowired
	UserDAO userDAO;

	public List<User> listAllUsers()
	{
		return userDAO.listAllUsers();
	}

	public void saveOrUpdateUser(User user)
	{
		userDAO.saveOrUpdateUser(user);
	}

	public User getUserById(int id)
	{
		return userDAO.getUserById(id);
	}

	public void deleteUser(int id)
	{
		userDAO.deleteUser(id);
	}

}
