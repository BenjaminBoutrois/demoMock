package demoMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dao.UtilisateurDAO;
import domaine.Utilisateur;
import service.ServiceGestion;

public class TestDemoMock
{
	UtilisateurDAO mockUtilisateurDAO;
	ServiceGestion serviceGestion;
	
	@BeforeEach
	public void beforeEach()
	{
		// 1 - Mocker l'interface du DAO
		mockUtilisateurDAO = Mockito.mock(UtilisateurDAO.class);
		
		// 2 - Cr�er le service
		serviceGestion = new ServiceGestion(mockUtilisateurDAO);
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
		Mockito.verify(mockUtilisateurDAO).getUtilisateur(mail);
	}
	
	@AfterEach
	public void afterEach()
	{
		serviceGestion = null;
		mockUtilisateurDAO = null;
	}
}
