package ac.za.cput.service;

import ac.za.cput.domain.Contact;

import java.util.Set;

public interface IContactService extends IService<Contact, String>{
    public Set<Contact> getAll();
}
