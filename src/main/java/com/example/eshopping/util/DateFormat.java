package com.example.eshopping.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

//import com.mongodb.DB;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientURI;


public class DateFormat {

	public static String getTodayString(){

		//Date date = Calendar.getInstance().getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		//formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String today = formatter.format(new Date());
		
		return  today;
	}
	
	public static Date stringToDate(String argDate) {
		Date t = null;
		try {
			//TimeZone tz = TimeZone.getDefault();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			sdf.setLenient(false);
			//sdf.setTimeZone(tz);
			t = new Date(sdf.parse(argDate).getTime());

		} catch (ParseException pe) {
			//logger_.debug("ParseException encountered in stringToTimestamp. String = " + argDate + ", Msg = " + pe.getMessage());
		}
		return t;
	}
	
	public  static String convertStringToDate(Date indate)
	{
	   String dateString = null;
	   SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   /*you can also use DateFormat reference instead of SimpleDateFormat 
	    * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
	    */
	   try{
		dateString = sdfr.format( indate );
	   }catch (Exception ex ){
		System.out.println(ex);
	   }
	   return dateString;
	}
	
	public static String getDateAndTime(){
		Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("yyyy-MM-dd HH:mm:s");
	      return ft.format(dNow);
	}
	public static Date getTodayAsDate(){
	 return new Date();
	}
	
	public static boolean checkforTodayDate(String recordDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 String todayDate = sdf.format(new Date());
		System.out.println(sdf.parse(recordDate).before(sdf.parse(todayDate)));
		boolean value = sdf.parse(todayDate).before(sdf.parse(recordDate));
		return value;
	}
	public static void main(String s[]){
		
		try {
			checkforTodayDate("12/12/2020");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		String dbURI = "mongodb://eshopping:springBootReact@18.224.18.89:27017/eshopping";
//		MongoClient mongoClient = new MongoClient(new MongoClientURI(dbURI));
//		List<String> databases = mongoClient.getDatabaseNames();
//        
//        for (String dbName : databases) {
//            System.out.println("- Database: " + dbName);
//             
//            DB db = mongoClient.getDB(dbName);
//             
//            Set<String> collections = db.getCollectionNames();
//            for (String colName : collections) {
//                System.out.println("\t + Collection: " + colName);
//            }
//        }
//         
//        mongoClient.close();

		
	
	
	
	
	
	
}

}
