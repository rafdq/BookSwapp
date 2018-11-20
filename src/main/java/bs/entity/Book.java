package bs.entity;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "book")
public class Book
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;

	@Column(name = "cover")
	private byte[] cover;

	@Column(name = "condition")
	private int condition;

	@Column(name = "active")
	private boolean active;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "user_id")
	private User user;

	public Book()
	{
	}

	public Book(String title, String author, byte[] cover, int condition, boolean active, User user)
	{
		this.title = title;
		this.author = author;
		this.cover = cover;
		this.condition = condition;
		this.active = active;
		this.user = user;
	}

	public long getId()
	{
		return id;
	}

	public String getTitle()
	{
		return title;
	}

	public String getAuthor()
	{
		return author;
	}

	public byte[] getCover()
	{
		return cover;
	}

	public int getCondition()
	{
		return condition;
	}

	public boolean isActive()
	{
		return active;
	}

	public User getUser()
	{
		return user;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public void setCover(byte[] cover)
	{
		this.cover = cover;
	}

	public void setCondition(int condition)
	{
		this.condition = condition;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Book [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", author=");
		builder.append(author);
		builder.append(", cover=");
		builder.append(Arrays.toString(cover));
		builder.append(", condition=");
		builder.append(condition);
		builder.append(", active=");
		builder.append(active);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}

}