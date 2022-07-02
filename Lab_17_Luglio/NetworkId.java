import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class NetworkId {
    static String findClass(String str){ 
        // Calculating first occurrence of '.' in str 
        int index = str.indexOf('.'); 
        // First octate in str in decimal form 
        String ipsub = str.substring(0,index); 
        int ip = Integer.parseInt(ipsub); 
        // Class A 
        if (ip>=1 && ip<=126) 
            return "A"; 
        // Class B 
        else if (ip>=128 && ip<=191) 
            return "B"; 
        // Class C 
        else if (ip>=192 && ip<223) 
            return "C"; 
        // Class D 
        else if (ip >=224 && ip<=239) 
            return "D"; 
        // Class E 
        else
            return "E"; 
    } 
  
    static String seprate(String str, String ipClass){ 
        // Initializing network and host empty 
        String network = "", host = ""; 
  
        if(ipClass == "A"){ 
            int index = str.indexOf('.'); 
            network = str.substring(0,index); 
            host = str.substring(index+1,str.length()); 
        }else if(ipClass == "B"){ 
            //Position of breaking network and HOST id 
            int index = -1;  
            int dot = 2;  
            for(int i=0;i<str.length();i++){ 
                if(str.charAt(i)=='.'){ 
                    dot -=1; 
                    if(dot==0){ 
                        index = i; 
                        break; 
                    } 
                } 
            } 
            network = str.substring(0,index); 
            host = str.substring(index+1,str.length()); 
        }else if(ipClass == "C"){ 
            //Position of breaking network and HOST id 
            int index = -1;  
            int dot = 3;  
            for(int i=0;i<str.length();i++){ 
                if(str.charAt(i)=='.'){ 
                    dot -=1; 
                    if(dot==0){ 
                        index = i; 
                        break;                      
                    } 
                } 
            } 
            network = str.substring(0,index); 
            host = str.substring(index+1,str.length()); 
        }else if(ipClass == "D" || ipClass == "E"){ 
            System.out.println("In this Class, IP address"+ 
            " is not divided into Network and Host IDs"); 
            return null; 
        } 
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] hash = digest.digest(host.getBytes(StandardCharsets.UTF_8));
        return network+"."+hash;
        //System.out.println("Network ID is "+network); 
        //System.out.println("Host ID is "+hash); 

}
}
