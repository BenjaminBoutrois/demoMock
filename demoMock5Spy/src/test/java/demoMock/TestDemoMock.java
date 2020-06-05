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
		
		// 4 - Appeler une m�thode du service (r�sultat r�el)
		Utilisateur utilisateur = mockUtilisateurDAO.getUtilisateur(mail);
		
		// 5 - Comparer le r�sultat r�el avec le r�sultat attendu
		assertEquals(utilisateur.getFirstName(), "testFirstName");
		
		// V�rifie si la fonction a �t� appel�e X fois avec "times(X)"
		Mockito.verify(mockUtilisateurDAO, Mockito.times(1)).getUtilisateur(mail);
	}
	
	@AfterEach
	public void afterEach()
	{
		mockUtilisateurDAO = null;
	}
}
