package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.BirthdayCommand.MESSAGE_FAILURE;
import static seedu.address.logic.commands.BirthdayCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MatchingBirthdayPredicate;
import seedu.address.testutil.TypicalMonths;

public class BirthdayCommandTest {
    public static final MatchingBirthdayPredicate FILTER_TEST_PREDICATE_SUCCESS =
            new MatchingBirthdayPredicate(TypicalMonths.VALID_MONTH_SUCCESS);
    public static final MatchingBirthdayPredicate FILTER_TEST_PREDICATE_FAILURE =
            new MatchingBirthdayPredicate(TypicalMonths.VALID_MONTH_FAILURE);
    private Model model;
    private Model expectedFilteredModel;

    @BeforeEach
    public void setup() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedFilteredModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedFilteredModel.updateFilteredPersonList(FILTER_TEST_PREDICATE_SUCCESS);

    }

    @Test
    public void execute_birthday_success() {
        assertCommandSuccess(new BirthdayCommand(FILTER_TEST_PREDICATE_SUCCESS), model,
                String.format(MESSAGE_SUCCESS, expectedFilteredModel.getFilteredPersonList().size()),
                        expectedFilteredModel);
    }

    @Test
    public void execute_birthdayNoResults() {
        CommandResult result = new BirthdayCommand(FILTER_TEST_PREDICATE_FAILURE).execute(model, "");
        assertEquals(result.toString(), new CommandResult(MESSAGE_FAILURE).toString());
    }
}
