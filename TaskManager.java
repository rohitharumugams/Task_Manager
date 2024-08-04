import java.util.*;
import java.io.*;
import java.time.*;

class Task_Manager {

    public static void writeToFilePersonal(ArrayList<Personal> per) {
        try (FileWriter myWriter = new FileWriter("Personal.txt")) {
            for (Personal p : per) {
                myWriter.write(p.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to Personal.txt.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Personal> readFromFilePersonal() {
        ArrayList<Personal> per = new ArrayList<>();
        File file = new File("Personal.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating Personal.txt.");
                e.printStackTrace();
            }
        }
        try (Scanner myReader = new Scanner(file)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                per.add(new Personal(data));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading from Personal.txt.");
            e.printStackTrace();
        }
        return per;
    }

    public static void writeToFileProfessional(ArrayList<Professional> pro) {
        try (FileWriter myWriter = new FileWriter("Professional.txt")) {
            for (Professional p : pro) {
                myWriter.write(p.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing to Professional.txt.");
            e.printStackTrace();
        }
    }

    public static ArrayList<Professional> readFromFileProfessional() {
        ArrayList<Professional> pro = new ArrayList<>();
        File file = new File("Professional.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating Professional.txt.");
                e.printStackTrace();
            }
        }
        try (Scanner myReader = new Scanner(file)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                pro.add(new Professional(data));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading from Professional.txt.");
            e.printStackTrace();
        }
        return pro;
    }

    public static void main(String[] args) {
        System.out.println("WELCOME USER !!!\n");
        Scanner in = new Scanner(System.in);

        ArrayList<Personal> per = readFromFilePersonal();
        ArrayList<Professional> pro = readFromFileProfessional();

        String uid = "", pwd = "";
        int logChoice = 0;
        boolean isLoggedIn = false;

        for (int i = 0; i < 3; i++) {
            System.out.print("Enter username: ");
            uid = in.nextLine();
            System.out.print("Enter password: ");
            pwd = in.nextLine();

            if (isValidLogin(uid, pwd, per, pro)) {
                isLoggedIn = true;
                break;
            } else {
                System.out.print("\nLogin not found. Do you want to\n1) Login again\n2) Register\nEnter choice: ");
                logChoice = in.nextInt();
                in.nextLine(); // consume newline
                if (logChoice == 2) {
                    break;
                }
            }
        }

        if (logChoice == 2) {
            System.out.print("Let's get started with your login ID and password\n\nEnter your login ID: ");
            uid = in.nextLine();
            System.out.print("Enter your password: ");
            pwd = in.nextLine();

            System.out.print(
                    "\nYou have successfully created an account. Now add your first task to proceed\n\nEnter type of task to add\n1) Personal\n2) Professional\nEnter your choice: ");
            int typeChoice = in.nextInt();
            in.nextLine(); // consume newline

            if (typeChoice == 1) {
                per.add(createPersonalTask(in, uid, pwd));
                System.out.println("Personal task added successfully\n");
            } else if (typeChoice == 2) {
                pro.add(createProfessionalTask(in, uid, pwd));
                System.out.println("Professional task added successfully\n");
            } else {
                System.out.println("Invalid task type chosen. Try again\n");
            }

            isLoggedIn = true;
        }

        if (!isLoggedIn) {
            System.out.println("Too many login attempts. Terminating application.");
            return;
        }

        while (true) {
            System.out.print(
                    "\n\n=========================================\n              OPERATIONS\n=========================================\n\nEnter which operation you want to perform\n\n1) Add tasks\n2) View all your tasks\n3) View your pending tasks\n4) Update your tasks\n5) Check off tasks\n6) Logout\n\nEnter choice: ");
            int choice = in.nextInt();
            in.nextLine(); // consume newline

            if (choice == 6) {
                System.out
                        .println("\nEducation is the most powerful weapon which you can use to change the World !!!\n");
                break;
            }

            switch (choice) {
                case 1:
                    addTask(in, per, pro, uid, pwd);
                    break;
                case 2:
                    viewTasks(per, pro, uid, pwd);
                    break;
                case 3:
                    viewPendingTasks(per, pro, uid, pwd);
                    break;
                case 4:
                    updateTasks(in, per, pro, uid, pwd);
                    break;
                case 5:
                    checkOffTasks(in, per, pro, uid, pwd);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }

        writeToFilePersonal(per);
        writeToFileProfessional(pro);
    }

    private static boolean isValidLogin(String uid, String pwd, ArrayList<Personal> per, ArrayList<Professional> pro) {
        for (Personal p : per) {
            if (p.getUID().equals(uid) && p.getPwd().equals(pwd)) {
                return true;
            }
        }
        for (Professional p : pro) {
            if (p.getUID().equals(uid) && p.getPwd().equals(pwd)) {
                return true;
            }
        }
        return false;
    }

    private static Personal createPersonalTask(Scanner in, String uid, String pwd) {
        System.out.println(
                "==============================================\n                 PERSONAL TASK\n==============================================");
        System.out.print("Enter task title: ");
        String title = in.nextLine();
        System.out.print("Enter label: ");
        String label = in.nextLine();
        System.out.print("Enter deadline (in YYYY-MM-DD format): ");
        String deadline = in.nextLine();
        System.out.print("Enter task description: ");
        String description = in.nextLine();
        return new Personal(uid, pwd, title, label, deadline, description);
    }

    private static Professional createProfessionalTask(Scanner in, String uid, String pwd) {
        System.out.println(
                "==============================================\n                PROFESSIONAL TASK\n==============================================");
        System.out.print("Enter task title: ");
        String title = in.nextLine();
        System.out.print("Enter label: ");
        String label = in.nextLine();
        System.out.print("Enter deadline (in YYYY-MM-DD format): ");
        String deadline = in.nextLine();
        System.out.print("Enter task description: ");
        String description = in.nextLine();
        System.out.print("Choose task difficulty\n1) Easy\n2) Medium\n3) Hard\nEnter choice: ");
        int difficulty = in.nextInt();
        System.out.print("Choose importance\n1) Not urgent\n2) Quite urgent\n3) Very urgent\nEnter choice: ");
        int importance = in.nextInt();
        in.nextLine(); // consume newline
        return new Professional(uid, pwd, title, label, deadline, description, importance, difficulty);
    }

    private static void addTask(Scanner in, ArrayList<Personal> per, ArrayList<Professional> pro, String uid,
            String pwd) {
        System.out.print("\nEnter which type of tasks you want to add\n1) Personal\n2) Professional\n");
        int typeChoice = in.nextInt();
        in.nextLine(); // consume newline

        if (typeChoice == 1) {
            per.add(createPersonalTask(in, uid, pwd));
            System.out.println("Personal task added successfully\n");
        } else if (typeChoice == 2) {
            pro.add(createProfessionalTask(in, uid, pwd));
            System.out.println("Professional task added successfully\n");
        } else {
            System.out.println("Invalid task type chosen. Try again\n");
        }
    }

    private static void viewTasks(ArrayList<Personal> per, ArrayList<Professional> pro, String uid, String pwd) {
        System.out.println(
                "=============================================\n                Personal tasks\n=============================================");
        for (Personal p : per) {
            if (uid.equals(p.getUID()) && pwd.equals(p.getPwd())) {
                p.fullDisplay();
                System.out.println("\n");
            }
        }

        System.out.println(
                "=============================================\n              Professional tasks\n=============================================");
        for (Professional p : pro) {
            if (uid.equals(p.getUID()) && pwd.equals(p.getPwd())) {
                p.fullDisplay();
                System.out.println("\n");
            }
        }
    }

    private static void viewPendingTasks(ArrayList<Personal> per, ArrayList<Professional> pro, String uid, String pwd) {
        System.out.println(
                "=======================================\n       Personal tasks (pending)\n=======================================");
        for (Personal p : per) {
            if (uid.equals(p.getUID()) && pwd.equals(p.getPwd())) {
                p.pendingTasks();
            }
        }

        System.out.println(
                "=======================================\n      Professional tasks (pending)\n=======================================");
        for (Professional p : pro) {
            if (uid.equals(p.getUID()) && pwd.equals(p.getPwd())) {
                p.pendingTasks();
            }
        }
    }

    private static void updateTasks(Scanner in, ArrayList<Personal> per, ArrayList<Professional> pro, String uid,
            String pwd) {
        System.out.print("\nEnter which type of task you want to update\n1) Personal\n2) Professional\n");
        int typeChoice = in.nextInt();
        in.nextLine(); // consume newline

        if (typeChoice == 1) {
            updatePersonalTasks(in, per, uid, pwd);
        } else if (typeChoice == 2) {
            updateProfessionalTasks(in, pro, uid, pwd);
        } else {
            System.out.println("Invalid task type chosen. Try again\n");
        }
    }

    private static void updatePersonalTasks(Scanner in, ArrayList<Personal> per, String uid, String pwd) {
        System.out.println(
                "==============================================\n                 Personal tasks\n==============================================");
        for (Personal p : per) {
            if (uid.equals(p.getUID()) && pwd.equals(p.getPwd())) {
                p.fullDisplay();
                System.out.println("\n");
            }
        }

        for (Personal p : per) {
            if (uid.equals(p.getUID()) && pwd.equals(p.getPwd())) {
                p.updateTasks(per, uid, pwd);
                break;
            }
        }
    }

    private static void updateProfessionalTasks(Scanner in, ArrayList<Professional> pro, String uid, String pwd) {
        System.out.println(
                "==============================================\n               Professional tasks\n==============================================");
        for (Professional p : pro) {
            if (uid.equals(p.getUID()) && pwd.equals(p.getPwd())) {
                p.fullDisplay();
                System.out.println("\n");
            }
        }

        for (Professional p : pro) {
            if (uid.equals(p.getUID()) && pwd.equals(p.getPwd())) {
                p.updateTasks(pro, uid, pwd);
                break;
            }
        }
    }

    private static void checkOffTasks(Scanner in, ArrayList<Personal> per, ArrayList<Professional> pro, String uid,
            String pwd) {
        System.out.print("Enter which type of tasks you want to check off\n1) Personal\n2) Professional\n");
        int typeChoice = in.nextInt();
        in.nextLine(); // consume newline

        if (typeChoice == 1) {
            checkOffPersonalTasks(in, per, uid, pwd);
        } else if (typeChoice == 2) {
            checkOffProfessionalTasks(in, pro, uid, pwd);
        } else {
            System.out.println("Invalid task type chosen. Try again\n");
        }
    }

    private static void checkOffPersonalTasks(Scanner in, ArrayList<Personal> per, String uid, String pwd) {
        System.out.println(
                "==============================================\n                 Personal tasks\n==============================================");
        for (Personal p : per) {
            if (uid.equals(p.getUID()) && pwd.equals(p.getPwd())) {
                p.fullDisplay();
                System.out.println("\n");
            }
        }

        System.out.print("Enter the task title which you want to check off: ");
        String title = in.nextLine();
        System.out.print("Enter the task label which you want to check off: ");
        String label = in.nextLine();

        per.removeIf(p -> p.getUID().equals(uid) && p.getPwd().equals(pwd) && p.getTitle().equals(title)
                && p.getLabel().equals(label));
        System.out.println("\nTask checked off successfully");
    }

    private static void checkOffProfessionalTasks(Scanner in, ArrayList<Professional> pro, String uid, String pwd) {
        System.out.println(
                "==============================================\n              Professional tasks\n==============================================");
        for (Professional p : pro) {
            if (uid.equals(p.getUID()) && pwd.equals(p.getPwd())) {
                p.fullDisplay();
                System.out.println("\n");
            }
        }

        System.out.print("Enter the task title which you want to check off: ");
        String title = in.nextLine();
        System.out.print("Enter the task label which you want to check off: ");
        String label = in.nextLine();

        pro.removeIf(p -> p.getUID().equals(uid) && p.getPwd().equals(pwd) && p.getTitle().equals(title)
                && p.getLabel().equals(label));
        System.out.println("\nTask checked off successfully");
    }
}

class Personal {
    private String uid, pwd, title, label, deadline, description;

    public Personal(String data) {
        // Initialize from a data string
        String[] fields = data.split(",");
        this.uid = fields[0];
        this.pwd = fields[1];
        this.title = fields[2];
        this.label = fields[3];
        this.deadline = fields[4];
        this.description = fields[5];
    }

    public Personal(String uid, String pwd, String title, String label, String deadline, String description) {
        this.uid = uid;
        this.pwd = pwd;
        this.title = title;
        this.label = label;
        this.deadline = deadline;
        this.description = description;
    }

    public String getUID() {
        return uid;
    }

    public String getPwd() {
        return pwd;
    }

    public String getTitle() {
        return title;
    }

    public String getLabel() {
        return label;
    }

    public void fullDisplay() {
        System.out.println(
                "Title: " + title + ", Label: " + label + ", Deadline: " + deadline + ", Description: " + description);
    }

    public void pendingTasks() {
        LocalDate now = LocalDate.now();
        LocalDate deadlineDate = LocalDate.parse(deadline);
        if (deadlineDate.isAfter(now)) {
            fullDisplay();
        }
    }

    public void updateTasks(ArrayList<Personal> per, String uid, String pwd) {
        Scanner in = new Scanner(System.in);
        for (Personal p : per) {
            if (p.getUID().equals(uid) && p.getPwd().equals(pwd)) {
                System.out.print("Enter new title: ");
                p.title = in.nextLine();
                System.out.print("Enter new label: ");
                p.label = in.nextLine();
                System.out.print("Enter new deadline (in YYYY-MM-DD format): ");
                p.deadline = in.nextLine();
                System.out.print("Enter new description: ");
                p.description = in.nextLine();
                break;
            }
        }
    }

    @Override
    public String toString() {
        return uid + "," + pwd + "," + title + "," + label + "," + deadline + "," + description;
    }
}

class Professional {
    private String uid, pwd, title, label, deadline, description;
    private int importance, difficulty;

    public Professional(String data) {
        // Initialize from a data string
        String[] fields = data.split(",");
        this.uid = fields[0];
        this.pwd = fields[1];
        this.title = fields[2];
        this.label = fields[3];
        this.deadline = fields[4];
        this.description = fields[5];
        this.importance = Integer.parseInt(fields[6]);
        this.difficulty = Integer.parseInt(fields[7]);
    }

    public Professional(String uid, String pwd, String title, String label, String deadline, String description,
            int importance, int difficulty) {
        this.uid = uid;
        this.pwd = pwd;
        this.title = title;
        this.label = label;
        this.deadline = deadline;
        this.description = description;
        this.importance = importance;
        this.difficulty = difficulty;
    }

    public String getUID() {
        return uid;
    }

    public String getPwd() {
        return pwd;
    }

    public String getTitle() {
        return title;
    }

    public String getLabel() {
        return label;
    }

    public void fullDisplay() {
        System.out.println("Title: " + title + ", Label: " + label + ", Deadline: " + deadline + ", Description: "
                + description + ", Importance: " + importance + ", Difficulty: " + difficulty);
    }

    public void pendingTasks() {
        LocalDate now = LocalDate.now();
        LocalDate deadlineDate = LocalDate.parse(deadline);
        if (deadlineDate.isAfter(now)) {
            fullDisplay();
        }
    }

    public void updateTasks(ArrayList<Professional> pro, String uid, String pwd) {
        Scanner in = new Scanner(System.in);
        for (Professional p : pro) {
            if (p.getUID().equals(uid) && p.getPwd().equals(pwd)) {
                System.out.print("Enter new title: ");
                p.title = in.nextLine();
                System.out.print("Enter new label: ");
                p.label = in.nextLine();
                System.out.print("Enter new deadline (in YYYY-MM-DD format): ");
                p.deadline = in.nextLine();
                System.out.print("Enter new description: ");
                p.description = in.nextLine();
                System.out.print("Enter new importance (1-3): ");
                p.importance = in.nextInt();
                System.out.print("Enter new difficulty (1-3): ");
                p.difficulty = in.nextInt();
                in.nextLine(); // consume newline
                break;
            }
        }
    }

    @Override
    public String toString() {
        return uid + "," + pwd + "," + title + "," + label + "," + deadline + "," + description + "," + importance + ","
                + difficulty;
    }
}
