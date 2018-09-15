/**
  Name: Nicholas Richardson
  Date: 11/20/2016
  Course/Section: IT 206.DL1
  Assignment: Programming Assignment 8

  Description:
  
  This program is designed to track the vacations being offered as a reward
  to be purchased by employees from Sew What? Gifts and Knickknacks.
  There is a maximum of 37 employees at the company and each family
  can have up to 10 family members join on the vacation. There are two
  vacations the employees can choose which are 1)Beach House and 2) Ski Resort Vacations.
  
  The user will be presented with a menu showing them the following:
  
  1) Add Vacation
  2) Remove Vacation
  3) Print All Beach House Vacation Bills
  4) Print All Ski Resort Vacation Bills
  5) Print Vacation Summary
  6) Quit
  
  
  All employees have to enter the following information:
  
  1. Beach House or Ski Resort Vacation
  2. Employee Name
  3. Location
  4. Zip Code
  5. Number of Family members
  
  After entering all of the data, depending on which vacation type the employee
  has chosen, they will be asked specific questions unique to the vacation type itself.
  
  For Beach House vacations, the employee will have to answer if they want hurricane
  insurance or not.
  
  For Ski Resort vacations, the employee will have to enter the star rating of the vacation
  location, the names of their family members, and their decisions on if they want ski lessons or not.
  
  The user will then be able to choose another option on the menu after choosing to enter information for
  a vacation. Each Print option will display different information to the user, and finally after either
  they reach the maximum amount of vacations or they choose to quit, the program will exit.
  
  */

import javax.swing.JOptionPane;

public class ReserveVacation{
   //The maximum number of employees allowed
   public static final int MAX_NUM_EMPLOYEES = 37;
   
   public static void main(String[] args){
      //Initializes the vacation array with the max as the length
      Vacation aVacation[] = new Vacation[MAX_NUM_EMPLOYEES];
      Vacation oneVacation;
      boolean quit = false;
      
      
      /*
      The user will be presented with a menu after starting the 
      program and will also be shown the menu after choosing an option
      and successfully entering information for it, other than quit.
      */
      do {
         switch (getMenuOption()) {
         case 1:
			      oneVacation = getVacationType();
               addVacation(oneVacation);
			      aVacation[Vacation.getTotalFamilies() -1 ] = oneVacation;
               break;
			case 2:
               removeVacation(aVacation);
               break;
			case 3:
               printBeachHouse(aVacation);
               break;
			case 4:
               printSkiResort(aVacation);
               break;
            case 5:
               printSummary(aVacation);
               break;
				default:
               quit = true;
			}
		} while (!quit);
	}
   
   /*
   Displays the menu to the user with each choice they
   have available. If the user decides to enter a value other than
   the one listed on the menu, they will be reprompted.
   
   @return menuOption the choice that a user decides to make
   from the menu, which will be used as input for the switch/case statement
   in the main method to determine which method to execute.
   */
   
   private static int getMenuOption() {
		int menuOption;
		do {
         try {
            menuOption = Integer.parseInt(JOptionPane.showInputDialog(
               "Welcome to the Vacation Reservation System!\n"
               + "   1. Add Vacation\n"
               + "   2. Remove Vacation\n"
               + "   3. Print All Beach House Vacation Bills\n"
               + "   4. Print All Ski Resort Vacation Bills\n"
               + "   5. Print Vacation Summary\n"
               + "   6. Quit"
             ));
         }
         catch (NumberFormatException e) {
            menuOption = 0;
         }
         if (menuOption < 1 || menuOption > 6) {
            JOptionPane.showMessageDialog(null, "Invalid option! Please try again.");
          }
		} while (menuOption < 1 || menuOption > 6);
		return menuOption;
	}
   
   /*
   This method will prompt the user to select from the list of different options
   (Beach House, Ski Resort). Upon doing so, the user will be asked to enter information
   that is common to all vacations, and will be reprompted to enter valid information
   if inputs are invalid. Each Vacations shares the following inputs in common:
   employee name, location, zip code, and number of family members.
   
   @return Vacation the vacation class
   
   */
   
   private static Vacation getVacationType(){
	   Vacation vacation = null;
	   
      //Initializes the options array with the two choices for vacations
	   Object[] options = {"Beach House Vacation", "Ski Resort Vacation"};
      
      /*
      The user is asked to select the vacation they want to enter
      
      */
	   int vacationType = JOptionPane.showOptionDialog(null, "What type of vacation do you wish to enter?", "Enter Vacation", 
      JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	   
      /*
      Regardless of vacation type selected up above, the user will be asked to enter
      the employee name, location, zipcode, and number of family members.
      
      If the user enters any invalid inputs, they will be continuously reprompted
      for the specific input until it is valid.
      
      tempString(), getZipCode(), and getNumMembers() are used to validate
      the inputs.
      
      */
	   
	   String employeeName = tempString("Please enter the employee name: ");
	   String location = tempString("Please enter the location: ");
	   String zipCode = getZipCode("Please enter a valid zip code in the form of yyyyy-yyyy where y is a number: ");
	   int number = getNumMembers("Please enter the number of family members (including you) between 1-10 you wish to invite: ");
	   boolean valid = false;
      do{
         valid = vacation.setEmpName(JOptionPane.showInputDialog("Please enter the name: "));
         if(!valid){
            JOptionPane.showMessageDialog(null, "Error!");
         }
      }while(!valid);
      
      
      
      /*
      Depending on the selection the user made for the vacation, the
      relevant object will be created and set with the employee name,
      location, zipcode, and number of family members.
      
      */
	   switch(vacationType){
		   case 0: // Beach House Vacation
		   vacation = new BeachVacation(employeeName, location, zipCode, number);
		   break;
		   
		   case 1: // Ski Resort Vacation
		   vacation = new SkiVacation(employeeName, location, zipCode, number);
		   break;
		   
		   default:
		   vacation = null;
		   break;
	   }
	   return vacation;
   }
   
   
   /*
   This method populates the vacation that the user selected, based on which
   vacation they chose. Each vacation has a different amount of inputs. 
  
   The Beach House vacation must input if they want any hurricane insurance.
   
   Ski Resort vacations must input the rating of the vacation, the names 
   of family members, and their decisions on if they want ski lessons.
   
   @param vacation the vacation object that represents the current vacation
   being populated.
   
   */
   private static void addVacation(Vacation vacation){
   
   
   /*
   If the vacation object is an instance of the BeachVacation class,
   then the user will be prompted to enter if they want hurricane insurance
   or not. The user will be continuously prompted for input if they enter
   any invalid inputs.
   
   */
   if(vacation instanceof BeachVacation){
	   boolean hurricaneInsurance = false;
	   do{
		   try{
			   hurricaneInsurance = ((BeachVacation)vacation).setHurricaneInsurance(JOptionPane.showInputDialog("Please enter y or n if you want hurricane insurance or not: "));
		   }catch (NumberFormatException e){}
		   if(!hurricaneInsurance){
			   JOptionPane.showMessageDialog(null, "Error. Please enter y or n.");
		   }
	   } while(!hurricaneInsurance);
   }
   
   /*
   If the vacation object is an instance of the SkiVacation class,
   then the user will be prompted to enter the star rating, family member
   names, and ski lesson decisions. If any invalid inputs are entered, the user
   will be reprompted to enter inputs until they are valid.   
   */
   
   
   if(vacation instanceof SkiVacation){
	   boolean rating = false;
	   
	   do{
		   try{
			   rating = ((SkiVacation)vacation).setRating(Integer.parseInt(JOptionPane.showInputDialog("Please enter the rating of the vacation between 2 and 4 stars: ")));
		   }catch (NumberFormatException e){}
		   if(!rating){
			   JOptionPane.showMessageDialog(null, "Error. Please enter a valid rating.");
		   }
	   } while(!rating);
	   
	   int index = 0;
	   int index2 = 0;
	   
	   boolean familyMember = false;
	   boolean skiLesson = false;
	   
	   while(index < ((SkiVacation)vacation).getNumFamMembers()){
		   
	   
	   do{
		   familyMember = ((SkiVacation)vacation).setFamilyMembers(JOptionPane.showInputDialog("Please enter family member " + "# " + index + 1 + "'s name: "));
		   if(!familyMember){
			   JOptionPane.showMessageDialog(null, "Error. Please enter a valid family member name.");
		   }
	   }while(!familyMember);
	   
	   index++;
   }
   
		while(index2 < ((SkiVacation)vacation).getNumFamMembers()){
			do{
				skiLesson = ((SkiVacation)vacation).setSkiLesson(JOptionPane.showInputDialog(null, "Please enter Y or N for family member " + "# " + index2 + 1 + "'s decision on if they want ski lessons: "));
				if(!skiLesson){
					JOptionPane.showMessageDialog(null, "Error. Please enter a valid family member decision.");
				}
			} while(!skiLesson);
			
			index2++;
		}
   }
   }
	
  
  /*
    This method will validate the string inputs for the vacation by
    checking if they are equal to an empty string. If the user enters any empty
    strings, they will be reprompted.
    
    @param prompt the prompt that the user will read to input
    
    @return tempString the string that the user enters
    
  */
	private static String tempString(String prompt){
		String tempString = "";
		
		do{
			tempString = JOptionPane.showInputDialog(prompt);
			if(tempString.trim().equals("")){
				JOptionPane.showMessageDialog(null, "Error! Please enter a value!");
			}
		} while (tempString.trim().equals(""));
		
		return tempString;
	}
	
   
   /*
   This method validates the zip code that the user enters. If the user enters
   an invalid format for the zip code (anything other than yyyyy-yyyy) then the
   method will return the invalid string.
   
   @param validateZip the prompt for the user to read when inputting entry
   
   @return getZipCode the string containing the input
   
   */
	private static String getZipCode(String validateZip){
		String getZipCode = "";
		boolean valid = true;
		
			getZipCode = JOptionPane.showInputDialog(validateZip);
			String tempZipCode = getZipCode.replace("-", "");
			
			do{
				if(tempZipCode.length() !=5 && tempZipCode.length() !=9){
					valid = false;
				}
				
				for(int i = 0; i < tempZipCode.length(); i++){
					if(!Character.isDigit(tempZipCode.charAt(i))){
						valid = false;
						break;
					}
				}
				
				if(valid){
					JOptionPane.showMessageDialog(null, "Zip Code: " + getZipCode);
				}else{
               JOptionPane.showMessageDialog(null,"Error! You entered an invalid zipcode.");
           }
			}while(!valid);
			
		return getZipCode;
	}
	
   
   /*
   This method validates whether or not the input is valid for the number
   of family members. If the number of family members is lower than 0 or
   exceeds the maximum number of family members, then they will be reprompted
   until valid inputs are made.
   
   @param prompt2 the prompt that the user reads while making the input
   
   @return num the value that the user inputs for the number of family members

   */
	private static int getNumMembers(String prompt2){
		int num;
		do{
			try{
				num = Integer.parseInt(JOptionPane.showInputDialog(null, prompt2));
			}catch(NumberFormatException e){
				num = -1;
			}
			if(num <0 || num > Vacation.MAX_NUM_MEMBERS){
				JOptionPane.showMessageDialog(null, "Error! Please enter a valid number of family members.");
			}
		}while(num <0 || num > Vacation.MAX_NUM_MEMBERS);
		
		return num;
	}
	
   
   /*
   This method removes an object from the array determined by the user.
   The user will input the number corresponding to the element in the array
   with which they want to remove. If the element is equal to the number they input,
   then the element in the array will be removed, and the array will have its elements
   shifted up by one element.
   
   @param vacation the array of vacation objects
   
   */
   
   private static void removeVacation(Vacation[] vacation){
     //Only enter the statement if at least one family has been entered
     if(Vacation.getTotalFamilies() > 0){
         String list = "";
         int selection = 0;
         int tracker =0;
         
         
         /*
         Checks to see if there are any null or un-entered
         elements in the array, and only adds to the output string
         list if they are not null elements.
         */
         for(int i = 0; i < vacation.length; i++){
            if(vacation[i] != null){
               list += "\n" + i + ") " + vacation[i].getEmpName();
            }
         }
         
         /*
         Continuously prompts the user to enter a selection until it equals
         one of the elements in the array. If the user enters an invalid input,
         they will be shown an error message and be reprompted.
         */
         do{
            try{
               selection = Integer.parseInt(JOptionPane.showInputDialog("Please enter the name you would like to remove from the list: " + list));
            }catch(NumberFormatException e){
               selection = -1;
            }
            if(selection<0){
               JOptionPane.showMessageDialog(null, "Error! Please enter a valid element you would wish to remove.");
            }
        }while(selection<0);
         
         
         /*
         The loop will look through the entire array and will check
         to see if the number entered by the user is equal to any element that is in the array and 
         is not a null element. If the element has a vacation and the user has validly selected it,
         then the array will have its elements shifted up by one from where they were and the tracker
         variable will be incremented. If the tracker variable is less than 1, then it means that no elements were
         found that the user entered to remove, and the user will be reprompted.
         */
                  
         for(int j = 0; j < vacation.length; j++){
            if(selection ==j){ 
               for(int k = 0; k < vacation.length - 1; k++){
                  vacation[k] = vacation[k+1];
               }
               tracker++;
               break;
            }
         }
         
         //This will be true if the element was not successfully removed.
         if(tracker <=0){
            JOptionPane.showMessageDialog(null, "Error! Sorry, that employee does not exist at that element. Please try again.");
         }
        } 
       }
   /*
   This method will print the information and bill associated to each
   BeachHouse Vacation. The method will loop through the entire array of
   entered vacations, and will only concatenate the ones that are 
   SkiVacation vacations. Upon finding at least one SkiVacation,
   the user will be shown the report and will then be shown the menu.
   
   @param vacation the array of Vacation objects
   
   */
        
	private static void printBeachHouse(Vacation[] vacation){
		if (Vacation.getTotalFamilies() > 0){
			String report = "";
			
			for(int i = 0; i < Vacation.getTotalFamilies(); i++){
				if(vacation[i] instanceof BeachVacation){

				report += "[" + vacation[i].getClass().getName() + "]\n" + vacation[i].toString() + "\n"
					   + "Total Cost: " + String.format("$%.2f", vacation[i].calculateCost()) + "\n\n";
            }
			}
			
			JOptionPane.showMessageDialog(null, report);
		}else{
			JOptionPane.showMessageDialog(null, "There are no vacations.");
		}
	}
	
   
   /*
   This method will print the information and bill associated to each
   BeachHouse Vacation. The method will loop through the entire array of
   entered vacations, and will only concatenate the ones that are 
   SkiVacation vacations. Upon finding at least one SkiVacation,
   the user will be shown the report and will then be shown the menu.
   
   @param vacation the array of Vacation objects
   
   */
	private static void printSkiResort(Vacation[] vacation){
		if (Vacation.getTotalFamilies() > 0){
			String report = "";
			
			for(int i = 0; i < Vacation.getTotalFamilies(); i++){
				if(vacation[i] instanceof SkiVacation){
            
				report += "[" + vacation[i].getClass().getName() + "]\n" + vacation[i].toString() + "\n"
					   + "Total Cost: " + String.format("$%.2f", vacation[i].calculateCost())+ "\n\n";
            }
			}
			
			JOptionPane.showMessageDialog(null, report);
		}else{
			JOptionPane.showMessageDialog(null, "There are no vacations.");
		}
	}
	
   /*
   This method will print the total amount of money it will cost
   for every vacation added together, and will show the user the 
   total number of vacations currently entered.
   In the loop, the calculation will be different depending on which
   vacation the object is an instance of.
   Upon looping through all entered vacations, the user will be shown the report.
   
      
   @param vacation the array of Vacation objects
   
   */
   
	private static void printSummary(Vacation[] vacation){
		double total = 0;
		for(int i = 0; i < Vacation.getTotalFamilies(); i++){
			if(vacation[i] instanceof BeachVacation){
				total+= vacation[i].calculateCost();
			}
			
			if(vacation[i] instanceof SkiVacation){
				total+= vacation[i].calculateCost();
			}
		}
		JOptionPane.showMessageDialog(null, "Total number of vacations: " + Vacation.getTotalFamilies()
									+ "\nTotal Amount : " + String.format("$%.2f",total));
	}
	 
}
	 
	 
	 