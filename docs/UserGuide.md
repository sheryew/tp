---
layout: page
title: User Guide
---

HR Insight is a **desktop app for HR people, optimized for use via a Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

- Table of Contents
{:toc}

---

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `HRInsight.jar` from [here](https://github.com/AY2324S1-CS2103-F13-2/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar HRInsight.jar` command to run the application.<br>

   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   - `list` : Lists all the details of an organization's employees.

   - `find` : Finds employees whose names contain any of the given keywords.

   - `add n/John Doe p/87654321 e/john.doe@gmail.com a/Tokyo s/5000 b/2000 d/Sales dob/1992-07-21` : <br>
     Adds an employee named `John Doe` to the employee list.

   - `delete 3` : Deletes the 3rd employee shown in the current list.

   - `claim 2 $/-60` : Deducts $60 from the claims of the 2nd employee in the list.

   - `clear` : Deletes all employees from the database.

   - `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `list [d/DEPARTMENT]` can be used as `list` or as `list d/Engineering`.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

Format: `help`

### Adding an employee: `add`

Adds an employee to the employee list.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS s/SALARY b/CLAIM_BUDGET d/DEPARTMENT dob/BIRTH_DATE (YYYY-MM-DD)​`

Examples:

- `add n/Adam p/12345678 e/adam@gmail.com a/Singapore s/10000 b/5000 d/Engineering dob/2000-01-01`
- `add n/John Doe p/87654321 e/john.doe@gmail.com a/Tokyo s/5000 b/2000 d/Sales dob/1992-07-21`

### Listing all employees : `list`

Lists all the details of an organization’s employees, or list all employees of a specified department.

Format: `list [d/DEPARTMENT]`

Examples:

- `list` Lists all employees in the employee list.
- `list d/Engineering` Lists all employees in the Engineering department.

### Editing an employee's information : `edit`

Edits an existing employee in the employee list by the specified index.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/SALARY] [b/CLAIM_BUDGET] [d/DEPARTMENT] [dob/BIRTH_DATE (YYYY-MM-DD)]`

- Edits the employee at the specified `INDEX`. The index refers to the index number shown in the displayed employee list.
  The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.

Examples:

- `edit 1 p/23423423 e/barry@example.com` Edits the phone number and email address of the 1st person to be `23423423` and `barry@example.com` respectively.

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The search is case-insensitive. e.g `hans` will match `Hans`
- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Only full words will be matched e.g. `Han` will not match `Hans`
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find John` returns `john` and `John Doe`
- `find alex david` returns `Alex Yeoh`, `David Li`<br>

### Deleting an employee : `delete`

Delete an employee in the employee list by the specified index.

Format: `delete INDEX`

- Deletes the employee at the specified `INDEX`.
- The index refers to the index number shown in the displayed employee list.
- The index **must be a positive integer** 1, 2, 3, …​

Examples:

- `list` followed by `delete 2` deletes the 2nd person in the employee list.
- `find Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Managing Employee's Claims: `claim`

Performs adjustments to employee's claims.

Format: `claim INDEX $/CLAIM_AMOUNT`

- The `INDEX` refers to the index number shown in the displayed employee list.
- The index **must be a positive integer** 1,2,3, ...
- The `CLAIM_AMOUNT` should consist of either + or - which symbolizes adding/deducting respectively followed by the amount.

Examples:

- `list` followed by `claim 1 $/-500` deducts $50 from the claims of the 1st employee in the list.
- `list` followed by `claim 2 $/+60` adds $60 to the claims of the 2nd employee in the list.

### Adding Employee’s Leave: `add_leave`

Adds a new leave for an employee.

Format:  `add_leave INDEX m/MONTH`

- The `INDEX` refers to the index number shown in the displayed employee list.
- The index **must be a positive integer** 1,2,3, ...
- The `MONTH` refers to the month of the leave the employee is taking in integer format (between 1-12).

Examples: 
- `list` followed by `add_leave 1 m/3` adds a leave on January for the 1st employee in the list.
- `list` followed by `add_leave 3 m/12` adds a leave on December for the 3rd employee in the list.

### Viewing all employees' leaves : `view_leave`

Views all employees who are on leave, with optional filters of month and department.

Format:  `view_leave [INDEX] [m/Month] [d/DEPARTMENT]`

- Gives a list of **all employees** who have leaves planned for the year.
- The index of the employee, month and department are optional arguments.
- If no one has planned leave dates for the month, **No one is taking leave** output is shown.

Examples: 
- `view_leave` displays all employees who have planned leave dates in the current year
- `view_leave m/10` displays all employees that are taking leave in October
- `view_leave m/10 d/IT` displays all employees in the IT department that are taking leave in October.


### Viewing all birthdays in a given month : `birthday`

Views all employees’ birthday in the given month

Format:  `birthday [m/MONTH]`

- Gives a list of **all employees** who have upcoming birthdays in the **inquired month**
- The month argument is optional. If **no month** is provided, the upcoming birthdays in the current month are listed.
- If there is no birthday in the month provided or the birthday is past the current date in the same month, return **No upcoming birthdays**

Examples: 
- `birthday` followed by `10` displays all employees who have their birthday in the month of October



### Saving the data

HR Insight data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HR Insight data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, HR Insight will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</box>

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous HR Insight home folder.

---

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

---

## Command summary

| Action             | Format, Examples                                                                                                                                                      |
|--------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**            | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS s/SALARY b/CLAIM_BUDGET d/DEPARTMENT dob/BIRTH_DATE (YYYY-MM-DD)​` <br> e.g., `add n/John Doe p/87654321 e/john.doe@gmail.com a/Tokyo s/5000 b/2000 d/Sales dob/1992-07-21` |
| **Clear**          | `clear`                                                                                                                                                               |
| **Delete**         | `delete INDEX`<br> e.g., `delete 3`                                                                                                                                   |
| **Edit**           | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [s/SALARY] [b/CLAIM_BUDGET] [d/DEPARTMENT] [dob/BIRTH_DATE (YYYY-MM-DD)]` <br> e.g., `edit 1 p/23423423 e/barry@example.com`|
| **Find**           | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                            |
| **List**           | `list [d/DEPARTMENT]`                                                                                                                                                 |
| **Claim**          | `claim INDEX $/CLAIM_AMOUNT` <br> e.g., `claim 1 $/-500`                                                                                                              |
| **Add Leave**      | `add_leave INDEX m/MONTH` <br> e.g., `add_leave 1 m/3`                                                                                                                |
| **Help**           | `help`                                                                                                                                                                |
| **View Leave**     | `view_leave INDEX m/Month d/DEPARTMENT` <br> e.g.,`view_leave m/10 d/IT`                                                                                              |
| **View Birthdays** | `birthday [m/MONTH]` <br> e.g., `birthday 10`                                                                                                                         |
