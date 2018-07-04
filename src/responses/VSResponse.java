package responses;

import java.util.ArrayList;
import java.util.Date;

import beans.Komentar;
import beans.Korisnik;
import beans.Teritorija;
import beans.VanrednaSituacija;
import beans.enums.NivoHitnosti;
import beans.enums.StanjeVSituacije;
import helpers.DateTime;

public class VSResponse {
	private long id;
	private String nazivMesta;
	private String opstina;
	private String opis;
	private String datumVreme;
	private String tacnaLokacija;
	private Teritorija teritorija;
	private NivoHitnosti nivoHitnosti;
	private String pathSlike;
	private StanjeVSituacije stanje;
	private KorisnikResponse volonter = null;
	private ArrayList<KomentarResponse> komentari;
	
	
	public VSResponse() {
		komentari = new ArrayList<KomentarResponse>();
	}

	public VSResponse(long id, String nazivMesta, String opstina, String opis, Date datumVreme, String tacnaLokacija,
			Teritorija teritorija, NivoHitnosti nivoHitnosti, String pathSlike, StanjeVSituacije stanje,
			Korisnik volonter, ArrayList<Komentar> komentari) {
		this();
		this.id = id;
		this.nazivMesta = nazivMesta;
		this.opstina = opstina;
		this.opis = opis;
		this.datumVreme = DateTime.getInstance().dateTimeToNormalStr(datumVreme);
		this.tacnaLokacija = tacnaLokacija;
		this.teritorija = teritorija;
		this.nivoHitnosti = nivoHitnosti;
		this.pathSlike = pathSlike;
		this.stanje = stanje;
		if (volonter != null)
			this.volonter = new KorisnikResponse(volonter);
		for (Komentar k : komentari) {
			this.komentari.add(new KomentarResponse(k));
		}
	}
	
	public VSResponse(VanrednaSituacija vs) {
		this(vs.getId(), vs.getNazivMesta(), vs.getOpstina(), vs.getOpis(), vs.getDatumVreme(),
				vs.getTacnaLokacija(), vs.getTeritorija(), vs.getNivoHitnosti(), vs.getPathSlike(),
				vs.getStanje(), vs.getVolonter(), vs.getKomentari());
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

	public String getDatumVreme() {
		return datumVreme;
	}

	public void setDatumVreme(String datumVreme) {
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

	public KorisnikResponse getVolonter() {
		return volonter;
	}

	public void setVolonter(KorisnikResponse volonter) {
		this.volonter = volonter;
	}

	public ArrayList<KomentarResponse> getKomentari() {
		return komentari;
	}

	public void setKomentari(ArrayList<KomentarResponse> komentari) {
		this.komentari = komentari;
	}
}
