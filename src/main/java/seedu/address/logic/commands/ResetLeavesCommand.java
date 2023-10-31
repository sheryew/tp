package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Resets all employees' leaves to - (AKA 0 leaves).
 * This command is suitable for resetting of leaves per year.
 */
public class ResetLeavesCommand extends Command {

    public static final String COMMAND_WORD = "reset_leaves";
    public static final String MESSAGE_SUCCESS = "All employees' leaves have been reset.";

    /**
     * @param model {@code Model} which the command should operate on.
     * @param commandText Not important in this case.
     * @return CommandResult object with modified Model with each employee having 0 leaves.
     */
    @Override
    public CommandResult execute(Model model, String commandText) {
        requireNonNull(model);
        List<Person> existingParticipants = model.getAddressBook().getPersonList();
        for (int i = 0; i < existingParticipants.size(); i++) {
            Person individual = existingParticipants.get(i);
            model.setPerson(individual, generateNewPerson(individual));
        }
        return new CommandResult(MESSAGE_SUCCESS);
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
