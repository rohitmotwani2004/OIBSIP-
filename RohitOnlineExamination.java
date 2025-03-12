import java.util.*;

class Question {
    String question, optionA, optionB, optionC, correctAnswer;

    Question(String question, String optionA, String optionB, String optionC, String correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correctAnswer = correctAnswer;
    }

    void display() {
        System.out.println(question);
        System.out.println("A. " + optionA);
        System.out.println("B. " + optionB);
        System.out.println("C. " + optionC);
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class User {
    private String username, password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    boolean login(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }

    void updateProfile(String newUsername, String newPassword) {
        this.username = newUsername;
        this.password = newPassword;
        System.out.println("Profile updated successfully!");
    }

    String getUsername() {
        return username;
    }
}

class Exam {
    private List<Question> questions = new ArrayList<>();
    private int score = 0;
    private boolean timeUp = false;
    
    Exam() {
        questions.add(new Question("What is Java?", "Programming Language", "Coffee", "Game", "A"));
        questions.add(new Question("JVM stands for?", "Java Virtual Machine", "Java Visual Maker", "Java Viewer Mode", "A"));
        questions.add(new Question("Who developed Java?", "Microsoft", "Sun Microsystems", "Apple", "B"));
        questions.add(new Question("Which keyword is used to define a class?", "class", "define", "struct", "A"));
        questions.add(new Question("What is the default value of an int?", "0", "null", "undefined", "A"));
    }

    void startExam(Scanner sc) {
        System.out.println("Exam Started! You have 30 seconds.");
        Timer timer = new Timer();
        
        timer.schedule(new TimerTask() {
            public void run() {
                timeUp = true;
                System.out.println("\nTime is up! Auto-submitting your exam.");
            }
        }, 30000); // 30 seconds timer

        for (Question q : questions) {
            if (timeUp) break;
            q.display();
            System.out.print("Enter your choice (A/B/C): ");
            String answer = sc.next();
            if (q.checkAnswer(answer)) {
                score++;
            }
        }
        timer.cancel();
        System.out.println("Exam Finished. Your score: " + score + "/" + questions.size());
    }
}

public class  RohitOnlineExamination {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, User> users = new HashMap<>();

        // User Registration
        System.out.println("Register new user:");
        System.out.print("Enter username: ");
        String regUsername = sc.next();
        System.out.print("Enter password: ");
        String regPassword = sc.next();
        User user = new User(regUsername, regPassword);
        users.put(regUsername, user);

        // User Login
        System.out.println("\nLogin:");
        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        if (users.containsKey(username) && users.get(username).login(username, password)) {
            System.out.println("Login Successful!");

            while (true) {
                System.out.println("\n1. Start Exam\n2. Update Profile\n3. Logout");
                System.out.print("Enter choice: ");
                
                if (!sc.hasNextInt()) {
                    System.out.println("Invalid input! Enter a number.");
                    sc.next(); // Consume invalid input
                    continue;
                }

                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        Exam exam = new Exam();
                        exam.startExam(sc);
                        break;
                    case 2:
                        System.out.print("Enter new username: ");
                        String newUsername = sc.next();
                        System.out.print("Enter new password: ");
                        String newPassword = sc.next();
                        user.updateProfile(newUsername, newPassword);
                        users.remove(username);
                        users.put(newUsername, user);
                        username = newUsername;
                        break;
                    case 3:
                        System.out.println("Logging out...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid option! Try again.");
                }
            }
        } else {
            System.out.println("Invalid credentials!");
        }
        sc.close();
    }
}
