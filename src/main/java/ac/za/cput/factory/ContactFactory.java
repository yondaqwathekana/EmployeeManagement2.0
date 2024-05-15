package ac.za.cput.factory;

import ac.za.cput.domain.Contact;
import ac.za.cput.util.Helper;

public class ContactFactory {

    public static Contact buildContact(String email, String mobile, String workTelephone) {
        Long mobilePhone = Long.valueOf(mobile);
        Long workPhone = Long.valueOf(workTelephone);

        if (!Helper.isValidEmail(email) || Helper.isNullOrEmpty(mobile) && mobile.length() != 10 || Helper.isNullOrEmpty(workTelephone) && workTelephone.length() != 10)
            return null;
        return new Contact.Builder().setEmail(email).setMobile(mobile).setWorkTelephone(workTelephone).build();
    }
}
