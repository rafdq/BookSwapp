package bs.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="user")
public class User
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="information")
	private String information;
	
	@Column(name="swap_points")
	private int swapPoints;

	@JsonManagedReference
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade= {CascadeType.ALL}, orphanRemoval = true)
	private List<Book> booksToSwap;

	public User()
	{
	}

	public User(String name, String email, String phoneNumber, String information)
	{
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.information = information;
	}

	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public String getInformation()
	{
		return information;
	}

	public int getSwapPoints()
	{
		return swapPoints;
	}

	public List<Book> getBooksToSwap()
	{
		return booksToSwap;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public void setInformation(String information)
	{
		this.information = information;
	}

	public void setSwapPoints(int swapPoints)
	{
		this.swapPoints = swapPoints;
	}

	public void setBooksToSwap(List<Book> booksToSwap)
	{
		this.booksToSwap = booksToSwap;
	}

		

}