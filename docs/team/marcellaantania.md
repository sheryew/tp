---
layout: page
title: Marcella Antania Tan's Project Portfolio Page
---

### Project: HR Insight

HR Insight is a desktop app for HR people, optimized for use via a Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- **New Feature**: Filter by Departments
  * What it does: allows the user to view the employees under a certain specified department. (PR [#51](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/51), [#68](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/68))
  * Justification: This feature is a necessary feature for HR admins as there is very likely to be a time when they need to view the employees under a certain department.
  * Highlights: This feature uses an existing command `list`. When an optional department argument is given, the command will show a filtered list.

- **New Feature**: Filter by Leave (and Department) using the `view_leave` command.
  * What it does: allows the user to view the employees with registered leaves in any of the optionally specified months and departments. (PR [#73](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/73))
  * Justification: This feature allows the user to easily view and manipulate the leaves of the employees. When an employee requests for a new leave, the HR admin can view the employees who have leave under the same month in the same department to ensure that there are not too many leave overlaps between the employees in the same department.
  * Highlights: This feature can be used with or without arguments. Without arguments, the feature will list all the employees who have planned leaves this year. With the month argument present, this feature will show the employees who have leaves in any of the specified months. With the department argument present, this feature will show all the employees from the specified department who have leaves. With both month and department arguments present, this feature will show all employees from the specified department who have leaves in any of the months.
 
- **New Feature**: Change GUI Theme
  * What it does: allows the user to change the theme of the GUI to their preference. (PR [#120](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/120))
  * Justification: This feature is a nice improvement for the product in terms of GUI and catering to user preferences.
  * Highlights: This feature allows user to change their GUI theme by using the `theme` command. Valid themes are dark, light, red, green and blue. The default GUI theme when the application is launched is a dark theme.

- **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=marcellaantania&breakdown=true)

- **Project management**:
  * Attend weekly meetings and provide timely updates on code progress.

- **Enhancements to existing features**: 
  * Increased test coverage for ListCommand-related classes and methods. (PR [#77](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/77))

- **Documentation**:
  * User Guide:
    * Added documentation for the features `add_leave` and `theme` (PR [#30](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/30), [#122](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/122))
  * Developer Guide:
    * Added implementation details of the `add_leave` and `theme` features with use cases (PR [#37](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/37), [#102](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/102), [#122](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/122)).
    * Created and added sequence diagrams for `list d/department`, `add_leave` and `theme` commands. (PR [#125](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/125), [#132](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/132))

- **Community**:
  * PRs reviewed (with non-trivial comments): [#33](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/33) ,[#70](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/70)
  * Reported critical bugs, fixed some and assigned the rest to the relevant teammates (Issue [#72](https://github.com/AY2324S1-CS2103-F13-2/tp/issues/72), [#86](https://github.com/AY2324S1-CS2103-F13-2/tp/issues/86), [#121](https://github.com/AY2324S1-CS2103-F13-2/tp/issues/121))
