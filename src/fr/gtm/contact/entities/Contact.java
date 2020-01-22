package fr.gtm.contact.entities;

public class Contact {
private long id;
private String civilite;
private String nom;
private String prenom;

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getCivilite() {
	return civilite;
}
public void setCivilite(String civilite) {
	this.civilite = civilite;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}
public Contact() {
	super();
}

}
