package demoMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import dao.UtilisateurDAO;
import domaine.Utilisateur;
import service.ServiceGestion;

public class TestDemoMock
{
	@Mock
	UtilisateurDAO mockUtilisateurDAO;
	
	@InjectMocks
	ServiceGestion serviceGestion;
	
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
		Mockito.when(mockUtilisateurDAO.getUtilisateur(mail)).thenReturn(new Utilisateur("testLogin", "testPassword", "testName", "testFirstName"));
		
		// 4 - Appeler une m�thode du service (r�sultat r�el)
		Utilisateur utilisateur = serviceGestion.getUtilisateur(mail);
		
		// 5 - Comparer le r�sultat r�el avec le r�sultat attendu
		assertEquals(utilisateur.getFirstName(), "testFirstName");
		
		// V�rifie si la fonction a �t� appel�e X fois avec "times(X)"
		Mockito.verify(mockUtilisateurDAO, Mockito.times(1)).getUtilisateur(mail);
	}
	
	@AfterEach
	public void afterEach()
	{
		serviceGestion = null;
		mockUtilisateurDAO = null;
	}
}
