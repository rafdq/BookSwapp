package bs.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bs.dao.UserDAO;
import bs.entity.User;

@Service
@Transactional
public class UserServiceImpl implements UserService
{
	@Autowired
	UserDAO userDAO;


	@Override
	public Page<User> listAllUsers(Pageable pageable)
	{
		return userDAO.listAllUsers(pageable);
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
