package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.BirthdayCommand.MESSAGE_FAILURE;
import static seedu.address.logic.commands.BirthdayCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MatchingBirthdayPredicate;
import seedu.address.model.person.Month;

public class BirthdayCommandTest {
    private Model model;
    private Model expectedFilteredModel;


    @BeforeEach
    public void setup() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedFilteredModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_birthday_success() {
        List<Month> monthList = new ArrayList<>();
        monthList.add(new Month(2));
        MatchingBirthdayPredicate matchingBirthdayPredicate = new MatchingBirthdayPredicate(monthList);
        expectedFilteredModel.updateFilteredPersonList(matchingBirthdayPredicate);
        assertCommandSuccess(new BirthdayCommand(matchingBirthdayPredicate), model,
                String.format(MESSAGE_SUCCESS, expectedFilteredModel.getFilteredPersonList().size())
                        + "Feb", expectedFilteredModel);
    }

    @Test
    public void execute_birthdayNoResults() {
        List<Month> monthList = new ArrayList<>();
        monthList.add(new Month(10));
        CommandResult result = new BirthdayCommand(new MatchingBirthdayPredicate(monthList)).execute(model, "");
        assertEquals(result.toString(), new CommandResult(MESSAGE_FAILURE).toString());
    }

    @Test
    public void checkSameEquals_success() {
        List<Month> monthList = new ArrayList<>();
        monthList.add(new Month(5));
        BirthdayCommand command1 = new BirthdayCommand(new MatchingBirthdayPredicate(monthList));
        assertEquals(command1, command1);
    }

    @Test
    public void checkSameMonthEquals_success() {
        List<Month> monthList1 = new ArrayList<>();
        monthList1.add(new Month(5));
        monthList1.add(new Month(6));
        BirthdayCommand command1 = new BirthdayCommand(new MatchingBirthdayPredicate(monthList1));

        List<Month> monthList2 = new ArrayList<>();
        monthList2.add(new Month(5));
        monthList2.add(new Month(6));
        BirthdayCommand command2 = new BirthdayCommand(new MatchingBirthdayPredicate(monthList2));

        assertEquals(command1, command2);
    }

    @Test
    public void checkEquals_failure() {
        List<Month> monthList1 = new ArrayList<>();
        monthList1.add(new Month(5));
        BirthdayCommand command1 = new BirthdayCommand(new MatchingBirthdayPredicate(monthList1));
        assertNotEquals(command1, new ExitCommand());
    }
}
