package pizzatech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pizzatech.dbaccess.QueryConstants;

@Entity
@Table(name = "USER_TABLE")
@NamedQueries({ @NamedQuery(name = QueryConstants.GET_USER, query = "SELECT e from User e WHERE e.username = :username AND e.password = :password") })
public class User {
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
