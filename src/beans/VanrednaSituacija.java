package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import beans.enums.NivoHitnosti;
import beans.enums.StanjeVSituacije;
import helpers.DateTime;


public class VanrednaSituacija implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4200389217860536829L;
	private long id;
	private String nazivMesta;
	private String opstina;
	private String opis;
	private Date datumVreme;
	private String tacnaLokacija;
	private Teritorija teritorija;
	private NivoHitnosti nivoHitnosti;
	private String pathSlike;
	private StanjeVSituacije stanje;
	private Korisnik volonter = null;
	private ArrayList<Komentar> komentari;
	
	
	public VanrednaSituacija() {
		this.datumVreme = DateTime.getInstance().getDateTime();
		this.komentari = new ArrayList<Komentar>();
		this.stanje = StanjeVSituacije.AKTIVNO;
	}

	public VanrednaSituacija(String nazivMesta, String opstina, String opis, String tacnaLokacija,
			Teritorija teritorija, NivoHitnosti nivoHitnosti, String pathSlike, long id) {
		this();
		this.nazivMesta = nazivMesta;
		this.opstina = opstina;
		this.opis = opis;
		this.tacnaLokacija = tacnaLokacija;
		this.teritorija = teritorija;
		this.nivoHitnosti = nivoHitnosti;
		this.pathSlike = pathSlike;
		this.id = id;
		
		this.datumVreme = DateTime.getInstance().getDateTime();
		this.komentari = new ArrayList<Komentar>();
		this.stanje = StanjeVSituacije.AKTIVNO;
	}

	
	public String getNazivMesta() {
		return nazivMesta;
	}

	public void setNazivMesta(String nazivMesta) {
		this.nazivMesta = nazivMesta;
	}

	public String getOpstina() {
		return opstina;
	}

	public void setOpstina(String opstina) {
		this.opstina = opstina;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Date getDatumVreme() {
		return datumVreme;
	}

	public String getTacnaLokacija() {
		return tacnaLokacija;
	}

	public void setTacnaLokacija(String tacnaLokacija) {
		this.tacnaLokacija = tacnaLokacija;
	}

	public Teritorija getTeritorija() {
		return teritorija;
	}

	public void setTeritorija(Teritorija teritorija) {
		this.teritorija = teritorija;
	}

	public NivoHitnosti getNivoHitnosti() {
		return nivoHitnosti;
	}

	public void setNivoHitnosti(NivoHitnosti nivoHitnosti) {
		this.nivoHitnosti = nivoHitnosti;
	}

	public String getPathSlike() {
		return pathSlike;
	}

	public void setPathSlike(String pathSlike) {
		this.pathSlike = pathSlike;
	}

	public StanjeVSituacije getStanje() {
		return stanje;
	}

	public void setStanje(StanjeVSituacije stanje) {
		this.stanje = stanje;
	}

	public Korisnik getVolonter() {
		return this.volonter;
	}

	public void setVolonter(Korisnik volonter) {
		this.volonter = volonter;
	}

	public ArrayList<Komentar> getKomentari() {
		return komentari;
	}

	public void addKomentar(Komentar k) {
		this.komentari.add(k);
	}
	
	public void removeKomentar(Komentar k) {
		this.komentari.remove(k);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
