package myPackage;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Help {
	private static boolean tablesAreCreated=false;
	private static Help instance;
	private Help() throws ClassNotFoundException
	{
		DbLayer.createAllTables();
		tablesAreCreated=true;
	}
	public static void getInstance() throws ClassNotFoundException
	{
		if(instance==null)
		instance=new Help();
	}
	public static boolean checkClient(String name, String surname,
			String birthDate, String phone, String address, String email) {
		return ((name.length() == 0) || (surname.length() == 0)
				|| (Help.convertToDate(birthDate) == null)
				|| (phone.length() == 0) || (address.length() == 0) || (email
					.length() == 0));
	}
	public static int thisYear()
	{
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	public static boolean checkCar(String make, String model,
			String year, String vin) {
		boolean validYear=true;
		try{
		int intYear = Integer.parseInt(year);
		validYear=!(intYear>1900)&&(intYear<Help.thisYear());
		}
		catch(Exception e)
		{
			
		}
		return ((make.length() == 0) || (model.length() == 0)
				||(vin.length() == 0) || validYear);
	}
	public static boolean checkOrder(String amount,String status)
	{
		boolean validAmount=true;
		try{
		int intAmount = Integer.parseInt(amount);
		validAmount=!(intAmount>-1)&&(intAmount<10001);
		}
		catch(Exception e)
		{
			
		}
		return (validAmount||(status==null));
	}
	public static Date convertToDate(String dateString) {
		String expectedPattern = "yyyy-MM-dd";
		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		java.util.Date date = null;
		try {
			date = formatter.parse(dateString);

		} catch (ParseException e) {
			// execution will come here if the String that is given
			// does not match the expected format.
			e.printStackTrace();
		}

		return date == null ? null : new java.sql.Date(date.getTime());
	}
}
