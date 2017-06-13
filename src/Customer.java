import java.util.ArrayList;

public class Customer {

	private int custID;
	private String title;
	private String first;
	private String last;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String email;
	private String position;
	private String company;
	private Database db;
	
	
	
	public Customer(int aCustID) {
		// TODO Auto-generated constructor stub
		db=new Database();
		String[] vals=db.getCustomer(aCustID);
		
		System.out.println(vals[0]);
		custID=Integer.parseInt(vals[0]);
		title=vals[1];
		first=vals[2];
		last=vals[3];
		street=vals[4];
		city=vals[5];
		state=vals[6];
		zip=vals[7];
		email=vals[8];
		position=vals[9];
		company=vals[10];
		
		
	}
	public Customer(String aTitle, String aFirstName, String aLastName, String anAddress, String aCity, String aState, String aZIP, String anEmail, String aPosition, String aCompany) {
		// TODO Auto-generated constructor stub
		db=new Database();
		
		
		title=aTitle;
		first=aFirstName;
		last=aLastName;
		street=anAddress;
		city=aCity;
		state=aState;
		zip=aZIP;
		email=anEmail;
		position=aPosition;
		company=aCompany;
		db.addCustomer(title, first, last,email);
		db.addAddress(street, city, state, zip);
		custID=Integer.parseInt(db.getCustomerID());
		int addID=Integer.parseInt(db.getAddressID());
		db.updateCustomer(addID, custID);
		db.addCompany(company);
		int compID=Integer.parseInt(db.getCompanyID());
		db.addEmployee(custID, position, compID);
		
		
	}
	
	
	
	
	public void setStreet(String aStreet)
	{
		street=aStreet;
	}
	public void setCity(String aCity)
	{
		city=aCity;
	}
	public void setState(String aState)
	{
		state=aState;
	}
	public void setZip(String aZip)
	{
		zip=aZip;
	}
	
	public void editAddress()
	{
		db.updateAddress(street, city, state, zip, custID);
	}
	
	
	public static ArrayList<String> displayCustomers(String last)
	{
		Database db2=new Database();
		return db2.getCustomers(last);
		
	}
	public static void displayTotals()
	{
		Database db3=new Database();
		db3.getTotals();
		
	}
	
	
	
	public void displayCustomer()
	{
		System.out.println("Customer Number: "+custID);
		System.out.printf("%s %s %s \n",title, first,last);
		System.out.println(street);
		System.out.printf("%s, %s %s \n", city,state,zip);
		System.out.println(email);
		System.out.printf("%s at %s \n", position,company);
	}

}
