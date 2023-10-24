package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class ExportCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final String templateStr = "engineering_dept";
    private final ExportCommand exportCommand = new ExportCommand(templateStr);
    private Person targetPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getOneBased());
    private List<String> indivAttributeList;
    private List<String> csvHeaders;

    @BeforeEach
    public void setUp() {
        csvHeaders = new ArrayList<String>();
        csvHeaders.add("Name");
        csvHeaders.add("Phone");
        csvHeaders.add("Email");
        csvHeaders.add("Address");
        csvHeaders.add("Salary");
        csvHeaders.add("Claim Budget");
        csvHeaders.add("DOB");
        csvHeaders.add("Department");
        csvHeaders.add("Leave");
        indivAttributeList = new ArrayList<String>();
        indivAttributeList.add(targetPerson.getName().toString());
        indivAttributeList.add(targetPerson.getPhone().toString());
        indivAttributeList.add(targetPerson.getEmail().toString());
        indivAttributeList.add(targetPerson.getAddress().toString());
        indivAttributeList.add(targetPerson.getSalary().amount);
        indivAttributeList.add(targetPerson.getClaimBudget().amount);
        indivAttributeList.add(targetPerson.getDob().dob);
        indivAttributeList.add(targetPerson.getDepartment().toString());
        indivAttributeList.add(targetPerson.getLeave().toString());
    }

    @Test
    public void execute_validFileName_success() throws CommandException {
        ExportCommand exportCommand = new ExportCommand("testExport");
        CommandResult commandResult = exportCommand.execute(model, "");
        String expectedMessage = "testExport.csv has been successfully created!\n"
                + "You can view the file in the Exported_CSVs folder.";
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertEquals(expectedCommandResult, commandResult);
        File expectedDirectory = new File(System.getProperty("user.dir"), "Exported_CSVs");
        File expectedFile = new File(expectedDirectory, "testExport.csv");
        assertTrue(expectedFile.exists(), "File should have been created");
    }

    @Test
    void generateFile_directoryDoesNotExist_createsDirectoryAndFile() {
        File expectedDirectory = new File(System.getProperty("user.dir"), "Exported_CSVs");
        File expectedFile = new File(expectedDirectory, "engineering_dept.csv");

        try {
            File result = exportCommand.generateFile("engineering_dept");
            assertTrue(expectedDirectory.exists(), "Directory should have been created");
            assertEquals(expectedFile.getAbsolutePath(), result.getAbsolutePath(),
                    "Generated file does not match expected");
        } catch (CommandException e) {
            fail("CommandException should not be thrown");
        }
    }

    @Test
    public void generateList_singlePerson_success() {
        List<Person> singlePerson = List.of(targetPerson);
        List<List<String>> firstIndexAttributes = exportCommand.generateListPeople(singlePerson);
        assertEquals(firstIndexAttributes, List.of(csvHeaders, indivAttributeList));
    }

    @Test
    public void generateCsv_attributeFields_success() {
        List<String> userAttributes = List.of("Nixon", "1234567", "Kent Ridge");
        String delimitedStr = exportCommand.convertToCsv(userAttributes);
        String expectedStr = "Nixon,1234567,Kent Ridge";
        assertEquals(delimitedStr, expectedStr);
    }

    @Test
    public void testEscapeSpecialCharacters_noSpecialCharacters_success() {
        String input = "This is a test";
        String output = exportCommand.escapeSpecialCharacters(input);
        assertEquals("This is a test", output);
    }
    @Test
    public void testEscapeSpecialCharacters_containsDoubleQuotes_success() {
        String input = "This is a \"test\"";
        String output = exportCommand.escapeSpecialCharacters(input);
        assertEquals("This is a \"\"test\"\"", output);
    }

    @Test
    public void testEscapeSpecialCharacters_containsComma_success() {
        String input = "This, is a test";
        String output = exportCommand.escapeSpecialCharacters(input);
        assertEquals("\"This, is a test\"", output);
    }

    @Test
    public void testEscapeSpecialCharacters_containsNewline_success() {
        String input = "This is a test\nNew line";
        String output = exportCommand.escapeSpecialCharacters(input);
        assertEquals("\"This is a test\nNew line\"", output);
    }

    @Test
    public void testEscapeSpecialCharacters_containsSpecialChars_failure() {
        String input = "This is a \"test, with comma\"";
        String output = exportCommand.escapeSpecialCharacters(input);
        assertNotEquals(input, output);
    }

    @Test
    public void equals_sameObject_true() {
        ExportCommand command = new ExportCommand("testFile");
        assertEquals(command, command);
    }

    @Test
    public void equals_null_false() {
        ExportCommand command = new ExportCommand("testFile");
        assertFalse(command.equals(null));
    }

    @Test
    public void equals_differentClass_false() {
        ExportCommand command = new ExportCommand("testFile");
        Object other = new Object();
        assertFalse(command.equals(other));
    }

    @Test
    public void equals_differentFileName_false() {
        ExportCommand command1 = new ExportCommand("testFile1");
        ExportCommand command2 = new ExportCommand("testFile2");
        assertFalse(command1.equals(command2));
    }

    @Test
    public void equals_sameFileName_true() {
        ExportCommand command1 = new ExportCommand("testFile");
        ExportCommand command2 = new ExportCommand("testFile");
        assertTrue(command1.equals(command2));
    }
}
