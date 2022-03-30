package myPackage;
import java.util.*;

enum Gender {
	MALE(1),FEMALE(2),OTHER(3);
	private int genderId;
	
	Gender (int genderId)
	{
		this.genderId = genderId;
	}
	public static Gender getNameOfGender(int genderOption){

        for(Gender value : values()) {
            if(value.genderId == genderOption) {
                return value;
            }
        }
        return null;
    }   
}

class InvalidException extends Exception {
	public InvalidException (String msg) {
		super (msg);
	}
}

public class VoterId {
	boolean age_eligible;
	static int number_of_applicant = 0 ;
	public static Map<Integer, List<String>> voterList = new HashMap<>();
	
	public String getGender (int genderType) {
		Gender gender = Gender.getNameOfGender(genderType);
		return gender.name();
	}
	
	public boolean checkAge (int age) {
		if (age > 17) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void getVoterList () {	
		voterList.forEach(
                (key, value)->System.out.println("Applicant Id: " + key + " Applicant Details : " + value));		
	}
	
	public void getApplicantDetails() {
		try {	
			String applicant_name, gender_value;
			int applicant_age, applicant_gender;
			Scanner sc = new Scanner (System.in);
			System.out.println("Enter the applicant's name :");
			applicant_name = sc.nextLine().toUpperCase();
			do {
				System.out.println("Enter the applicant's age ( minimum age : 18 ):");
				applicant_age = sc.nextInt();
				if (checkAge(applicant_age)) {
					age_eligible = true;
					break;
				}			
				System.out.println("Please enter the valid age ");
			} while (!age_eligible);
			
			System.out.println("Enter the applicant's gender : \n1 - male \n2 - female \n3 - other ");
			applicant_gender = sc.nextInt();
			gender_value = getGender (applicant_gender);
		
			registerVoterId (applicant_name, applicant_age,gender_value);
		}
		catch (Exception e) {
			System.out.println(e + " Invalid input ! Please be carefull on data type while entering input ");
		}
	}
	
	public void registerVoterId (String applicant_name, int applicant_age, String applicant_gender) {
		number_of_applicant++;
		String ageStr = Integer.toString(applicant_age);
		List<String> applicantDetails = new ArrayList <String> ();
		applicantDetails.add(applicant_name);
		applicantDetails.add(ageStr);
		applicantDetails.add(applicant_gender);
		voterList.put(number_of_applicant, applicantDetails);
		System.out.println("Voter Id applied for "+applicant_name+" .His/Her Id is "+ number_of_applicant + ". Save it for future process. \n");
	}
	
	public static void main(String[] args) {
		try {
			boolean option_flag = true;
			int option;
			Scanner sc = new Scanner (System.in);
			VoterId newApplicant = new VoterId();					
			while(option_flag) {
				System.out.println("Please enter any one of the option below :\n1.Apply Voter Id. \n2.View Voter List. \n3.Exit");
				option = sc.nextInt();
				sc.nextLine();
				switch (option) {
					case 1: newApplicant.getApplicantDetails();
					break;
					case 2: newApplicant.getVoterList();
					break;
					case 3: option_flag = false;
							sc.close();
					break;
					default : System.out.println("No such option!");
				}
			}
		}
		catch (Exception e) {
			System.out.println("Invalid input ! Program Exited !");
		}
		finally {
			System.gc();
			System.out.println("Thank You!");
		}	
	}
}
