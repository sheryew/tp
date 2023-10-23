package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBookList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class RedoCommandTest {
    private Model model = new ModelManager();
    private RedoCommand command = new RedoCommand();

    @Test
    public void execute_redo_success() {
        model.addPerson(ALICE);
        model.addCommandText("add Alice");
        model.addPerson(BENSON);
        model.addCommandText("add Benson");
        model.undo();
        assertCommandSuccess(command, model, String.format(RedoCommand.MESSAGE_SUCCESS, "add Benson"), model);
    }

    @Test
    public void execute_redo_failure() {
        assertCommandFailure(command, model, AddressBookList.REDO_ERROR_MESSAGE);
    }
}
