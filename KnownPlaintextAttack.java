package rotor96Crypto;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KnownPlaintextAttack {
    
    public static void main(String[] args) {
        String ctext = "^*?z9>Y&.q!k$HK[xr)P`G@'I+`8KQ'b$n=L?u&ziF:6B8}PDuxM x0Yi}[W5/E3(B_V-|s[_^jv^EdZR0gvQsb?V$<o~w3GML30R";
        String knownPlaintext = "Th";
        List<String> keys = GetKeys();
         String bestKey = null;
        String bestMessage = null;
        int Bscore = Integer.MIN_VALUE;
        
     for (String key : keys) {
            String message = Rotor96Crypto.encdec(Rotor96Crypto.DEC, key, ctext);
             if (message.startsWith(knownPlaintext)) {
                int score = ScoreMess(message);
                if (score > Bscore) {
                    Bscore = score;
                    bestKey = key;
                    bestMessage = message;
                }
            }
        }
        
        System.out.println("Key: " + bestKey);
        
        System.out.println("Message: " + bestMessage);
        
    }
    
    private static List<String> GetKeys() {
    	
    	
    	
        List<String> keys = new ArrayList<>();
        
        try 
        {
        	
        	File file = new File("C:/Users/pc/eclipse-workspace/crypto/src/rotor96Crypto/passwords.txt");

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String password = scanner.nextLine();
                keys.add(password);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("passwords.txt file not found!");
        }
        return keys;
    }

    
    private static int ScoreMess(String message) {
    	
    	int score = 0;
    	for (int i = 0; i < message.length(); i++) {
    	char c = message.charAt(i);
    	if (c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
    	score++;
    	}
    	}
    	return score;
    	}
}













