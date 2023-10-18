package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Leave;
import seedu.address.model.person.Person;

public class LeaveCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private LeaveCommand leaveFirstCommand = new LeaveCommand(INDEX_FIRST_PERSON, "0000+0000000");
    private LeaveCommand leaveSecondCommand = new LeaveCommand(INDEX_SECOND_PERSON, "0+0000000000");

    @Test
    public void equals() {
        assertFalse(leaveFirstCommand.equals(null));
        assertEquals(leaveFirstCommand, leaveFirstCommand);
        assertFalse(leaveFirstCommand.equals(leaveSecondCommand));
        assertFalse(leaveFirstCommand.toString().equals(""));
    }

    @Test
    public void execute_success() {
        Person person = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Leave leave = person.getLeave().update("0000+0000000");
        String expected = String.format(LeaveCommand.MESSAGE_LEAVE_SUCCESS, person.getName(), leave.toString());
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandSuccess(leaveFirstCommand, model, expected, expectedModel);
    }

    @Test
    public void execute_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        LeaveCommand command = new LeaveCommand(outOfBoundIndex, "0000+0000000");
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        assertCommandFailure(new LeaveCommand(INDEX_FIRST_PERSON, "0000---00000"), model,
                String.format(Leave.ILLEGAL_MONTH, "May, Jul"));

        Person person = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        assertCommandFailure(leaveSecondCommand, model,
                String.format(LeaveCommand.MESSAGE_NOT_EDITED, person.getLeave().toString()));
    }
}
