public class BeachVacation extends Vacation{
   //Constants
   public static final double INSURANCE_RATE = 9.50;
   public static final double INSURANCE_RATE_EXTRA = 4.75;
   
   public static final double BASE_COST_SMALL_FAMILY = 196.0;
   public static final double BASE_COST_MEDIUM_FAMILY = 306.0;
   public static final double BASE_COST_LARGE_FAMILY = 524.0;
   
   public static final double SMALL_FAMILY_MIN_SIZE = 1;
   public static final double SMALL_FAMILY_MAX_SIZE = 3;
   
   public static final double MEDIUM_FAMILY_MIN_SIZE = 4;
   public static final double MEDIUM_FAMILY_MAX_SIZE = 7;
   
   public static final double LARGE_FAMILY_MIN_SIZE = 8;
   public static final double LARGE_FAMILY_MAX_SIZE = 10;
   
   public static final double INSURANCE_RATE_NUM_FAMILY_MEMBER_THRESHOLD = 5;
   
   //Instance Variables
   private String hurricaneInsurance;
   private String[] familyMembers;
   
   //Constructors
   
   /* The 'super' keyword indicates that the constructor will call the
   superclass and execute the code within it as well.
   
   Initializes the family members array for the BeachVacation constructor.
   
   */
   public BeachVacation(String empName, String location, String zipCode, int numFamMembers)
   {
	  super(empName, location, zipCode, numFamMembers);
     this.familyMembers = new String[MAX_NUM_MEMBERS];
   }
   
   //Accessors
   public String getHurricaneInsurance()
   {
      return this.hurricaneInsurance;
   }
   
   //Mutators
   public boolean setHurricaneInsurance(String hurricaneInsurance)
   {
      if(hurricaneInsurance.equalsIgnoreCase("y") || (hurricaneInsurance.equalsIgnoreCase("n")))
      {
         this.hurricaneInsurance=hurricaneInsurance;
         return true;
      }else{
         return false;
      }
   }
   
   
   //Special Purpose Methods
   
   /*
   Implements the abstract method in the Vacation class 'calculateCost'
   
   Depending on the number of family members, the calculation will be different.
   If the employee wishes to have hurricane insurance, then the cost will be even higher.
   
   The hurricane insurance adds an additional amount of money to the total.
   
   @return total the total amount of money it costs for a single vacation
   */
   public double calculateCost(){
      double total = 0.0;
      
      
      if(this.getNumFamMembers() >= SMALL_FAMILY_MIN_SIZE && this.getNumFamMembers() <= SMALL_FAMILY_MAX_SIZE)
      {
         total+=BASE_COST_SMALL_FAMILY;
      }else if(this.getNumFamMembers() >= MEDIUM_FAMILY_MIN_SIZE && this.getNumFamMembers() <= MEDIUM_FAMILY_MAX_SIZE)
      {
         total+=BASE_COST_MEDIUM_FAMILY;
      }else if(this.getNumFamMembers() >= LARGE_FAMILY_MIN_SIZE && this.getNumFamMembers() <= LARGE_FAMILY_MAX_SIZE)
      {
         total+=BASE_COST_LARGE_FAMILY;
      }
      
      if(this.getHurricaneInsurance().equalsIgnoreCase("y"))
      {
         if(this.getNumFamMembers() > INSURANCE_RATE_NUM_FAMILY_MEMBER_THRESHOLD)
         {
            total+= (this.getNumFamMembers() * (INSURANCE_RATE + INSURANCE_RATE_EXTRA));
         }else if(this.getNumFamMembers() <= INSURANCE_RATE_NUM_FAMILY_MEMBER_THRESHOLD && this.getNumFamMembers() > 0)
         {
            total+= (this.getNumFamMembers() * INSURANCE_RATE);
         }
      }
      
      return total;
   }
   
   
   /*
   
   Returns a string representation of the object
   Calls the Vacation toString() method.
   
   @return the information about the vacation
   */

   public String toString(){
      return super.toString();
   }
}
   
   