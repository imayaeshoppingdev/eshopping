package com.example.eshopping.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.eshopping.util.DateFormat;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

			Date date1 = format.parse("2020-03-14");
			Date date2 = format.parse(DateFormat.getTodayString());
			
			if (date1.compareTo(date2) <= 0) {
			    System.out.println("earlier");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
