import java.util.ArrayList;
import java.util.Scanner;

public class CustomerApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean check=true;
		
		Scanner user = new Scanner(System.in);
		
		while(check)
		{	ArrayList<String> ids=null;
			boolean check2=true;
			boolean check5=true;
			System.out.println("Please input the desired last name to search for.\n "
					+ "Type in 'totals' to find information about database quantities");
			String last=user.nextLine();
			if(last.equals("totals"))
			{
				Customer.displayTotals();
				check5=false;
				check2=false;
			}
			else
			{
				ids=Customer.displayCustomers(last);
			}
						
			Customer cust=null;
			
			while(check2==true)
			{
				System.out.println("Please input the desired customer id from the list."
						+ "If the desired customer is not listed. Please enter (New)");
				String id1=user.nextLine();
				System.out.println("");
				if(!id1.equals("New"))
				{
					try
						{
							
						
						int id=Integer.parseInt(id1);
						
						check2=checkIDs(id,ids);
						if(!check2)
						{
							cust = new Customer(id);
						}	
						else
						{
							System.out.println("That is not one of the displayed customer ids. Please try again.");
						}
											
					}
					catch(NumberFormatException e)
					{
						System.out.println("That is an invalid input. Please try again.");
					}
				}
				else
				{
					
					cust=createCust(user);
					check2=false;
					
				}
			}
			if(check5)
			{
				cust.displayCustomer();
			}
			boolean check3=true;
			while(check3==true)
			{
				System.out.println("Press (1) to search for another customer or (2) to Edit the customer's address or (3) to exit.");
				String input=user.nextLine();
				if(input.equals("1"))
				{
					check3=false;	
				}
				else if(input.equals("2"))
				{
					
					System.out.println("Please input desired Address. If no change enter 'N'");
					String add=user.nextLine();
					
					if(!add.equals("N"))
					{
						cust.setStreet(add);
					}
					System.out.println("Please input desired City. If no change enter 'N'");
					String city=user.nextLine();
					
					if(!city.equals("N"))
					{
						cust.setCity(city);
					}
					System.out.println("Please input desired State. If no change enter 'N'");
					String state=user.nextLine();
					
					if(!state.equals("N"))
					{
						cust.setState(state);
					}
					System.out.println("Please input desired ZIP. If no change enter 'N'");
					String zip=user.nextLine();
					
					if(!zip.equals("N"))
					{
						cust.setZip(zip);
					}
					cust.editAddress();
					
					boolean check4=true;
					while(check4==true)
					{
						System.out.println("Press (1) to search for another customer or (2) to exit.");
						String input2=user.nextLine();
						
						if(input2.equals("1"))
						{
							check4=false;
							check3=false;
						}
						else if(input2.equals("2"))
						{
							check4=false;
							check3=false;
							check=false;
						}
						else
						{
							System.out.println("That is an invalid input. Please try again.");
						}
					}
					
					
				}
				else if(input.equals("3"))
				{
					check=false;
					check3=false;
				}
				else
				{
					System.out.println("That is an invalid input. Please try again.");
				}
				
				
			}
			
			
			
			
			
			
		}
		user.close();
		System.out.println("Thanks for using this application! Have a good day!");
	}
	
	public static boolean checkIDs(int id, ArrayList<String> ids)
	{
		if(!ids.isEmpty())
		{
			for(int i=0; i<ids.size();i++)
			{
				if(id==Integer.parseInt(ids.get(i)))
				{
					
					return false;
				}
				
			}
			return true;
		}
		else
		{
			return true;
		}
		
	}
	
	
	
	
	public static Customer createCust(Scanner user)
	{
		System.out.println("Please input desired title.  For example 'Mr.'");
		String title=user.nextLine();
		
		System.out.println("Please input desired first name");
		String first=user.nextLine();
		
		System.out.println("Please input desired last name");
		String last=user.nextLine();
		
		System.out.println("Please input desired email");
		String email=user.nextLine();
		
		System.out.println("Please input desired street Address");
		String add=user.nextLine();
		
		System.out.println("Please input desired city");
		String city=user.nextLine();
		
		System.out.println("Please input desired state");
		String state=user.nextLine();
		
		System.out.println("Please input desired zip code");
		String zip=user.nextLine();
		
		System.out.println("Please input your job name, such as 'Software Engineer'");
		String position=user.nextLine();
		
		System.out.println("Please input the company in which you are employed");
		String company=user.nextLine();
		
		Customer cust = new Customer(title,first,last,add,city,state,zip,email,position,company);
		return cust;
		
		
	}
	
	
	
	
	
	
	
	

}
