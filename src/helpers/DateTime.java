package helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTime {
	   private static DateTime instance = null;
	   private SimpleDateFormat readDateSDF;
	   private SimpleDateFormat writeDateSDF;
	   private DateFormat writeDateTimeSDF;
	   
	   protected DateTime() {
		   this.readDateSDF = new SimpleDateFormat("yyyy-MM-dd");
		   this.readDateSDF.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
		   
		   this.writeDateSDF = new SimpleDateFormat("dd.MM.yyyy.");
		   this.writeDateSDF.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
		   
		   this.writeDateTimeSDF = new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss");
		   this.writeDateTimeSDF.setTimeZone(TimeZone.getTimeZone("Europe/Belgrade"));
	   }
	   public static DateTime getInstance() {
	      if(instance == null) {
	         instance = new DateTime();
	      }
	      return instance;
	   }
	   
	   //////////////////////////////////////////////////////
	   // For Date
	   
	   public Date getCurrentDate() {
		   Date cal =new Date();
		   Date date = null;
		   try {
			   date = this.writeDateSDF.parse(this.writeDateSDF.format(cal));
		   } catch (ParseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   return date;
	   }
	   
	   public Date htmlStringToDate(String sDate) {
		   try {
			   return this.readDateSDF.parse(sDate);
		   } catch (ParseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   return null;
	   }
	   
	   public String dateToNormalStr(Date date) {
		   return this.writeDateSDF.format(date);
	   }
	   
	  //////////////////////////////////////////////////////
	  //For DateTime
	   
	   public Date getDateTime() {
		   Date cal = new Date();
		   Date date = null;
		   try {
			   date = this.writeDateTimeSDF.parse(this.writeDateTimeSDF.format(cal));
		   } catch (ParseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   return date;
	   }
	   
	   public String dateTimeToNormalStr(Date date) {
		   return this.writeDateTimeSDF.format(date);
	   }
	}
