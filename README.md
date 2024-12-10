
# Online School Management System (OSMS)

 Introduction
The Online School Management System (OSMS) is a Java-based application designed to streamline school operations. It offers robust features for administrators, teachers, and students, making it easier to manage day-to-day school activities like scheduling classes, exams, and tracking student performance.

---

 Features
- User Management:
  - Admin, Teacher, and Student roles with specific permissions.
- Class Management:
  - CRUD operations for creating, editing, and managing classes and sections.
- Exam Scheduling:
  - Assign subjects, dates, and classes to exams.
- Student Management:
  - Manage student profiles, parent details, and class assignments.
- Teacher Management:
  - Manage teacher profiles, qualifications, and subjects.

---

 Technical Details
# Technology Stack
- Programming Language: Java
- IDE: NetBeans
- Database: MySQL
- UI: Swing Framework
- Connectivity: JDBC

# Database Schema
- Tables:
  - `class`: Stores class details (name, section, student strength).
  - `exam`: Details about exams (name, term, date, subject).
  - `student`: Profiles of students, including contact and class details.
  - `teacher`: Teacher profiles with qualifications and subjects.

---

                              |
                              v
                 +----------------------------+
                 |        Service Layer       |
                 |    (Business Logic)        |
                 +------------+---------------+
                              |
                              v
                 +----------------------------+
                 |     Repository Layer       |
                 | (Database Interactions)    |
                 +------------+---------------+
                              |
                              v
                 +----------------------------+
                 |         Data Layer         |
                 |     (Entities/Models)  |
                 +----------------------------+



 Installation and Setup
1. Clone the Repository:
   bash
   git clone https://github.com/Lindsay199904/Online-Course-Management-System-.git
   cd Online-Course-Management-System
   

2. Set Up MySQL Database:
   - Import the provided `school_management_system.sql` file.
   - Ensure MySQL is running and update the database credentials in the Java code:
     java
     con = DriverManager.getConnection("jdbc:mysql://localhost/school_management_system", "username", "password");
     

3. Run the Application:
   - Open the project in NetBeans.
   - Build and run the project.

---

 Deployment (Optional)
To host the OSMS application:
1. Package the application as a JAR file using NetBeans.
2. Deploy it on a platform like:
   - AWS Elastic Beanstalk: For scalable deployments.
   - Azure App Services: Suitable for hosting Java applications.
   - Heroku: For free-tier deployments with database integration.

---

 Future Improvements
- Integration with cloud-hosted databases.
- Mobile-friendly UI for student and parent access.
- Adding real-time notifications for exam schedules and updates.

---
Application overview 

![image](https://github.com/user-attachments/assets/70530c97-a9da-4616-8dcd-ccc68e35e5ff)

![image](https://github.com/user-attachments/assets/8179bed9-ed82-46a8-83ce-d5c492b43c2c)

![image](https://github.com/user-attachments/assets/38d6f2cd-ce99-4b0e-b14d-d4c90cc2e95a)

![image](https://github.com/user-attachments/assets/ac15f73e-243f-42b2-b36a-bd94e7f94e9d)

![image](https://github.com/user-attachments/assets/fe755149-cbc2-4343-939c-13113a4e2ca0)

![image](https://github.com/user-attachments/assets/298ceec8-1aac-4dd8-9d8a-24bf5a817b4b)

![image](https://github.com/user-attachments/assets/8544aa8c-c745-44e8-b7c1-703415ff0544)

![image](https://github.com/user-attachments/assets/314b9433-919c-42c4-8eaa-bccc6ec7c2fb)


Coding:
├── Controller
│   ├── AboutController.java       // Handles display and management of application metadata
│   ├── ClassController.java       // Manages CRUD operations for classes
│   ├── ExamController.java        // Handles scheduling and management of exams
│   ├── LoginController.java       // Manages user login and session handling
│   ├── MarkController.java        // Manages CRUD operations for student marks
│   ├── StudentController.java     // Handles CRUD operations for student records
│   ├── SubjectController.java     // Manages CRUD operations for subjects
│   └── TeacherController.java     // Handles teacher-related CRUD operations
│
├── Model
│   ├── About.java                 // Represents application metadata
│   ├── Classes.java               // Represents a school class with attributes and relationships
│   ├── Exam.java                  // Represents an exam, including date, subject, and class
│   ├── Login.java                 // Represents user authentication details
│   ├── Mark.java                  // Represents student marks and related attributes
│   ├── Student.java               // Represents a student and their details
│   ├── Subject.java               // Represents a subject taught in the school
│   └── Teacher.java               // Represents a teacher and their related details
│
├── Repository
│   ├── AboutRepository.java       // Data access for About information
│   ├── ClassRepository.java       // Data access for Class records
│   ├── ExamRepository.java        // Data access for Exam information
│   ├── LoginRepository.java       // Data access for user login records
│   ├── MarkRepository.java        // Data access for Student marks
│   ├── StudentRepository.java     // Data access for Student details
│   ├── SubjectRepository.java     // Data access for Subject information
│   └── TeacherRepository.java     // Data access for Teacher details
│
├── Service
│   ├── AboutService.java          // Business logic for application metadata
│   ├── ClassService.java          // Business logic for managing classes
│   ├── ExamService.java           // Business logic for exams
│   ├── LoginService.java          // Business logic for login and authentication
│   ├── MarkService.java           // Business logic for managing marks
│   ├── StudentService.java        // Business logic for student management
│   ├── SubjectService.java        // Business logic for subject management
│   └── TeacherService.java        // Business logic for teacher management
│
└── Security
    ├── JwtUtil.java               // Utility class for JWT generation and validation
    ├── JwtFilter.java             // Filter for processing JWT tokens in requests
    └── WebSecurityConfig.java     // Security configuration for the application
    
Demo : https://drive.google.com/file/d/1-gmsGoxdPspWYV4BzcOy4y4iTt8u-bkM/view?usp=drivesdk 


 Contributors
- Lindsay M. Blood

Feel free to submit issues or suggestions to enhance the project!
