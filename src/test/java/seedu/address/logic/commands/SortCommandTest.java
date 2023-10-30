package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

public class SortCommandTest {
    private Model model = new ModelManager();
    private Model reversed = new ModelManager();

    @Test
    public void execute_success() {
        model.addPerson(ALICE);
        model.addPerson(BENSON);
        reversed.addPerson(BENSON);
        reversed.addPerson(ALICE);
        assertCommandSuccess(new SortCommand("name", true), model, SortCommand.MESSAGE_SUCCESS, reversed);
        assertCommandSuccess(new SortCommand("name"), model, SortCommand.MESSAGE_SUCCESS, model);
    }
}
