public abstract class Vacation { 
   //Constants
   public static final int MAX_NUM_EMPLOYEES = 37;
   public static final int MAX_NUM_MEMBERS = 10;
   
   //Static variable
   private static int totalFamilies = 0;
   
   //Instance Variables
   private String empName;
   private String location;
   private String zipCode;
   private int numFamMembers;
   private String[] familyMembers;
   private String familyMember;
   
   //Constructors
   
   /*
   Increments the total number of families entered
   each time that the constructor is instantiated
   
   Sets instance variables when instantiated by subclasses
   
   */
   public Vacation(String empName, String location, String zipCode, int numFamMembers){
	  this.empName = empName;
	  this.location = location;
	  this.zipCode = zipCode;
	  this.numFamMembers = numFamMembers;
      this.familyMembers = new String[MAX_NUM_MEMBERS];
      ++totalFamilies;
   }
   
   //Accessors
   public String getEmpName(){
      return this.empName;
   }
   
   public String getLocation(){
      return this.location;
   }
   
   public String getZipCode(){
      return this.zipCode;
   }
   
   public int getNumFamMembers(){
      return this.numFamMembers;
   }
   
   public String[] getFamilyMembers(){
      String[] tempArray = new String[this.familyMembers.length];
      for (int i = 0; i < this.familyMembers.length; i++)
      {
         tempArray[i] = this.familyMembers[i];
      }
      
      return tempArray;
   }
   
   public static int getTotalFamilies(){
      return totalFamilies;
   }
   
   
   //Mutators
   public boolean setEmpName(String empName)
   {
      if(!empName.equals(""))
      {
         this.empName = empName;
         return true;
      }else
      {
         return false;
      }
   }
   
   public boolean setLocation(String location)
   {
      if(!location.equals(""))
      {
         this.location = location;
         return true;
      }else
      {
         return false;
      }
   }
   
   
   
   /*
   Sets the zip code for an employee.
   The zipcode must follow the format yyyyy-yyyy,
   where y is a number.
   
   @param zipCode the zipcode of the employee going on vacation
   
   @return boolean true or false value
   */ 
   
   public boolean setZipCode(String zipCode)
   {
      String tempZipCode = zipCode.replace("-", "");
         
      boolean validZipCode = true;
      
      if (tempZipCode.length() != 9)
      {
         validZipCode = false;
      }
      
      for (int i = 0; i < tempZipCode.length(); i++) {
         if (!Character.isDigit(tempZipCode.charAt(i))) {
            validZipCode = false;
            break;
         }
      }
      
     if(validZipCode){
         this.zipCode=tempZipCode;
         return true;
      }else{
         return false;
      }
   }
   
   
   /*
   Sets the number of family members in a single vacation party.
   The total number of family members must be within the range,
   otherwise it will return false.
   
   @param numFamMembers the number of family members in a single
   vacation party
   
   @return boolean true or false value
   
   */
   
   public boolean setNumFamMembers(int numFamMembers)
   {
      if(numFamMembers > 0 && numFamMembers <= MAX_NUM_MEMBERS)
      {
         this.numFamMembers = numFamMembers;
         return true;
      }else{
         return false;
      }
   }
   
   
   /*
   Abstract method calculateCost returns a double
   Implemented by subclasses (BeachVacation and SkiVacation)
   
   Each class has a different way to implement this method, thus
   the abstraction.
   
   @return the total cost for a vacation
   */
   
   public abstract double calculateCost();
   
   
   /*
   
   Returns a string representation of the object
   
   @return the information about the vacation
   */
   public String toString()
   {
      return "\nEmployee Name: " + this.getEmpName() 
      + "\nLocation: " + this.getLocation()
      + "\nZip Code: " + this.getZipCode() 
      + "\nNumber of family members: " + this.getNumFamMembers();
      
   }
}