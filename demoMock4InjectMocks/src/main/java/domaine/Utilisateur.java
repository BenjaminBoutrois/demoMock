package domaine;

public class Utilisateur
{

	private String login, password, name, firstName;

	public Utilisateur(String login, String password, String name, String firstName)
	{
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.firstName = firstName;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
}
