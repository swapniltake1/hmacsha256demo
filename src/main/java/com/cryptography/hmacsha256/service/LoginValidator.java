package com.cryptography.hmacsha256.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cryptography.hmacsha256.helper.FinalKey;

@Component
public class LoginValidator {

	   @Autowired
	    private FinalKey finalkey;
	    
	    private String oldkey;



		public void setOldkey(String oldkey) {
			System.out.println("old key set inn login validator:: "+oldkey);
			this.oldkey = oldkey;
		}




	public boolean validateLogin(String receivedSignature) {
		   
		   System.out.println("Inn validate login block");
		    
		   System.out.println("old Key:: "+oldkey);
		   System.out.println("receivedSignature:: "+receivedSignature);
		   
            if(oldkey.equals(receivedSignature)) {
            	
            	System.out.println("key matched succesfully !!! ");
            	
            	return true;
            }
            System.out.println("Key Not Matched !!");
            
		    return false;
		}

}

