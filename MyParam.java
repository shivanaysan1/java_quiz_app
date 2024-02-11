package examform;

public class MyParam {
    private static String candidateName;
    private static String nationality;
    private static String gender;
    private static String age;

    public static String getName() {
        return candidateName;
    }

    public static void setName(String n) {
        candidateName = n;
    }
    
    public static String getNationality() {
        return nationality;
    }
    
    public static void setNationality(String nt) {
        nationality = nt;
    }
   
    public static String getAge() {
        return age;
    }
    
    public static void setAge(String a) {
        age = a;
    }
    
    public static String getGender() {
        return gender;
    }
    
    public static void setGender(String g) {
        gender = g;
    }  
}