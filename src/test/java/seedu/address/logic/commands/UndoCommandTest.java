package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBookList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class UndoCommandTest {
    private Model model = new ModelManager();
    private UndoCommand command = new UndoCommand();

    @Test
    public void execute_undo_success() {
        model.addPerson(ALICE);
        model.addCommandText("add Alice");
        model.addPerson(BENSON);
        model.addCommandText("add Benson");
        assertCommandSuccess(command, model, String.format(UndoCommand.MESSAGE_SUCCESS, "add Benson"), model);
    }

    @Test
    public void execute_undo_failure() {
        assertCommandFailure(command, model, AddressBookList.UNDO_ERROR_MESSAGE);
    }
}
