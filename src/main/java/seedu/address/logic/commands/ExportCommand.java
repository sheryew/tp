package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String FOLDER_NAME = "Exported_CSVs";
    public final String fileName;

    public ExportCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model, String commandText) {
        requireNonNull(model);
        List<Person> existingPeople = model.getFilteredPersonList();
        List<List<String>> dataLines = generateListPeople(existingPeople);

        File folder = new File(FOLDER_NAME);
        if (!folder.exists()) {
            if (!folder.mkdir()) {
                return new CommandResult("Error: Unable to create a new directory!");
            }
        }

        File csvOutputFile = new File(folder, String.format("%s.csv", this.fileName));
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
            return new CommandResult("File successfully written!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();  // Log the exception for debugging purposes
            return new CommandResult("Error: Unable to write to file!");
        }
    }

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

    public String convertToCSV(List<String> data) {
        return data.stream()
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

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
