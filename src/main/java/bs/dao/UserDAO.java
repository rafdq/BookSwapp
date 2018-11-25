package bs.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import bs.entity.User;


public interface UserDAO   //extends PagingAndSortingRepository<User, Integer>, CrudRepository<User, Integer>
{

	public Page<User> listAllUsers(Pageable pageable);

	public void saveOrUpdateUser(User user);

	public User getUserById(int id);

	public void deleteUser(int id);

	public long getUsersCount();

}
