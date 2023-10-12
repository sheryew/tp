package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Leave;
import seedu.address.model.person.Money;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_SALARY = "10000";
    public static final String DEFAULT_BUDGET = "2500";
    public static final String DEFAULT_DEPARTMENT = "Engineering";
    public static final String DEFAULT_DOB = "2000-01-01";
    public static final String DEFAULT_LEAVE = "000000000000";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Money salary;
    private Money claimBudget;
    private Department department;
    private Birthday dob;
    private Leave leave;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        salary = new Money(DEFAULT_SALARY);
        claimBudget = new Money(DEFAULT_BUDGET);
        department = new Department(DEFAULT_DEPARTMENT);
        dob = new Birthday(DEFAULT_DOB);
        leave = new Leave(DEFAULT_LEAVE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        salary = personToCopy.getSalary();
        claimBudget = personToCopy.getClaimBudget();
        department = personToCopy.getDepartment();
        dob = personToCopy.getDob();
        leave = personToCopy.getLeave();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code Person} that we are building.
     */
    public PersonBuilder withSalary(String salary) {
        this.salary = new Money(salary);
        return this;
    }

    /**
     * Sets the {@code ClaimBudget} of the {@code Person} that we are building.
     */
    public PersonBuilder withClaimBudget(String budget) {
        this.claimBudget = new Money(budget);
        return this;
    }

    /**
     * Sets the {@code Department} of the {@code Person} that we are building.
     */
    public PersonBuilder withDepartment(String department) {
        this.department = new Department(department);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code Person} that we are building.
     */
    public PersonBuilder withDob(String dob) {
        this.dob = new Birthday(dob);
        return this;
    }

    /**
     * Sets the {@code Leave} of the {@code Person} that we are building.
     */
    public PersonBuilder withLeave(String leave) {
        this.leave = new Leave(leave);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, salary, claimBudget, department, dob);
    }

}
