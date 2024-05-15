package ac.za.cput.repository;

import ac.za.cput.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    public Contact findContactByEmailOrMobile(String email, String mobile);
    public Contact removeContactByEmail(String email);
}
