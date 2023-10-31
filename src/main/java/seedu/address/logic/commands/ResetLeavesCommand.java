package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Resets all employees' leaves to - (AKA No recorded leaves).
 * This command is suitable for resetting of leaves per year.
 */
public class ResetLeavesCommand extends Command {

    public static final String COMMAND_WORD = "reset_leaves";
    public static final String MESSAGE_SUCCESS = "All employees' leaves have been reset.";

    /**
     * @param model {@code Model} which the command should operate on.
     * @param commandText Identifier for undo statements.
     * @return CommandResult with modified Model with each employee having no recorded leaves.
     */
    @Override
    public CommandResult execute(Model model, String commandText) {
        requireNonNull(model);
        List<Person> existingParticipants = model.getAddressBook().getPersonList();
        List<Person> newParticipants = new ArrayList<>();
        for (int i = 0; i < existingParticipants.size(); i++) {
            Person individual = existingParticipants.get(i);
            newParticipants.add(generateNewPerson(individual));
        }
        model.setAddressBook(generateNewAb(newParticipants));
        model.addCommandText(commandText);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Generates new Address Book containing modified Persons object.
     * @param newIndividuals List of Person objects, each with no recorded leaves.
     * @return Address Book Object.
     */
    private AddressBook generateNewAb(List<Person> newIndividuals) {
        AddressBook ab = new AddressBook();
        ab.setPersons(newIndividuals);
        return ab;
    }

    /**
     * Private function that helps create a new Person with same attributes but 0 leaves.
     * @param individual Employee (Person Object).
     * @return Person Object containing same attributes except total of zero leaves.
     */
    private Person generateNewPerson(Person individual) {
        return new Person(individual.getName(), individual.getPhone(), individual.getEmail(),
                individual.getAddress(), individual.getSalary(), individual.getClaimBudget(),
                individual.getDepartment(), individual.getDob());
    }
}
