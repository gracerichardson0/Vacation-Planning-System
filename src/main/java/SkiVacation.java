public class SkiVacation extends Vacation { 
   //Constants
   public static final int[] rating = {2, 3, 4};
   public static final double FLAT_FEE = 308;
   public static final double SKI_LESSONS = 49.95;
   public static final int MIN_NUM_STARS = 2;
   public static final int MAX_NUM_STARS = 4;
   
   //Instance Variables
   private String skiLesson;
   private int vacationRating;
   private int delete;
   private String[] skiLessons;
   private String[] familyMembers;
   
   //Constructors
   
   /* The 'super' keyword indicates that the constructor will call the
   superclass and execute the code within it as well.
   
   Initializes the family members array for the SkiVacation constructor.
   
   Initializes the ski lesson decision array for the SkiVacation constructor
   */
   
   public SkiVacation(String empName, String location, String zipCode, int numFamMembers){
	   super(empName, location, zipCode, numFamMembers);
      this.familyMembers = new String[MAX_NUM_MEMBERS];
      this.skiLessons = new String[MAX_NUM_MEMBERS];
   }
   
   //Accessors
   public int getVacationRating(){
      return this.vacationRating;
   }
   
   public String[] getSkiLesson(){
      String[] temporaryArray = new String[this.skiLessons.length];
      for (int i = 0; i < this.skiLessons.length; i++)
      {
         temporaryArray[i] = this.skiLessons[i];
      }
      
      return temporaryArray;
   }
   
   //Mutators
   public boolean setRating(int vacationRating){
      boolean isValid = false;
      int i=0;
      
      while(!isValid && i<rating.length) {
         if(rating[i]==vacationRating) {
            this.vacationRating=vacationRating;
            isValid=true;
         }
         else {
            i++;
         }
      }
      
      return isValid;
   }
   
   
   public boolean setDelete(int delete){
      if(delete > 0){
         this.delete=delete;
         return true;
      }else{
         return false;
      }
   }
   /*
   Sets the family member's name for each family member
   in the array. If the user enters an empty string
   then the method will return a boolean false value.
   
   @param skiLesson the ski lesson decision for each family member
   
   @return boolean true or false
   
   */

   
   public boolean setFamilyMembers(String familyMember)
   {
      boolean valid = true;
      for (int i = 0; i < this.getNumFamMembers(); i++)
      {
         if(!familyMember.equals(""))
         {
            this.familyMembers[i] = familyMember;
            valid=true;
         }
      }
      if(valid)
      {
         return true;
      }else
      {
         return false;
      }      
   }
   
   /*
   Sets the ski lesson decision for each family member
   in the array. If the user enters a value other than
   'y' or 'n' then the method will return a boolean false
   
   @param skiLesson the ski lesson decision for each family member
   
   @return boolean true or false
   
   */
   
   public boolean setSkiLesson(String skiLesson) {
      boolean isValid = true;
      for (int i = 0; i < this.getNumFamMembers(); i++) {
         if(skiLesson.equalsIgnoreCase("y") || skiLesson.equalsIgnoreCase("n")){
            this.skiLessons[i] = skiLesson;
            isValid=true;
          }
      }
      if(isValid){
         return true;
      }else{
         return false;
      }      
   }
   
    

   
   
   //Special Purpose Methods
   /*
   This method returns the total cost of the SkiVacation.
   This class implements the calculateCost method differently;
   It takes into account the number of stars for the rating 
   of the vacation. Each rating has a different amount of money
   it costs. If the employee decides to want ski lessons,
   it will add to the total a specific amount.
   
   @return total the total amount of money it costs for a SkiVacation
   */
   
   public double calculateCost(){
      double total = 0.0;
      
      if(this.getVacationRating() >= MIN_NUM_STARS && this.getVacationRating() <= MAX_NUM_STARS){
         total+= (FLAT_FEE * this.getVacationRating());
      }
      
      for(int i = 0; i < this.getNumFamMembers(); i++){
         if(skiLessons[i].equalsIgnoreCase("y")){
            
            total+= SKI_LESSONS;
         }
      }
      
      return total;
   }
   /*
   Returns a string representation of the SkiVacation, returning all
   information involving the vacation. The method also returns the
   names of the family members, and their decisions for ski lessons.
   
   @return a string representation of the SkiVacation class
   */
   
   public String toString(){
      String listOfMembers = "";
         if (this.getNumFamMembers() > 0)
         {
            for (int i = 0; i < this.getNumFamMembers(); i++)
            {
               listOfMembers += (i+1) + ") " + this.familyMembers[i] + "\n";
            }
         }
            else
            {
               listOfMembers += "No Family Members Entered!";
            }

      String listOfLessons = "";
      if (this.getNumFamMembers() > 0)
         {
         for (int i = 0; i < this.getNumFamMembers(); i++)
            {
               listOfLessons += this.familyMembers[i] + ": " + this.skiLessons[i] + "\n";
            }
         }
            else
            {
               listOfLessons += "No Ski Lessons Chosen!";
            }
            
      return super.toString() 
      + "\nFamily Members: " + listOfMembers 
      + "\nSki Lessons: " + listOfLessons;
   }
}
   
   
   