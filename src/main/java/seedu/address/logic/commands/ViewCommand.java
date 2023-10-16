package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ViewCommandParser.ADDRESS_IDENTIFIER;
import static seedu.address.logic.parser.ViewCommandParser.BIRTHDAY;
import static seedu.address.logic.parser.ViewCommandParser.CLAIM_BUDGET;
import static seedu.address.logic.parser.ViewCommandParser.DEPARTMENT;
import static seedu.address.logic.parser.ViewCommandParser.EMAIL_IDENTIFIER;
import static seedu.address.logic.parser.ViewCommandParser.NAME_IDENTIFIER;
import static seedu.address.logic.parser.ViewCommandParser.PHONE_IDENTIFIER;
import static seedu.address.logic.parser.ViewCommandParser.SALARY_IDENTIFIER;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Returns ViewCommand Object based on what user's wants to view.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String WRONG_PREFIX = "Allowed Formats: n/, a/, e/, s/, b/, d/, dob/.\n"
            + "Example: view n/1,2";
    public static final String EXCESS_PREFIX = "Kindly input only one prefix.\n"
            + "Allowed Format: n/, a/, e/, s/, b/, dob/.\n"
                + "Example: view n/1,2";

    public final HashMap<String, List<Index>> references;

    /**
     * Initialises ViewCommand Object containing view operations on specific individuals.
     *
     * @param references HashMap object referencing view operations on Index individuals in list.
     */
    public ViewCommand(HashMap<String, List<Index>> references) {
        this.references = references;
    }

    /**
     * Returns CommandResult after successfully parsing user's view commands.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult which contains string representation of person's attribute.
     * @throws CommandException Exception thrown if index input from HR is beyond the pre-existing max list index.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        String finalString = "";

        for (String key: references.keySet()) {
            List<Index> indexList = references.get(key);
            String tempStr = "You are viewing " + key + ":\n";
            for (int i = 0; i < indexList.size(); i++) {
                int listIndex = indexList.get(i).getZeroBased();
                if (listIndex >= lastShownList.size()) {
                    throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
                }
                Person personToEdit = lastShownList.get(indexList.get(i).getZeroBased());
                HashMap<String, String> personRelevantInfo = generateArrInfo(personToEdit);
                int index = indexList.get(i).getOneBased();
                massAssertionFn(key);
                if (Objects.equals(key, NAME_IDENTIFIER)) {
                    tempStr += String.format("%s. %s.\n", index, personRelevantInfo.get(NAME_IDENTIFIER));
                } else if (Objects.equals(key, PHONE_IDENTIFIER)) {
                    tempStr += generateNiceStr(personRelevantInfo, index, PHONE_IDENTIFIER);
                } else if (Objects.equals(key, EMAIL_IDENTIFIER)) {
                    tempStr += generateNiceStr(personRelevantInfo, index, EMAIL_IDENTIFIER);
                } else if (Objects.equals(key, ADDRESS_IDENTIFIER)) {
                    tempStr += generateNiceStr(personRelevantInfo, index, ADDRESS_IDENTIFIER);
                } else if (Objects.equals(key, SALARY_IDENTIFIER)) {
                    tempStr += generateNiceStr(personRelevantInfo, index, SALARY_IDENTIFIER);
                } else if (Objects.equals(key, CLAIM_BUDGET)) {
                    tempStr += generateNiceStr(personRelevantInfo, index, CLAIM_BUDGET);
                } else if (Objects.equals(key, DEPARTMENT)) {
                    tempStr += generateNiceStr(personRelevantInfo, index, DEPARTMENT);
                } else {
                    tempStr += generateNiceStr(personRelevantInfo, index, BIRTHDAY);
                }
            }
            finalString += tempStr + "\n";
        }
        return new CommandResult(finalString);
    }

    /**
     * Assertion test to ensure key corresponds to all the existing identifiers.
     *
     * @param key User Input of the prefixes one wants to view.
     */
    public void massAssertionFn(String key) {
        assert Objects.equals(key, NAME_IDENTIFIER) || Objects.equals(key, PHONE_IDENTIFIER)
                || Objects.equals(key, EMAIL_IDENTIFIER) || Objects.equals(key, ADDRESS_IDENTIFIER)
                    || Objects.equals(key, SALARY_IDENTIFIER) || Objects.equals(key, CLAIM_BUDGET)
                        || Objects.equals(key, DEPARTMENT) || Objects.equals(key, BIRTHDAY);
    }

    /**
     * Returns HashMap which contains employee's information.
     *
     * @param person Person object that we want to retrieve information from.
     * @return HashMap, which informs us of the person's primary attributes.
     */
    private HashMap<String, String> generateArrInfo(Person person) {
        HashMap<String, String> map = new HashMap<>();
        map.put(NAME_IDENTIFIER, person.getName().toString());
        map.put(PHONE_IDENTIFIER, person.getPhone().toString());
        map.put(EMAIL_IDENTIFIER, person.getEmail().toString());
        map.put(ADDRESS_IDENTIFIER, person.getAddress().toString());
        map.put(SALARY_IDENTIFIER, person.getSalary().toString());
        map.put(CLAIM_BUDGET, person.getClaimBudget().toString());
        map.put(DEPARTMENT, person.getDepartment().toString());
        map.put(BIRTHDAY, person.getDob().toString());
        return map;
    }

    /**
     * Returns String containing relevant person's information.
     *
     * @param personInfo HashMap containing Person's primary attributes.
     * @param index Integer which represents index from list.
     * @param identifier String which represents what attribute to view from.
     * @return String representation of relevant information.
     */
    public String generateNiceStr(HashMap<String, String> personInfo, int index, String identifier) {
        String result = personInfo.get(identifier);
        String name = personInfo.get(NAME_IDENTIFIER);
        return String.format("%s. %s's %s is %s.\n", index, name, identifier, result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ViewCommand that = (ViewCommand) o;
        return references.equals(that.references);
    }

    @Override
    public int hashCode() {
        return Objects.hash(references);
    }

}
