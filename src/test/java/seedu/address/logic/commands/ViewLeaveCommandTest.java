package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.HasLeaveAnyMonthPredicate;
import seedu.address.model.person.HasLeaveThisMonthPredicate;
import seedu.address.model.person.Person;



class ViewLeaveCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equalsTest() {
        Predicate<Person> predicate1 = new HasLeaveThisMonthPredicate("2");
        Predicate<Person> predicate2 = new HasLeaveThisMonthPredicate("2");
        ViewLeaveCommand command1 = new ViewLeaveCommand(predicate1);
        ViewLeaveCommand command2 = new ViewLeaveCommand(predicate2);
        ViewLeaveCommand command3 = new ViewLeaveCommand(new HasLeaveThisMonthPredicate("12"));
        assertEquals(command1, command1);
        assertEquals(command1, command2);
        assertFalse(command1.equals(command3));
        assertFalse(command1.equals(null));
    }

    @Test
    void execute_successAnyMonth() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Predicate<Person> testPredicate = new HasLeaveAnyMonthPredicate();
        expectedModel.updateFilteredPersonList(testPredicate);
        assertCommandSuccess(new ViewLeaveCommand(testPredicate), model,
                String.format(Messages.MESSAGE_VIEW_LEAVE_SUCCESS,
                        expectedModel.getFilteredPersonList().size()), expectedModel);
    }

    @Test
    void execute_successParticularMonth() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Predicate<Person> testPredicate = new HasLeaveThisMonthPredicate("1");
        expectedModel.updateFilteredPersonList(testPredicate);
        assertCommandSuccess(new ViewLeaveCommand(testPredicate), model,
                String.format(Messages.MESSAGE_VIEW_LEAVE_SUCCESS,
                        expectedModel.getFilteredPersonList().size()), expectedModel);
    }

    @Test
    void execute_successNoLeaveInMonths() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Predicate<Person> testPredicate = new HasLeaveAnyMonthPredicate().negate();
        expectedModel.updateFilteredPersonList(testPredicate);
        assertCommandSuccess(new ViewLeaveCommand(testPredicate), model,
                Messages.MESSAGE_VIEW_LEAVE_NO_EMPLOYEES, expectedModel);
    }
}
