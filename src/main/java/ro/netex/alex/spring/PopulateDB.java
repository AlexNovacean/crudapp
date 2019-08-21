package ro.netex.alex.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PopulateDB implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CustomerService customerService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent applicationReadyEvent) {
        if (customerService.findAll().isEmpty()) customerService.populate();
    }
}
