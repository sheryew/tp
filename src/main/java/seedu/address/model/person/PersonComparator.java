package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

/**
 * Comparator to sort AddressBook based on given parameter.
 */
public class PersonComparator implements Comparator<Person> {
    public static final String SORT_ADDRESS = "address";
    public static final String SORT_CLAIM = "claim";
    public static final String SORT_DEPARTMENT = "dep";
    public static final String SORT_DOB = "dob";
    public static final String SORT_EMAIL = "email";
    public static final String SORT_NAME = "name";
    public static final String SORT_PHONE = "phone";
    public static final String SORT_SALARY = "salary";

    private final String param;

    /**
     * Initializes PersonComparator.
     * @param param parameter to sort
     */
    public PersonComparator(String param) {
        requireNonNull(param);
        this.param = param.trim();
    }

    @Override
    public int compare(Person a, Person b) {
        switch (param) {
        case PersonComparator.SORT_NAME:
            return a.getName().compareTo(b.getName());
        case PersonComparator.SORT_PHONE:
            return a.getPhone().compareTo(b.getPhone());
        case PersonComparator.SORT_EMAIL:
            return a.getEmail().compareTo(b.getEmail());
        case PersonComparator.SORT_ADDRESS:
            return a.getAddress().compareTo(b.getAddress());
        case PersonComparator.SORT_SALARY:
            return a.getSalary().compareTo(b.getSalary());
        case PersonComparator.SORT_CLAIM:
            return a.getClaimBudget().compareTo(b.getClaimBudget());
        case PersonComparator.SORT_DEPARTMENT:
            return a.getDepartment().compareTo(b.getDepartment());
        case PersonComparator.SORT_DOB:
            return a.getDob().compareTo(b.getDob());
        default:
            throw new IllegalArgumentException();
        }
    }
}
