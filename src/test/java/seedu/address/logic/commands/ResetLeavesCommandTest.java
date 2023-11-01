package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ResetLeavesCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Person targetPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getOneBased());
    private Person modifiedPerson;

    @BeforeEach
    public void setUp() {
        modifiedPerson = new Person(targetPerson.getName(), targetPerson.getPhone(),
                targetPerson.getEmail(), targetPerson.getAddress(), targetPerson.getSalary(),
                    targetPerson.getClaimBudget(), targetPerson.getDepartment(), targetPerson.getDob());
    }

    @Test
    public void execute_emptyLeavesAddressBook_success() {
        model.setPerson(targetPerson, modifiedPerson);
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandSuccess(new ResetLeavesCommand(), model, ResetLeavesCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
