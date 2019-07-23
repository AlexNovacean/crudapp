package ro.netex.alex.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
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
}