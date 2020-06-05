package service;

import java.util.ArrayList;

import dao.UtilisateurDAO;
import domaine.Utilisateur;

public class ServiceGestion
{
	private UtilisateurDAO utilisateurDAO;
	
	public ServiceGestion(UtilisateurDAO utilisateurDAO)
	{
		this.utilisateurDAO = utilisateurDAO;
	}
	
	public boolean authentificate(String mail, String password)
	{
		return utilisateurDAO.getUser(mail, password);
	}
	
	public Utilisateur getUtilisateur(String mail)
	{
		return utilisateurDAO.getUtilisateur(mail);
	}
	
	public void create(String nom, String prenom, String mail, String mdp)
	{
		utilisateurDAO.createUtilisateur(nom, prenom, mail, mdp);
	}
	
	public ArrayList<Utilisateur> getUtilisateurs()
	{
		return utilisateurDAO.getUtilisateurs();
	}
	
	public void update(String oldMail, String name, String firstName, String newMail, String password)
	{
		utilisateurDAO.updateUtilisateur(oldMail, name, firstName, newMail, password);
	}
}
