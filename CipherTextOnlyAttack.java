package rotor96Crypto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class CipherTextOnlyAttack {
	
    public static void main(String[] args) {
    	
    	
        String ctext = "?Uv(x4+5n7yPC`B\\y&F(Lx!vI{!,C}dB[ZpkreC;{}{?Vk&RIk![YTs:v!S&D YUH<JMM!=)?'JedHnyu.|iDDi3A6FyO~EO*[HbjUnwi4C`cvjPQSe,A<`'AdY./,ix5l9xC~IK ?%gs_;HchYeVauCvikO?Y7<&|T3 0%]3Jea^Ra?.OlrAqn!&g%f)lhOi'%jP:;wUy{[6LjP!fi\\M#`m{VP%[(Va(87/2D{nzvF?FUdpcy]%#IEX<ULA7B\";E}3^;@Wp9)YCNy?Z|c;U)fpcR|E.;9UXJj6.%~L*4ml@Lv&\\w#vIyT[9]dd3;21[|{2";
        String fileofwords = "C:/Users/pc/eclipse-workspace/crypto/src/rotor96Crypto/passwords.txt";
        HashSet<String> Dict = new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileofwords));
            String password;
            while ((password = reader.readLine()) != null) {
            	Dict.add(password);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String decryptedTextMaxEnglishChars = "";
        String keyUsed = "";
        int MaximumEnglishCharacters = -1;
       

        for (String key : Dict) {
            String decryptedText = Rotor96Crypto.encdec(Rotor96Crypto.DEC, key, ctext);
            int numEnglishChars = countEnglishChars(decryptedText);
            if (numEnglishChars > MaximumEnglishCharacters) {
            	MaximumEnglishCharacters = numEnglishChars;
                decryptedTextMaxEnglishChars = decryptedText;
                keyUsed = key;
            }
        }

        System.out.println("Decrypted text with highest number of English characters:");
        System.out.println(decryptedTextMaxEnglishChars);
        System.out.println("Key used: " + keyUsed);

        // Experiment to find out how many ciphertext letters were needed to decode the message unambiguously
        String Part= ctext.substring(0, 10); // use first 10 letters as partial ciphertext
        int numberofchars = 0;
        String decryptedText = "";
        while (decryptedText.isEmpty()) {
            for (String key : Dict) {
                decryptedText = Rotor96Crypto.encdec(Rotor96Crypto.DEC, key, Part);
                if (isEnglish(decryptedText)) {
                    break;
               
                }
           
            
            }
            
            
            numberofchars++;
            Part = ctext.substring(0, 10 + numberofchars);
        }

        System.out.println("Number of ciphertext letters for unambiguous decoding: " + numberofchars);
    }

    private static int countEnglishChars(String text) {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (isEnglishChar(c)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isEnglishChar(char c) {
    	
    	
           return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')
        		   || c == ' '
        		   || c == ',' 
        		   || c == '.' 
        		   || c == '?' 
        		   || c == '!';
           
           
           
    }
    
    
    private static boolean isEnglish(String text) {
    	
    	
    	
    
        int EnglishCounts = countEnglishChars(text);
        
        
        double englishPercet = (double) EnglishCounts / text.length();
        
        
        return englishPercet >= 0.7;
    }
}
