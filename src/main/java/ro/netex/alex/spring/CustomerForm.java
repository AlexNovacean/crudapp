package ro.netex.alex.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import java.util.function.Consumer;


public class CustomerForm extends FormLayout {

    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private ComboBox<CustomerStatus> status = new ComboBox<>("Status");
    private DatePicker birthDate = new DatePicker("Birth Date");
    private Binder<Customer> binder = new Binder<>(Customer.class);

    private Button save = new Button("Save");
    private Button delete = new Button("Delete");

    private Consumer<Customer> onSave;
    private Consumer<Customer> onDelete;



    public CustomerForm(Consumer<Customer> onSave, Consumer<Customer> onDelete) {
        this.onSave = onSave;
        this.onDelete = onDelete;
        status.setItems(CustomerStatus.values());

        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        add(firstName, lastName, status, birthDate, buttons);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    public void setCustomer(Customer customer) {
        binder.setBean(customer);

        if (customer == null) {
            setVisible(false);
        } else {
            setVisible(true);
            firstName.focus();
        }
    }

    private void save() {
        Customer customer = binder.getBean();
        onSave.accept(customer);
        setCustomer(null);
    }

    private void delete() {
        Customer customer = binder.getBean();
        onDelete.accept(customer);
        setCustomer(null);
    }
}
