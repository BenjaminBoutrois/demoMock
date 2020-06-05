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
		
		// 2 - Créer le service
		serviceGestion = new ServiceGestion(mockUtilisateurDAO);
	}
	
	@Test
	public void testGetUtilisateur()
	{
		String mail = "test@mail.com";
		
		// 3 - Imposer un comportement au mock (stubbing)
		Mockito.when(mockUtilisateurDAO.getUtilisateur(mail)).thenReturn(new Utilisateur("testLogin", "testPassword", "testName", "testFirstName"));
		
		// 4 - Appeler une méthode du service (résultat réel)
		Utilisateur utilisateur = serviceGestion.getUtilisateur(mail);
		
		// 5 - Comparer le résultat réel avec le résultat attendu
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
