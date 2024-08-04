# Task Manager Application

This is a simple Task Manager application in Java that allows users to manage their personal and professional tasks. Users can log in, register, add tasks, view all tasks, view pending tasks, update tasks, and check off tasks.

## Features

- User login and registration
- Add personal and professional tasks
- View all tasks
- View pending tasks
- Update tasks
- Check off tasks

## Class Structure

- `Task_Manager`: The main class that handles user interaction and task management.
- `Personal`: A class representing personal tasks.
- `Professional`: A class representing professional tasks.

## Files

- `Task_Manager.java`: Contains the main application logic, the `Personal` class and the `Professional` class.
- `Personal.txt`: Stores the personal tasks (created automatically if not present).
- `Professional.txt`: Stores the professional tasks (created automatically if not present).

## How to Run

1. **Compile the Java files:**

   ```sh
   javac Task_Manager.java
   ```

2. **Run the application:**

   ```sh
   java Task_Manager
   ```

## Usage

1. **Login or Register:**

   - When you run the application, you will be prompted to enter your username and password.
   - If you don't have an account, you can choose to register.

2. **Add Tasks:**

   - After logging in, you can add personal or professional tasks.

3. **View Tasks:**

   - You can view all your tasks or only pending tasks.

4. **Update Tasks:**

   - You can update your tasks' details.

5. **Check Off Tasks:**

   - You can mark tasks as completed, which will remove them from your task list.

6. **Logout:**
   - You can logout to end your session.

## Example

## Dependencies

- Java Development Kit (JDK) 8 or later

## Notes

- Ensure that `Personal.txt` and `Professional.txt` are in the same directory as your compiled Java files.
- The application will create these files if they do not exist.
