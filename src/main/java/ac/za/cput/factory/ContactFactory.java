package ac.za.cput.factory;

import ac.za.cput.domain.Contact;
import ac.za.cput.util.Helper;

public class ContactFactory {
    public static Contact buildContact(String email, String mobile, String workTelephone){
        if(!Helper.isValidEmail(email)) {
            System.err.println("Incorrect email format. Enter correct email address");
            return null;
        }
        if(Helper.isNullOrEmpty(mobile) || Helper.isNullOrEmpty(workTelephone))
            return null;

        return new Contact.Builder().setEmail(email).setMobile(mobile).setWorkTelephone(workTelephone).build();
    }
}
