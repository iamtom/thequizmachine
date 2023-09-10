package tom.iamtom.thequizmachine.helpers;

import java.util.Random;

public class StringGenerator {
    
    private Random random;

    public StringGenerator() {
        random = new Random();
    }
    
    public String randomQuizCode(int requiredLength) {
        //used to generate a quiz code for users to easily access/share a quiz
        
        String acceptableChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        
        int upperLimit = acceptableChars.length() - 1;
        
        char[] characters = new char[requiredLength];
        
        for(int i = 0; i < requiredLength; i++) {
            int number = random.nextInt(upperLimit);
            
            characters[i] = acceptableChars.charAt(number);
            
        }
        
        String quizCode = String.valueOf(characters);
        
        return quizCode;
        
    }
    
}
