package ro.netex.alex.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }

    public List<Customer> findAll() {
        return findAll(null);
    }

    public List<Customer> findAll(String stringFilter) {
        List<Customer> customers = repository.findAll();
        List<Customer> arrayList = new ArrayList<>();
        for (Customer contact : customers) {
            boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                    || contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
            if (passesFilter) {
                arrayList.add(contact);
            }
        }
        Collections.sort(arrayList, new Comparator<Customer>() {

            @Override
            public int compare(Customer o1, Customer o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        return arrayList;
    }

    public void delete(Customer value) {
        repository.deleteById(value.getId());
    }

    public void save(Customer entry) {
        repository.save(entry);
    }

    public void populate() {
        final String[] names = new String[]{"Gabrielle Patel", "Brian Robinson", "Eduardo Haugen",
                "Koen Johansen", "Alejandro Macdonald", "Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson",
                "Emily Stewart", "Corinne Davis", "Ryann Davis", "Yurem Jackson", "Kelly Gustavsson",
                "Eileen Walker", "Katelyn Martin", "Israel Carlsson", "Quinn Hansson", "Makena Smith",
                "Danielle Watson", "Leland Harris", "Gunner Karlsen", "Jamar Olsson", "Lara Martin",
                "Ann Andersson", "Remington Andersson", "Rene Carlsson", "Elvis Olsen", "Solomon Olsen",
                "Jaydan Jackson", "Bernard Nilsen"};
        Random r = new Random(0);
        for (String name : names) {
            String[] split = name.split(" ");
            Customer c = new Customer();
            c.setFirstName(split[0]);
            c.setLastName(split[1]);
            c.setStatus(CustomerStatus.values()[r.nextInt(CustomerStatus.values().length)]);
            c.setBirthDate(LocalDate.now().minusDays(r.nextInt(365 * 100)));
            repository.save(c);
        }
    }
}