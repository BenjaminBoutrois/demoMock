package demoMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import dao.UtilisateurDAO;
import domaine.Utilisateur;

public class TestDemoMock
{
	@Spy
	UtilisateurDAO mockUtilisateurDAO;
	
	@BeforeEach
	public void beforeEach()
	{
		// 1 - Mocker l'interface du DAO
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetUtilisateur()
	{
		String mail = "test@mail.com";
		
		// 3 - Imposer un comportement au mock (stubbing)
		Mockito.doReturn(new Utilisateur("testLogin", "testPassword", "testName", "testFirstName")).when(mockUtilisateurDAO).getUtilisateur(mail);
		
		// 4 - Appeler une méthode du service (résultat réel)
		Utilisateur utilisateur = mockUtilisateurDAO.getUtilisateur(mail);
		
		// 5 - Comparer le résultat réel avec le résultat attendu
		assertEquals(utilisateur.getFirstName(), "testFirstName");
		
		// Vérifie si la fonction a été appelée X fois avec "times(X)"
		Mockito.verify(mockUtilisateurDAO, Mockito.times(1)).getUtilisateur(mail);
	}
	
	@AfterEach
	public void afterEach()
	{
		mockUtilisateurDAO = null;
	}
}
