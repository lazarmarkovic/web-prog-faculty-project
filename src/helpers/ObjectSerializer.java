package helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import beans.GrandContainer;
import beans.Korisnik;
import beans.enums.Vrsta;

public class ObjectSerializer {
	private static ObjectSerializer instance = null;
	
	protected ObjectSerializer() {}
	
	public static ObjectSerializer getInstance() {
		if(instance == null) {
			instance = new ObjectSerializer();
		}
		return instance;
	}
	
	public GrandContainer initObjectFileOrRead() {
		String path = Storage.getInstance().getDATAC_FILE();
		File f = new File(path);
		if (f.exists()) {
			GrandContainer gc = this.read();
			this.readAdminsFromFile(gc);
			return gc;
		}
		GrandContainer gc = new GrandContainer();
		this.readAdminsFromFile(gc);
		this.write(gc);
		return gc;
	}
	
	private void readAdminsFromFile(GrandContainer gc) {
		try {
			String adminsPath = Storage.getInstance().getADMIN_FILE();
			FileReader input = new FileReader(adminsPath);
			
			BufferedReader bufRead = new BufferedReader(input);
			String line = null;

			while ( (line = bufRead.readLine()) != null)
			{    
			    String[] a = line.split(";");
			    Korisnik k = new Korisnik(a[0], a[1], a[2], a[3], a[4], a[5], a[6]);
			    k.setVrsta(Vrsta.ADMIN);
			    if (!gc.existsKorisnik(a[0])) {
			    	gc.addKorisnik(k);
			    }
			}
			bufRead.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private GrandContainer read() {
		String path = Storage.getInstance().getDATAC_FILE();
		GrandContainer result = null;
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			result = (GrandContainer) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void write(GrandContainer gc) {
		String path = Storage.getInstance().getDATAC_FILE();
		try {
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(gc);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
