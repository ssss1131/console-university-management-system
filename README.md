# University Management System

## Overview
The **University Management System** is a comprehensive software application designed to streamline and automate administrative, academic, and research activities within a university. This system provides tools for managing users, disciplines, organizations, research papers, and projects while supporting multiple roles such as administrators, managers, students, teachers, and researchers.

The project emphasizes modularity, localization, and flexibility, offering a customizable and scalable solution for educational institutions.

---

## Features

### General Features
- **User Authentication**: Secure login and logout functionality for all roles.
- **Multi-language Support**: Localization for English, Russian, and Kazakh.
- **Dynamic Role-Based Access**: Permissions tailored to each user role.

### Administrative Features
- Manage users: Create, update, and delete users.
- View system logs for tracking activity.
- Assign and manage disciplines for schools and programs.

### Managerial Features
- Open and close registration periods.
- Assign research supervisors to students.
- Manage journals: Create and delete journals.
- Finalize disciplines and approve new ones.

### Student Features
- Register for semesters and view discipline information.
- View marks and diploma project details.
- Join organizations and collaborate with peers.

### Researcher Features
- Add and manage research papers and projects.
- Sort papers by citations, date, or title length.
- Format research paper citations (Plain Text, BibTeX).
- View top-cited researchers by school or year.

### Organization Management
- Create and manage organizations.
- Assign roles (e.g., President, Vice President, Member) and ensure unique role assignments.
- Display organization details and member lists.

  ![image](https://github.com/user-attachments/assets/dfa4ec47-22bb-400c-9502-51a2da5bc9d2)


---

## Technical Details

### Architecture
The system is built using a **modular MVC (Model-View-Controller)** design pattern, ensuring separation of concerns and maintainability.

### Key Technologies
- **Java**: Core language for application development.
- **Serialization**: Persistent data storage using object serialization.
- **Custom Frameworks**: Utilizes a custom command pattern for executing user actions.

### Localization
- **Constants-Based Localization**: All user-facing messages are stored in language-specific maps, enabling dynamic message rendering based on the selected language.

### Commands and Menu
- Command-based execution, with actions dynamically registered during runtime.
- Role-based filtering ensures users only see actions they are authorized to perform.

---

## Installation and Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/ssss1131/console-university-management-system.git
   ```
2. Add logs package, for properly working of logger
   ![image](https://github.com/user-attachments/assets/2a813142-0ac2-4e75-b8d5-c9978a5044e5)

3.Mark directory resources as "Resources". You can find it in project structure
![image](https://github.com/user-attachments/assets/1c34b1e4-c366-4815-bc24-4de89f19f362)

4. Run the Main class in menu

5. You can create accounts with admin account that have such credentials
   login: admin@1.kz
   password: admin
