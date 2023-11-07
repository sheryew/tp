package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Money salary;
    private final Money claimBudget;
    private final Department department;
    private final Birthday dob;
    private final Leave leave;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
            Money salary, Money claimBudget, Department dep, Birthday dob) {
        requireAllNonNull(name, phone, email, address, salary, claimBudget, dep, dob);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.claimBudget = claimBudget;
        this.department = dep;
        this.dob = dob;
        this.leave = new Leave();
    }

    /**
     * Constructs a {@code Person}.
     */
    public Person(Name name, Phone phone, Email email, Address address,
            Money salary, Money claimBudget, Department dep, Birthday dob, Leave leave) {
        requireAllNonNull(name, phone, email, address, salary, claimBudget, dep, dob);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.claimBudget = claimBudget;
        this.department = dep;
        this.dob = dob;
        this.leave = leave;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Money getSalary() {
        return salary;
    }

    public Money getClaimBudget() {
        return claimBudget;
    }

    public Department getDepartment() {
        return department;
    }

    public Birthday getDob() {
        return dob;
    }

    public Leave getLeave() {
        return leave;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons are the same.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && this.equals(otherPerson);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && dob.equals(otherPerson.dob)
                && address.equals(otherPerson.address)
                && (phone.equals(otherPerson.phone) || email.equals(otherPerson.email));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, salary, claimBudget, department, dob, leave);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("salary", salary)
                .add("claimBudget", claimBudget)
                .add("department", department)
                .add("dob", dob)
                .add("leave", leave)
                .toString();
    }

}
