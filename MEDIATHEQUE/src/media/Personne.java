package media;
public abstract class Personne {
	protected int id;
	protected String nom ;
	protected String prenom ;
	protected String adresse ;
	protected int phonNumber ;
	
	public int getId() {
		return id;
	}	
	
	public void setId(int id) {
		this.id = id;
	}	
	
}
