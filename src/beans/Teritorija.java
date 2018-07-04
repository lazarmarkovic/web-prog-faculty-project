package beans;

import java.io.Serializable;

public class Teritorija implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2893658470350630969L;
	private long id;
	private String naziv;
	private String povrsina;
	private String brojStanovnika;
	
	
	public Teritorija() {}
	
	public Teritorija(String naziv, String povrsina, String brojStanovnika, long id) {
		this();
		this.naziv = naziv;
		this.povrsina = povrsina;
		this.brojStanovnika = brojStanovnika;
		this.id = id;
	}

	
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getPovrsina() {
		return povrsina;
	}

	public void setPovrsina(String povrsina) {
		this.povrsina = povrsina;
	}

	public String getBrojStanovnika() {
		return brojStanovnika;
	}

	public void setBrojStanovnika(String brojStanovnika) {
		this.brojStanovnika = brojStanovnika;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
