package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The employee index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d employees listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS = "Multiple values specified for the "
            + "following single-valued field(s): ";
    public static final String MESSAGE_OVER_CLAIM = "Claim is REJECTED as the amount is greater than "
            + "the funds the Employee currently has!";
    public static final String MESSAGE_EMPTY_DEPARTMENT_FILTER = "Department name cannot be empty!";
    public static final String MESSAGE_EMPTY_MONTH_LEAVE_FILTER = "Month(s) cannot be empty!";

    public static final String MESSAGE_LIST_COMMAND_FORMAT = "list: "
            + "Lists all the details of an organization’s employees, "
            + "or list all employees of a specified department (case-insensitive).\n"
            + "Parameters: [d/DEPARTMENT] (optional)\n"
            + "Example: list (to list all employees) or "
            + "list d/Engineering (to list all employees in Engineering department)";

    public static final String MESSAGE_VIEW_LIST_COMMAND_FORMAT = "view_list: "
            + "Lists all employees with planned leave, optionally within "
            + "specified department or months.\n"
            + "Parameters: [d/DEPARTMENT] [m/MONTH(S)] (optional)\n"
            + "Format: MONTHS must be integers separated by commas without spaces. "
            + "1: Jan, 2: Feb, ..., 12: Dec.\n"
            + "Positive MONTHS add leaves on the specified months and negative MONTHS remove them.\n"
            + "Example: view_list d/Engineering m/1,5";

    public static final String MESSAGE_MONTHS_SPACES_DETECTED = "Spaces detected in your MONTHS.\n";
    public static final String MESSAGE_INVALID_MONTH = "Please check your MONTHS. Invalid month provided.\n";
    public static final String MESSAGE_LIST_SUCCESS = "Listed all employees (%1$d)";
    public static final String MESSAGE_FILTER_SUCCESS = "Listed filtered employees (%1$d)";
    public static final String MESSAGE_BIRTHDAY_FAILURE = "No employees have birthdays in this month.";
    public static final String MESSAGE_VIEW_LEAVE_SUCCESS = "Employees with specified leave(s) listed (%1$d)";
    public static final String WRONG_EXPORT_FILE_NAME_FAILURE = "Kindly provide one filename for this set of data.\n"
            + "Example: export engineering_dept";
    public static final String MESSAGE_NO_ARGUMENTS_EXPECTED = "\"%s\" command should not have any arguments";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Address: ")
                .append(person.getAddress())
                .append("; Salary: ")
                .append(person.getSalary())
                .append("; Claim budget: ")
                .append(person.getClaimBudget())
                .append("; Department: ")
                .append(person.getDepartment())
                .append("; DOB: ")
                .append(person.getDob());
        return builder.toString();
    }

}
