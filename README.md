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

**New User**
![image](https://github.com/user-attachments/assets/d7058f0c-0918-4f78-a32c-71c55089e33f)

**Existing User**
![image](https://github.com/user-attachments/assets/70c48cf4-6b81-4b51-bf6c-138922f3e224)

**Add Task**
![image](https://github.com/user-attachments/assets/66640e45-620a-4bbb-abc4-3d393372d701)
![image](https://github.com/user-attachments/assets/19dbbad6-617f-4d12-b03f-4e1d7689be99)

**View All Tasks**
![image](https://github.com/user-attachments/assets/e0fcbca1-b4b3-4b1c-b6ce-fff2e566341c)

**View Pending Tasks**
![image](https://github.com/user-attachments/assets/54d84706-cafa-4362-8ce2-f923d4e6002b)

**Update Task**
![image](https://github.com/user-attachments/assets/df4b214c-a203-42bc-a83d-81659400473c)

**Checking Off a Task**
![image](https://github.com/user-attachments/assets/e70f84df-42fc-476d-a56b-49ff8eed8d7e)

**Logging Off**
![image](https://github.com/user-attachments/assets/1247bb03-19e5-4bcf-b961-e0e63a169114)


## Dependencies

- Java Development Kit (JDK) 8 or later

## Notes

- Ensure that `Personal.txt` and `Professional.txt` are in the same directory as your compiled Java files.
- The application will create these files if they do not exist.
