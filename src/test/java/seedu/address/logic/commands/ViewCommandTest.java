package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.ViewCommandParser.ADDRESS_IDENTIFIER;
import static seedu.address.logic.parser.ViewCommandParser.BIRTHDAY;
import static seedu.address.logic.parser.ViewCommandParser.CLAIM_BUDGET;
import static seedu.address.logic.parser.ViewCommandParser.DEPARTMENT;
import static seedu.address.logic.parser.ViewCommandParser.EMAIL_IDENTIFIER;
import static seedu.address.logic.parser.ViewCommandParser.NAME_IDENTIFIER;
import static seedu.address.logic.parser.ViewCommandParser.PHONE_IDENTIFIER;
import static seedu.address.logic.parser.ViewCommandParser.SALARY_IDENTIFIER;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ViewCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Person targetPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getOneBased());
    private final HashMap<String, List<Index>> templateReference = new HashMap<String, List<Index>>() {{
            put("Salary", List.of(INDEX_FIRST_PERSON));
        }};
    private HashMap<String, String> personInfo;

    private final ViewCommand viewCommand = new ViewCommand(templateReference);

    @BeforeEach
    public void setUp() {
        personInfo = new HashMap<>();
        personInfo.put(NAME_IDENTIFIER, targetPerson.getName().toString());
        personInfo.put(PHONE_IDENTIFIER, targetPerson.getPhone().toString());
        personInfo.put(EMAIL_IDENTIFIER, targetPerson.getEmail().toString());
        personInfo.put(ADDRESS_IDENTIFIER, targetPerson.getAddress().toString());
        personInfo.put(SALARY_IDENTIFIER, targetPerson.getSalary().toString());
        personInfo.put(CLAIM_BUDGET, targetPerson.getClaimBudget().toString());
        personInfo.put(DEPARTMENT, targetPerson.getDepartment().toString());
        personInfo.put(BIRTHDAY, targetPerson.getDob().toString());
    }

    @Test
    public void execute_validViewSalary_success() {
        Person personToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedResponse = String.format("You are viewing Salary:\n"
                + "1. %s's Salary is %s.\n\n", personToView.getName(), personToView.getSalary());
        assertCommandSuccess(viewCommand, model, expectedResponse, model);
    }

    @Test
    public void execute_wrongIndex_failure() throws ParseException {
        HashMap<String, List<Index>> nameWithWrongIndex = new HashMap<String, List<Index>>() {{
                put("Name", List.of(ParserUtil.parseIndex("10000")));
            }};
        ViewCommand wrongViewCommand = new ViewCommand(nameWithWrongIndex);
        String expectedResponse = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(wrongViewCommand, model, expectedResponse);
    }

    @Test
    public void execute_validViewClaimBudgetDepartment_success() {
        HashMap<String, List<Index>> claimBudgetAndDepartment = new HashMap<String, List<Index>>() {{
                put("Claim Budget", List.of(INDEX_FIRST_PERSON));
                    put("Department", List.of(INDEX_FIRST_PERSON));
            }};
        ViewCommand newViewCommand = new ViewCommand(claimBudgetAndDepartment);
        Person personToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        String expectedResponse = String.format("You are viewing Department:\n"
                + "1. %s's Department is %s.\n\n", personToView.getName(), personToView.getDepartment())
                    + String.format("You are viewing Claim Budget:\n"
                            + "1. %s's Claim Budget is %s.\n\n", personToView.getName(), personToView.getClaimBudget());
        assertCommandSuccess(newViewCommand, model, expectedResponse, model);
    }

    @Test
    public void generate_assertionPrefix_failure() {
        assertThrows(AssertionError.class, () -> viewCommand.massAssertionFn("INVALID_KEY"));
    }

    @Test
    public void generate_responseStr_success() {
        String returnStr = viewCommand.generateNiceStr(personInfo, 1, "Salary");
        String expectedStr = "1. Benson Meier's Salary is $100.\n";
        assertEquals(returnStr, expectedStr);
    }

    @Test
    public void generate_responseStr_failure() {
        String returnStr = viewCommand.generateNiceStr(personInfo, 1, "Salary");
        String expectedStr = "1. Benson Meier's Name is Benson Meier.\n";
        assertNotEquals(returnStr, expectedStr);
    }

    @Test
    public void viewCommand_equals() throws ParseException {
        HashMap<String, List<Index>> references1 = new HashMap<>();
        references1.put("Key1", List.of(ParserUtil.parseIndex("1")));
        ViewCommand viewCommand1 = new ViewCommand(references1);
        HashMap<String, List<Index>> referencesSame = new HashMap<>();
        referencesSame.put("Key1", List.of(ParserUtil.parseIndex("1")));
        ViewCommand viewCommandSameValues = new ViewCommand(referencesSame);
        HashMap<String, List<Index>> referencesDifferent = new HashMap<>();
        referencesDifferent.put("Key2", List.of(ParserUtil.parseIndex("2")));
        ViewCommand viewCommandDifferentValues = new ViewCommand(referencesDifferent);
        String notACommand = "This is a string, not a command";
        assertTrue(viewCommand1.equals(viewCommand1));
        assertTrue(viewCommand1.equals(viewCommandSameValues));
        assertFalse(viewCommand1.equals(viewCommandDifferentValues));
        assertFalse(viewCommand1.equals(null));
        assertFalse(viewCommand1.equals(notACommand));
    }

}
