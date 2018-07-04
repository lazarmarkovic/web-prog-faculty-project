package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import beans.enums.StanjeVSituacije;
import beans.enums.Vrsta;
import helpers.ObjectSerializer;

public class GrandContainer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1490385491066842699L;
	private ArrayList<Korisnik> korisnici;
	private ArrayList<VanrednaSituacija> vSituacije;
	private ArrayList<Teritorija> teritorije;
	private ArrayList<Komentar> komentari;
	
	private long nextKorisnikID;
	private long nextVSituacijaID;
	private long nextTeritorijaID;
	private long nextkomentarID;
	
	
	public GrandContainer() {
		this.korisnici = new ArrayList<Korisnik>();
		this.vSituacije = new ArrayList<VanrednaSituacija>();
		this.teritorije = new ArrayList<Teritorija>();
		this.komentari = new ArrayList<Komentar>();
		
		this.nextKorisnikID = 1;
		this.nextVSituacijaID = 1;
		this.nextTeritorijaID = 1;
		this.nextkomentarID = 1;
	}
	

	public long getNextKorisnikID() {
		return this.nextKorisnikID++;
	}
	public long getNextVSituacijaID() {
		return this.nextVSituacijaID++;
	}
	public long getNextTeritorijaID() {
		return this.nextTeritorijaID++;
	}
	public long getNextKomentarID() {
		return this.nextkomentarID++;
	}

	///// Za korisnike
	public ArrayList<Korisnik> getKorisnici() {
		return korisnici;
	}
	
	public ArrayList<Korisnik> getVolonteri() {
		ArrayList<Korisnik> volonteri = new ArrayList<Korisnik>();
		for (Korisnik k : this.korisnici) {
			if (k.getVrsta() == Vrsta.VOLONTER) {
				volonteri.add(k);
			}
			
		}
		return volonteri;
	}

	public void addKorisnik(Korisnik k) {
		this.korisnici.add(k);
		ObjectSerializer.getInstance().write(this);
	}
	
	public void updateKorisnik(Korisnik k, String kime) {
		Korisnik update = this.findKorisnik(kime);
		update.setEmail(k.getEmail());
		update.setIme(k.getIme());
		update.setPassword(k.getPassword());
		update.setPathSlike(k.getPathSlike());
		update.setPrezime(k.getPrezime());
		update.setTelefon(k.getTelefon());
		ObjectSerializer.getInstance().write(this);
	}
	
	public void removeKorisnik(Korisnik k) {
		this.korisnici.remove(k);
		ObjectSerializer.getInstance().write(this);
	}
	
	public boolean existsKorisnik(Korisnik k) {
		return this.korisnici.contains(k);
	}
	
	public boolean existsKorisnik(String kime) {
		for (Korisnik k : this.korisnici) {
			if (k.getKorisnickoIme().toLowerCase().equals(kime.toLowerCase()))
				return true;
		}
		return false;
	}
	
	public Korisnik findKorisnik(String kime) {
		for (Korisnik k : this.korisnici) {
			if (k.getKorisnickoIme().toLowerCase().equals(kime.toLowerCase()))
				return k;
		}
		return null;
	}
	
	// Za VanRedSituacije
	public ArrayList<VanrednaSituacija> getvSituacije() {
		return vSituacije;
	}
	
	public void addvSituacija(VanrednaSituacija vs) {
		this.vSituacije.add(vs);
		ObjectSerializer.getInstance().write(this);
	}
	
	public void updateVSituacija(VanrednaSituacija vs, long id) {
		VanrednaSituacija update = this.findVSituacija(id);
		update.setNazivMesta(vs.getNazivMesta());
		update.setNivoHitnosti(vs.getNivoHitnosti());
		update.setOpis(vs.getOpis());
		update.setOpstina(vs.getOpstina());
		update.setPathSlike(vs.getPathSlike());
		update.setStanje(vs.getStanje());
		update.setTacnaLokacija(vs.getTacnaLokacija());
		update.setTeritorija(vs.getTeritorija());
		update.setVolonter(vs.getVolonter());
		ObjectSerializer.getInstance().write(this);
	}
	
	public void removeVSituacija(VanrednaSituacija vs) {
		this.vSituacije.remove(vs);
		ObjectSerializer.getInstance().write(this);
	}
	
	public boolean existsVSituacija(VanrednaSituacija vs) {
		return this.vSituacije.contains(vs);
	}
	
	public boolean existsVSituacija(long id) {
		for (VanrednaSituacija vs : this.vSituacije) {
			if (vs.getId() == id)
				return true;
		}
		return false;
	}
	
	public VanrednaSituacija findVSituacija(long id) {
		for (VanrednaSituacija vs : this.vSituacije) {
			if (vs.getId() == id)
				return vs;
		}
		return null;
	}

	
	//Za teritorije
	public ArrayList<Teritorija> getTeritorije() {
		return teritorije;
	}
	
	public void  addTeritorija(Teritorija t) {
		this.teritorije.add(t);
		ObjectSerializer.getInstance().write(this);
	}
	
	public void updateTeritorija(Teritorija t, long id) {
		Teritorija update = this.findTeritorija(id);
		update.setBrojStanovnika(t.getBrojStanovnika());
		update.setNaziv(t.getNaziv());
		update.setPovrsina(t.getPovrsina());
		ObjectSerializer.getInstance().write(this);
	}
	
	public void removeTeritorija(Teritorija t) {
		this.teritorije.remove(t);
	}
	
	public boolean existsTeritorija(Teritorija t) {
		return this.teritorije.contains(t);
	}
	
	public boolean existsTeritorija(long id) {
		for (Teritorija t : this.teritorije) {
			if (t.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	public Teritorija findTeritorija(long id) {
		for (Teritorija t : this.teritorije) {
			if (t.getId() == id) {
				return t;
			}
		}
		return null;
	}

	
	//Za komentare
	public ArrayList<Komentar> getKomentari() {
		return komentari;
	}
	
	public void addKomentar(Komentar k) {
		this.komentari.add(k);
		ObjectSerializer.getInstance().write(this);
	}
	
	public void updateKomentar(Komentar k, long id) {
		Komentar update = this.findKomentar(id);
		update.setKorisnik(k.getKorisnik());
		update.setTekst(k.getTekst());
		update.setVs(k.getVs());
		ObjectSerializer.getInstance().write(this);
	}
	
	public void  removeKomentar(Komentar k) {
		this.komentari.remove(k);
		ObjectSerializer.getInstance().write(this);
	}
	
	public boolean existsKomentar(Komentar k) {
		return this.komentari.contains(k);
	}
	
	public boolean existsKomentar(long id) {
		for (Komentar k : this.komentari) {
			if (k.getId() == id)
				return true;
		}
		return false;
	}
	
	public Komentar findKomentar(long id) {
		for (Komentar k : this.komentari) {
			if (k.getId() == id)
				return k;
		}
		return null;
	}
	
	/////////////////////////////////////////////////////////////
	/////////////// Odeljak za prijavu //////////////////////////
	
	
	public Korisnik signin(String username, String password) {
		for (Korisnik k : this.korisnici) {
			if (k.getKorisnickoIme().equals(username) && k.getPassword().equals(password)) {
				return k;
			}
		}
		return null;
	}
	
	
	/////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////
	////////////////  Odeljak za filtere i pretragu ////////////
	
	private ArrayList<VanrednaSituacija> filtered;
	
	public void initSearch() {
		filtered = new ArrayList<VanrednaSituacija>();
	}
			
	public void setupFilters(boolean poDatumu, boolean poHitnosti, boolean poTeritoriji, boolean showMyVS, long terId, long hitnost, Korisnik k) {
		filtered = new ArrayList<VanrednaSituacija>();
		for (VanrednaSituacija vs : this.vSituacije) {
			if (vs.getStanje() == StanjeVSituacije.AKTIVNO) {
				filtered.add(vs);
			}
		}
		
		if (poDatumu) {
			for (int i = 0; i < filtered.size(); i++) {
				for (int j = i + 1; j < filtered.size(); j++) {
					if (filtered.get(i).getDatumVreme().compareTo(filtered.get(j).getDatumVreme()) < 0) {
						Collections.swap(filtered, i, j);
					}
				}
			}
		}
		
		if (poHitnosti) {
			Iterator<VanrednaSituacija> it = filtered.iterator();
			while (it.hasNext()) {
				VanrednaSituacija vs = it.next();
				if (vs.getNivoHitnosti().getValue() != hitnost) {
					it.remove();
				}
			}
		}
		
		if (poTeritoriji) {
			Iterator<VanrednaSituacija> it = filtered.iterator();
			while (it.hasNext()) {
				VanrednaSituacija vs = it.next();
				if (vs.getTeritorija().getId() != terId) {
					it.remove();
				}
			}
		}
		
		if (showMyVS) {
			System.out.println("OOOOOOOOOOOOOOOOOOOOOOMMMMM");
			Iterator<VanrednaSituacija> it = filtered.iterator();
			while (it.hasNext()) {
				VanrednaSituacija vs = it.next();
				if (vs.getVolonter() == null) {
					it.remove();
				} else if (!vs.getVolonter().getKorisnickoIme().equals(k.getKorisnickoIme())) {
					it.remove();
				}
			}
		}
		
		// Izbaci arhivirane
		Iterator<VanrednaSituacija> it = filtered.iterator();
		while (it.hasNext()) {
			VanrednaSituacija vs = it.next();
			if (vs.getStanje() != StanjeVSituacije.AKTIVNO) {
				it.remove();
			}
		}
	}
	
	public void setupSearch(boolean poNazTer, boolean poNazOpst, boolean poOpisu, boolean poVol, boolean poPraz, String traziOvo) {
		if (poNazTer) {
			Iterator<VanrednaSituacija> it = filtered.iterator();
			while (it.hasNext()) {
				VanrednaSituacija vs = it.next();
				if (!vs.getTeritorija().getNaziv().toLowerCase().contains(traziOvo.toLowerCase())) {
					it.remove();
				}
			}
		}
		
		if (poNazOpst) {
			Iterator<VanrednaSituacija> it = filtered.iterator();
			while (it.hasNext()) {
				VanrednaSituacija vs = it.next();
				if (!vs.getOpstina().toLowerCase().contains(traziOvo.toLowerCase())) {
					it.remove();
				}
			}
		}
		
		if (poOpisu) {
			Iterator<VanrednaSituacija> it = filtered.iterator();
			while (it.hasNext()) {
				VanrednaSituacija vs = it.next();
				if (!vs.getOpis().toLowerCase().contains(traziOvo.toLowerCase())) {
					it.remove();
				}
			}
		}
		
		if (poVol) {
			Iterator<VanrednaSituacija> it = filtered.iterator();
			while (it.hasNext()) {
				VanrednaSituacija vs = it.next();
				if (!(vs.getVolonter().getIme() + vs.getVolonter().getPrezime()).toLowerCase().contains(traziOvo.toLowerCase())) {
					it.remove();
				}
			}
		}
		
		if (poPraz) {
			Iterator<VanrednaSituacija> it = filtered.iterator();
			while (it.hasNext()) {
				VanrednaSituacija vs = it.next();
				if (vs.getVolonter() != null) {
					it.remove();
				}
			}
		}
	}
	
	public ArrayList<VanrednaSituacija> getFilterSearchResults() {
		return filtered;
	}
	
}
