package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Creates a new CSV file containing employee's data.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String FOLDER_NAME = "Exported_CSVs";
    public final String fileName;

    /**
     * Initialises a ExportCommand object containing the filename user wants.
     *
     * @param fileName String which represents the name of export file.
     */
    public ExportCommand(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns a CommandResult object with different response message.
     * Performs creation of File object first before writing of contents.
     *
     * @param model {@code Model} which the command should operate on.
     * @param commandText Identifier for redo/undo of statements.
     * @return CommandResult containing the different success/failure response.
     * @throws CommandException In the event where file creation/writing has failed.
     */
    @Override
    public CommandResult execute(Model model, String commandText) throws CommandException {
        requireNonNull(model);
        List<Person> existingPeople = model.getFilteredPersonList();
        List<List<String>> dataLines = generateListPeople(existingPeople);
        File csvOutputFile = generateFile(this.fileName);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCsv)
                    .forEach(pw::println);
            return new CommandResult(String.format("%s.csv has been successfully created!\n", this.fileName)
                    + "You can view the file in the Exported_CSVs folder.");
        } catch (FileNotFoundException e) {
            throw new CommandException("Error: Unable to write to file!");
        }
    }

    /**
     * Returns a List of List of Strings where each internal List is a particular person's attributes.
     *
     * @param existingData List of Person Object. Data is found is retrieved from model.
     * @return List of List of Strings.
     */
    public List<List<String>> generateListPeople(List<Person> existingData) {
        List<List<String>> dataLines = new ArrayList<>();
        dataLines.add(List.of("Name", "Phone", "Email", "Address", "Salary", "Claim Budget", "DOB", "Leave"));
        for (Person people: existingData) {
            List<String> peopleDetails = new ArrayList<>();
            peopleDetails.add(people.getName().toString());
            peopleDetails.add(people.getPhone().value);
            peopleDetails.add(people.getEmail().value);
            peopleDetails.add(people.getAddress().value);
            peopleDetails.add(people.getSalary().amount);
            peopleDetails.add(people.getClaimBudget().amount);
            peopleDetails.add(people.getDob().dob);
            peopleDetails.add(people.getLeave().toString());
            dataLines.add(peopleDetails);
        }
        return dataLines;
    }

    /**
     * Returns a File Object with identifier of fileName and stored in FOLDER_NAME.
     *
     * @param fileName String containing the name user wants to name the file.
     * @return File Object where it is stored in a specific directory coined FOLDER_NAME.
     * @throws CommandException if directory cannot be created.
     */
    public File generateFile(String fileName) throws CommandException {
        File folder = new File(FOLDER_NAME);
        if (!folder.exists()) {
            if (!folder.mkdir()) {
                throw new CommandException("Something went wrong.\n"
                        + "Directory cannot be created.");
            }
        }
        return new File(folder, String.format("%s.csv", this.fileName));
    }

    /**
     * String Object where each attribute of a Person is joined by a delimiter.
     *
     * @param data List of Strings where each string is an attribute of a person.
     * @return String containing the various attributes joined together with ",".
     */
    public String convertToCsv(List<String> data) {
        return data.stream()
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    /**
     * Performs formatting of csv for fields containing special characters.
     * Especially useful when columns like address has multiple commas.
     *
     * @param data String containing person's attributes.
     * @return String of nicely formatted attributes.
     */
    public String escapeSpecialCharacters(String data) {
        String escapedData = data;
        if (data.contains("\"")) {
            escapedData = data.replace("\"", "\"\"");
        }
        if (data.contains(",") || data.contains("\n")) {
            escapedData = "\"" + escapedData + "\"";
        }
        return escapedData;
    }
}
