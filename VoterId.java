package myPackage;
import java.util.*;

enum Gender {
	MALE,FEMALE,OTHER
}

class InvalidException extends Exception {
	public InvalidException (String msg) {
		super (msg);
	}
}

public class VoterId {
	boolean is_eligible, eligible_flag;
	static int number_of_applicant = 0 ;
	public static Map<Integer, List<Object>> voterList = new HashMap<>();
	
	public VoterId () {
		System.out.println("WELCOME");
		
	}
	
	public VoterId (String name, int age, String gender) throws InvalidException {
		is_eligible = eligibilityCheck(age, gender);
		if (is_eligible) {
			registerVoterId (name, age, gender);
		}
		else {
			throw new InvalidException ("Invalid age or gender !");
		}
	}
	
	public void getApplicantDetails() throws InvalidException {
		String applicant_name, applicant_gender;
		int applicant_age;
		Scanner sc = new Scanner (System.in);
		System.out.println("Enter the applicant's name :");
		applicant_name = sc.nextLine();
		System.out.println("Enter the applicant's age ( greater than 17 ):");
		applicant_age = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the applicant's gender ( male / female / other ) :");
		applicant_gender = sc.nextLine();
		is_eligible = eligibilityCheck(applicant_age, applicant_gender);
		if (is_eligible) {
			registerVoterId (applicant_name, applicant_age, applicant_gender);
		}
		else {
			throw new InvalidException ("Invalid age or gender !");
		}
	}
	
	public void getVoterList (){
		Iterator<Integer> itr = voterList.keySet().iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
	}
	
	public void registerVoterId (String applicant_name, int applicant_age, String applicant_gender) {
		number_of_applicant++;
		List<Object> applicantDetails = new ArrayList <> ();
		applicantDetails.add(applicant_name);
		applicantDetails.add(applicant_age);
		applicantDetails.add(applicant_gender);
		voterList.put(number_of_applicant, applicantDetails);
	}
	
	public boolean eligibilityCheck (int age, String gender) {
		if (age > 17) {
			String gender_to_upper = gender.toUpperCase();
			Gender[] genders = Gender.values();
			System.out.println (genders);
			
		}
		return true;
		
		/*	for (Gender gender_values: genders) {
				if (gender_values.equals(gender_to_upper)) {
					eligible_flag = true;
				}
				else {
					eligible_flag = false;
				}
			}
			return eligible_flag ;
			
		}
		else {
			return false;
		} */
	}
	
	public static void main(String[] args) {
		try {
			boolean option_flag = true;
			int option;
			Scanner sc = new Scanner (System.in);
			VoterId applicant1 = new VoterId("ABC",19,"MALE");
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
				break;
				default : System.out.println("No such option!");
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			System.out.println("Thank You!");
		}
		
	}

}
