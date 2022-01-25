package ch.tbz;

import java.util.Scanner;
import java.util.logging.Logger;


public class Menu {
    private User user = null;
    private final Scanner scan = new Scanner(System.in);
    private final AuthService auth = new AuthService();
    // Create a Logger
    Logger logger = Logger.getLogger(Menu.class.getName());
    TaskService taskService = new TaskService();
    private int taskId = 0;

    //Hardcode

    /**
     * Hier werden die Hardcode Daten eingelesen.
     */
    private void initialData() {

        AssignedTask first = taskService.createAssignedTask(11, 1, auth.getUsers().get(0).getId(), "Task nr 1", "Erster", "25.12.2002");
        AssignedTask sec = taskService.createAssignedTask(12, 1, auth.getUsers().get(0).getId(), "Task nr 2", "Zweiter", "26.01.2022");
        AssignedTask third = taskService.createAssignedTask(12, 1, auth.getUsers().get(0).getId(), "Task nr 3", "Dritter", "26.01.2022");
        AssignedTask fourth = taskService.createAssignedTask(13, 1, auth.getUsers().get(0).getId(), "Task nr 4", "Vierter", "26.01.2022");
        PrivateTask fistpriv = taskService.createPrivate(14, 1, "star", "Test2", "Test1", "31.03.2003");
        PrivateTask secpriv = taskService.createPrivate(14, 1, "star", "Test1", "Test2", "31.03.2003");

    }


    public void Run() {
        initialData();
        while (true) {
            if (user != null) {
                if (user.isLoggedIn()) {
                    showWhenLoggedIn();
                }
            }
            showWhenLoggedout();
        }
    }

    /**
     * Menu wird angezeigt wenn der Benutzer eingeloggt ist.
     */
    private void showWhenLoggedIn() {
        try {
            do {
                System.out.println();
                System.out.println("*****************************");
                System.out.println("Bitte waehlen Sie:");
                System.out.println("x: exit");
                printMenu();
                //Logout
                String input = scan.next();
                if (input.equals("x")) {
                    System.out.println("Wir wünschen einen schoenen Tag!");
                    auth.Logout(user.getId());
                }

                //Admin Switch Case

                switch (input) {
                    case "c":
                        System.out.println("create Task");
                        createTask();
                        break;
                    case "e":
                        this.editTask();
                        break;
                    case "d":
                        this.deleteTask();
                        break;
                    case "a":
                        this.assignUserToTask();
                        break;
                    case "l":
                        this.listTask();
                        break;
                    case "x":
                        break;
                    default:
                        System.out.println("Eingabe nicht korrekt!");
                }
            } while (user.isLoggedIn());
        } catch (Exception e) {
            System.out.println("Ein Fehler ist aufgetreten!" + e);
        }
    }

    /**
     * AdminMenu (print options)
     */
    private void printMenu() {
        System.out.println("c Task erstellen");
        System.out.println("e Task bearbeiten");
        System.out.println("d Task loeschen");
        System.out.println("a Task an User zuweisen");
        System.out.println("l Tasks auflisten");
    }


    /**
     * Logout
     */
    private void exit() {
        System.out.println("Wir wünschen Ihnen einen schoenen Tag!");
        if (user != null)
            auth.Logout(user.getId());
        System.exit(0);
    }

    /**
     * Menu when User logged out
     */

    private void showWhenLoggedout() {
        do {
            System.out.println("Bitte waehlen Sie:");
            System.out.println("e: Einloggen");
            System.out.println("x: Beenden");
            var input = scan.next();
            if (input.equals("x")) {
                exit();
            } else if (input.equals("e")) {
                System.out.print("Geben Sie den Benutzernamen ein: ");
                var un = scan.next();
                System.out.print("Geben Sie ihr Passwort ein: ");
                var pw = scan.next();
                user = auth.Login(un, pw);
            } else {
                System.out.println("Eingabe nicht korrekt!");
            }
        } while (user == null);
    }

    /**
     * Choose User
     * @return Returnt die ID des Users
     */
    private int chooseUser() {
        for (int i = 0; i < 10; i++) {
            for (User user : auth.getUsers()) {
                System.out.println(user.getId() + " : " + user.getName());
            }
            System.out.println("Waehle einen User:");
            int userId = Integer.parseInt(scan.next());
            for (User user : auth.getUsers()) {
                if (userId == user.getId()) {
                    System.out.println("Ihr User hat die Nummer: " + userId);
                    return userId;
                }
            }
            logger.warning("USER IST NICHT VORHANDEN");
        }
        logger.warning("ES WURDE MEHR ALS 10 MAL EINEN FALSCHEN USER GENOMMEN");
        return auth.getUsers().get(0).getId();
    }


    /**
     * Choose Task
     * @return Retunt die Id des Tasks
     */
    private int chooseTask() {
        for (int i = 0; i < 10; i++) {
            for (Task task : this.taskService.getAllTasks()) {
                System.out.println(task.getId() + " : " + task.getTitle());
            }
            System.out.println("Waehle einen Task:");
            int taskId = Integer.parseInt(scan.next());
            for (Task task : this.taskService.getAllTasks()) {
                if (taskId == task.getId()) {
                    return taskId;
                }
            }
            logger.warning("TASK IST NICHT VORHANDEN!");
        }
        logger.warning("ES WURDE MEHR ALS 10 MAL EIN FALSCHER TASK GENOMMEN");
        return taskService.getAllTasks().get(0).getId();
    }

    /**
     * MENU Print all Tasks
     */
    private void listTask() {
        for (Task task : taskService.getAllTasks()) {
            if (task.getCreator() == user.getId()) {
                System.out.println("*****************************");
                System.out.println("Task: " + task.getTitle());
                if (task.assignedTask) {
                    System.out.println("Dies ist ein Zugewiesener Task");
                } else {
                    System.out.println("Dies ist ein Privater Task");
                }
                System.out.println("Der Task sollte am " + task.dueDate + " erledigt werden.");
                System.out.println("Erstellt von: " + getUserFromId(task.getCreator()).getName());
                if (task instanceof AssignedTask) {
                    System.out.println("Zugewiesen an: " + getUserFromId(((AssignedTask) task).getAssignto()).getName());
                }
                System.out.println("Beschreibung: " + task.getDescription());
            }
        }
    }

    /**
     * MENU assign User to Task
     */
    private void assignUserToTask() {
        boolean hasAssignedTask = false;
        AssignedTask newTask = null;
        do {
            int taskId = chooseTask();
            Task task = this.getTaskById(taskId);
            if (task != null && task.assignedTask) {
                newTask = (AssignedTask) task;
                hasAssignedTask = true;
            }
        }
        while (!hasAssignedTask);

        int userId = chooseUser();
        taskService.assignUserToTask(newTask, userId);
    }

    /**
     * MENU edit Task
     */
    private void editTask() {
        int taskId = chooseTask();
        Task task = getTaskById(taskId);
        System.out.println("Jetziger Titel: " + task.getTitle());
        System.out.println("Geben sie einen neuen Titel ein");
        String newTitle = scan.next();

        System.out.println("Jetzige Beschreibung: " + task.getDescription());
        System.out.println("Geben sie eine neue Beschreibung ein");
        String newDescribtion = scan.next();

        System.out.println("Jetziges Faellikeitsdatum: " + task.getDueDate());
        System.out.println("Geben sie ein neues Faelligkeisdatum ein");
        String newDueDate = scan.next();

        taskService.editTask(taskId, newDescribtion, newTitle, newDueDate);
    }


    /**
     * Get all Users
     * @param id User id
     * @return Returnt den User der Id
     */
    private User getUserFromId(int id) {
        for (User user : auth.getUsers()) {
            if (user.getId() == id) {
                return user;
            }
        }
        logger.warning("User wurde nicht gefunden!");
        return null;
    }

    /**
     * Menu create Task
     */
    private void createTask() {
        System.out.println("Wie heisst dein Task? [Titel]");
        String title = scan.next();
        System.out.println("Beschreibe deinen Task: [Beschreibung]");
        String decription = scan.next();
        System.out.println("Wann soll der Task erledigt werden? [dd/mm/yyyy]");
        String dueDate = scan.next();
        System.out.println("Wollen sie den Task jemandem zuweisen? [ja, nein]");
        String assignQuestion = scan.next();
        if (assignQuestion.equals("ja")) {
            int userId = this.chooseUser();
            this.taskId++;
            System.out.println("Du hast ein Assigend Task erstellt!");
            AssignedTask task = taskService.createAssignedTask(taskId, user.getId(), userId, decription, title, dueDate);
            taskService.assignUserToTask(task, userId);
        } else {
            System.out.println("Du hast einen Privaten Task erstellt!");
            this.taskId++;
            taskService.createPrivate(this.taskId, user.getId(), "*", decription, title, dueDate);
        }
    }

    /**
     * Task für ID
     * @param id ID von Task
     * @return  Returnt den Task zur entsprechenden ID
     */
    private Task getTaskById(int id) {
        for (Task task : taskService.getAllTasks()) {
            if (id == task.getId()) {
                return task;
            }
        }
        logger.warning("ES EXISTIERT KEIN TASK MIT DIESER ID, WIR HABEN DEN ERSTEN TASK GENOMMEN");
        return taskService.getAllTasks().get(0);
    }

    /**
     * Delete Task
     */
    private void deleteTask() {
        int taskId = this.chooseTask();
        try {
            System.out.println("Der Task: " + getTaskById(taskId).getTitle() + " wurde gelOEscht");
            taskService.deleteTask(taskId);
        } catch (NullPointerException e) {
            logger.warning("DER TASK KONNTE NICHT GELOESCHT WERDEN");
        }
    }
}
