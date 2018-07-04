package responses;

import java.util.Date;

import beans.Teritorija;
import beans.VanrednaSituacija;
import beans.enums.NivoHitnosti;
import beans.enums.StanjeVSituacije;

public class VSResponseWithoutKorisnikAndKomentari {
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
	
	
	public VSResponseWithoutKorisnikAndKomentari() {}

	public VSResponseWithoutKorisnikAndKomentari(long id, String nazivMesta, String opstina, String opis,
			Date datumVreme, String tacnaLokacija, Teritorija teritorija, NivoHitnosti nivoHitnosti, String pathSlike,
			StanjeVSituacije stanje) {
		this();
		this.id = id;
		this.nazivMesta = nazivMesta;
		this.opstina = opstina;
		this.opis = opis;
		this.datumVreme = datumVreme;
		this.tacnaLokacija = tacnaLokacija;
		this.teritorija = teritorija;
		this.nivoHitnosti = nivoHitnosti;
		this.pathSlike = pathSlike;
		this.stanje = stanje;
	}
	
	public VSResponseWithoutKorisnikAndKomentari(VanrednaSituacija vs) {
		this(vs.getId(), vs.getNazivMesta(), vs.getOpstina(), vs.getOpis(), vs.getDatumVreme(),
				vs.getTacnaLokacija(), vs.getTeritorija(), vs.getNivoHitnosti(), vs.getPathSlike(),
				vs.getStanje());
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public void setDatumVreme(Date datumVreme) {
		this.datumVreme = datumVreme;
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
	
	
}
