package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Department;
import seedu.address.model.person.MatchingDepartmentPredicate;
import seedu.address.model.person.Person;



/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private static final Predicate<Person> FILTER_TEST_PREDICATE =
            new MatchingDepartmentPredicate(new Department(VALID_DEPARTMENT));
    private Model model;
    private Model expectedModel;
    private Model expectedFilteredModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedFilteredModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedFilteredModel.updateFilteredPersonList(FILTER_TEST_PREDICATE);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, String.format(
                Messages.MESSAGE_LIST_SUCCESS, expectedModel.getFilteredPersonList().size()), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        assertCommandSuccess(new ListCommand(FILTER_TEST_PREDICATE), model, String.format(
                Messages.MESSAGE_FILTER_SUCCESS, expectedFilteredModel.getFilteredPersonList().size()),
                expectedFilteredModel);
    }
}
