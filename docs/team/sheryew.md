---
layout: page
title: Sher Yew's Project Portfolio Page
---

### Project: HR Insight

HR Insight is a desktop app for HR people, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

- **New Feature**: Added the ability to manage claims of employees.

  - What it does: Allows users to keep track of remaining claim budget for each employee. Dynamic allocation/subtraction of claims for each employee, allowing latest claim budget to be displayed.
  - Justification: This feature reduces the effort required for administrative claim processing, since excessive claims will be rejected automatically by the system and mathematical errors are avoided completely.
  - Highlights: This feature is challenging since parsing checks are required to determine if user wants to allocate/subtract those funds and thereafter, having to produce different success commands.
  - PR [#63](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/63)

- **New Feature**: Added the ability to view specific attribute for employee(s).

  - What it does: Allows users to view specific attribute for employee(s). Provides faster access to attributes for employee(s) instead of manual scraping the entire list for just one attribute. (PR)
  - Justification: This feature is beneficial if user just wants to view one attribute for a large number of employees. Reduces time required for manual scraping of just that attribute for the whole list.
  - Highlights: This feature is challenging as I incorporated multiple index parsing and the effort to ensure every index is captured and checked for validity is time-consuming.
  - PR [#70](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/70)

- **New Feature**: Added the ability to export Employees' attributes into a CSV file.

  - What it does: Allows users to export all employees' attributes into a CSV file. Additionally, users are able to export a subset of employees by filtering on criteria like department.
  - Justification: This feature is beneficial as having a CSV file comprising of employees' attributes allow users to incorporate this data into third-party vendors. Allows our application to be used in harmony with others.
  - Highlights: Learnt Java and its specific utility classes like PrintWriter and streams to make this feature functional.
  - PR [#88](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/88)

- **New Feature**: Added the ability to reset all employees' leaves.

  - What it does: Allows users to reset existing leaves for all employees.
  - Justification: This feature is beneficial whenever a new calendar year strikes, since employees leaves are resetted to zero. We do not want previous year leaves to affect existing year leaves.
  - PR [#117](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/117)

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=sheryew&breakdown=true)

- **Project management**:

  - Managed releases `v1.2` - `v1.3rc` (3 releases) on GitHub
  - Conducted weekly meetings and jotted down meeting minutes on the project page.

- **Documentation**:

  - User Guide:
    - Added documentation for the features `claim`, `view_attribute`, `export` and `reset_leaves`.
    - Added UI images (Before & After) for each feature. (PR [#119](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/119))
  - Developer Guide:
    - Added implementation details for `claim`, `view_attribute`, `export` and `reset_leaves`.
    - Added UML diagrams for `export`.

- **Community**:

  - PRs reviewed. (PR [#130](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/130)), (PR [#85](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/85)), (PR [#30](https://github.com/AY2324S1-CS2103-F13-2/tp/pull/30))
