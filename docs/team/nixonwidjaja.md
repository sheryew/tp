---
layout: page
title: Nixon's Project Portfolio Page
---

### Project: HR Insight

HR Insight is a desktop app for HR people, optimized for use via a Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- **New Feature**: Undo and redo command
  * What it does: Allows users to undo previous commands and reverse the undo commands with redo command. (PR [#85](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/85))
  * Justification: This feature improves the app significantly because users can conveniently undo any mistakes in command, so any errors can be fixed quickly.
  * Highlights: The design to implement undo and redo commands is challenging and fun to think about.
 
- **New Feature**: Leave command and employee's leave attribute
  * What it does: Allows employees to have a leave attribute that records which months they are on leave, and for users to edit those leaves using the leave command. (PR [#62](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/62))
  * Justification: HR people should be able to view and edit the leave records of all employees in the company.
 
- **New Feature**: Sort command
  * What it does: Allows users to sort the employee list based on given parameter, e.g., sort based on name, sort salary in descending order, etc. (PR [#108](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/108))
  * Justification: This features mimics real employee lists/tables that enable HR people to sort based on certain criteria, so they can get more insights from the employee data.
  * Highlights: Learned a lot from implementing custom comparator in Java to enable list sorting.

- **New Feature**: Navigate through previous commands using up/down keys
  * What it does: Allows users to navigate through their previous inputs conveniently using up/down keys on the keyboard. (PR [#126](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/126))
  * Justification: This features improves user experience significantly because it mimics real computer CLI/terminal behavior.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=nixonwidjaja&breakdown=true)

- **Enhancements to existing features**:
  * Adapted `add`, `edit`, and `list` commands to work based on HR Insight needs. (PR [#26](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/26))

- **Documentation**:
  * User Guide and Developer Guide: Added documentations for `undo`, `redo`, `leave`, `sort` commands.

- **Community**:
  * Reviewed PRs and gave non-trivial suggestions to improve code quality. ([#61](https://github.com/AY2324S1-CS2103-F13-2/tp/issues/61), [#74](https://github.com/AY2324S1-CS2103-F13-2/tp/issues/74), [#117](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/117))
  * Reported and fixed critical bugs. (PR [#75](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/75), [#110](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/110), [#136](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/136))
  * Set up the organization, repo, and Codecov for the team.
