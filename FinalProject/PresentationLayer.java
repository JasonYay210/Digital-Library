// Lily Chau, Nick Criss, Renee Bogdany, Chloe Calderon, Natnail Tolossa, Jason Chen
// ISTE 330 - Group 1

import java.awt.*;
import java.math.BigInteger;
import java.security.MessageDigest;

import javax.swing.*;

public class PresentationLayer {
    DataLayer dl = new DataLayer();
    public Font myFontForOutput = new Font("Courier", Font.PLAIN, 32);
    String databaseName = new String();
    String userName = new String();
    String password = new String();

    // public enum userrole{
    //     STUDENT, FACULTY, ORGANIZATION
    // }

    public PresentationLayer() {
        JPanel databaseBox = new JPanel(new GridLayout(2, 1));
        JLabel lblDatabase = new JLabel("Database name?");
        lblDatabase.setFont(myFontForOutput);
        databaseBox.add(lblDatabase);
        JTextField textfieldDatabaseName = new JTextField("researchSpecialty");
        textfieldDatabaseName.setFont(myFontForOutput);
        textfieldDatabaseName.setForeground(Color.BLUE);
        databaseBox.add(textfieldDatabaseName);
        JOptionPane.showMessageDialog(null, databaseBox,
                "Database name Input Prompt", JOptionPane.QUESTION_MESSAGE);
        databaseName = textfieldDatabaseName.getText();

        JPanel Inputbox = new JPanel(new GridLayout(2, 2));
        JLabel lblUser = new JLabel("Username  -> ");
        JLabel lblPassword = new JLabel("Password  -> ");
        JTextField tfUser = new JTextField("root");
        JTextField tfPassword = new JPasswordField("student");

        Inputbox.add(lblUser);
        Inputbox.add(tfUser);
        Inputbox.add(lblPassword);
        Inputbox.add(tfPassword);

        lblUser.setFont(myFontForOutput);
        tfUser.setFont(myFontForOutput);
        tfUser.setForeground(Color.BLUE);
        lblPassword.setFont(myFontForOutput);
        tfPassword.setFont(myFontForOutput);
        tfPassword.setForeground(Color.BLUE);

        String temp_password = new String();

        JOptionPane.showMessageDialog(null, Inputbox,
                "Default Password is 'student'", JOptionPane.INFORMATION_MESSAGE);

        userName = tfUser.getText();
        temp_password = tfPassword.getText();

        if (temp_password.equals("")) {
            password = "student";
        } else {
            password = temp_password;
        }

        dl.connect(databaseName, userName, password);
        //after connecting to the sql database it connects to the gui menu
        mainMenu();

        //closing database 
        System.out.println("Exiting...");
        System.out.println("\nClosing all connections to the database...\n");
        dl.close();

        java.util.Date today = new java.util.Date();
        System.out.println("\nProgram terminated @ " + today + "\n");
    }

    // GUI MENU for all users
    public void mainMenu() {
        String[] options = {"Faculty", "Student", "Organization", "Exit"};
        int guiChoice = JOptionPane.showOptionDialog(null, "Which of the following are you?", "Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        switch (guiChoice) {
            case 0:
                optionFaculty();
                break;
            case 1:
                optionStudent();
                break;
            case 2:
                optionOrganization();
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                mainMenu();
                break;
        }
    }

    // login prompt to faculty and students for a username and password
    private boolean promptForCred(String role) {
        int attempts = 0;
    while (attempts < 3) {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
            "Username:", usernameField,
            "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(null, message, role + " Login", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (authenticate(username, password, role)) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect username or password. Please try again.");
                attempts++;
            }
        } else {
            break; 
        }
    }
    return false; // Return false after 3 failed login
}
    // checks to see if password and user match
    private boolean authenticate(String username, String password, String role) {
        boolean isUsername = false;
        boolean isPassword = false;
        boolean isauthenticated = false;
        
        System.out.println("Role " + role);
        
        // //check if username exists
        isUsername = dl.checkUsername(username, role);
        if(isUsername) {
         
         isauthenticated = true;
         
         isPassword = dl.checkPassword(username, password, role);    
         if(isPassword)
            isauthenticated = true;
         else
            isauthenticated = false;     
         
        } else {
         isauthenticated = false;
        }
        return isauthenticated;
    }

    // takes user to faculty menu
    private void optionFaculty() {

        if(promptForCred("Faculty")){
        String[] facultyOptions = {"Add Faculty", "Update Faculty", "Add Abstract", "Add Specialty", "View Student Specialty matches", "Return to Main Menu"};
    
        int facultyChoice;
    
        do {
            facultyChoice = JOptionPane.showOptionDialog(null, "Faculty Menu", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, facultyOptions, facultyOptions[0]);
    
            switch (facultyChoice) {
                case 0:
                    // Add Faculty
                    addFaculty();
                    break;
                case 1:
                    updateFaculty();
                    break;
                case 2:
                    // Add Abstract
                    addAbstract();
                    break;
                case 3:
                    // Add Specialty
                    addSpecialty();
                    break;
                case 4:
                    // View Student Specialty matches
                    specialtyInterest();
                    break;
                case 5:
                    // Return to Main Menu
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (facultyChoice != 5);
    }
    else{
        mainMenu();
    }

    // Takes user to student menu
    }
    private void optionStudent() {
    if(promptForCred("Student")){   
        String[] studentOptions = {"Add Student", "Add Specialty", "View Faculty Specialty Matches", "View Faculty Abstract Matches", "Return to Main Menu"};

        int studentChoice;
    
        do {
            studentChoice = JOptionPane.showOptionDialog(
                null, "Student Menu", "Menu",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
                null, studentOptions, studentOptions[0]
            );switch (studentChoice) {
                case 0:
                    // Add Student
                    addStudent();
                    break;
                case 1:
                    // Add Specialty
                    addStudentSpecialty();
                    break;
                case 2:
                    // View Faculty Specialty Matches
                    checkSpecialtyPairing();
                    break;
                case 3:
                    // View Faculty Abstract Matches
                    searchKeyWords();
                    break;
                case 4:
                    // Return to Main Menu
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (studentChoice != 4);
    }
    else{
        mainMenu();;
    }
    }

    // takes user to organization menu
    private void optionOrganization() {

        String[] organizationOptions = {"Add Organization", "Find relevant student/faculty", "Return to Main Menu"};
    
        int orgChoice;
    
        do {
            orgChoice = JOptionPane.showOptionDialog(
                    null, "Organization Menu", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, organizationOptions, organizationOptions[0]
            );
    
            switch (orgChoice) {
                case 0:
                    // Add Organization
                    addOrganization();
                    break;
                case 1:
                    // Find relevant student/faculty
                    getOrganizationKeyword();
                    break;
                case 2:
                    // Return to Main Menu
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (orgChoice != 2);
    }
    
    // option in the faculty menu for faculty to add another faculty menu (should be out of the faculty menu and in the main menu)
    private void addFaculty() {
        JTextField fNameField = new JTextField();
        JTextField lNameField = new JTextField();
        JTextField uNameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField buildingNumField = new JTextField();
        JTextField officeNumField = new JTextField();
        JTextField workNumField = new JTextField();
        JTextField cellNumField = new JTextField();
        JTextField officeHrsField = new JTextField();

        JPanel facultyPanel = new JPanel(new GridLayout(10, 2));
        facultyPanel.add(new JLabel("First Name:"));
        facultyPanel.add(fNameField);
        facultyPanel.add(new JLabel("Last Name:"));
        facultyPanel.add(lNameField);
        facultyPanel.add(new JLabel("Username:"));
        facultyPanel.add(uNameField);
        facultyPanel.add(new JLabel("Email:"));
        facultyPanel.add(emailField);
        facultyPanel.add(new JLabel("Password:"));
        facultyPanel.add(passwordField);
        facultyPanel.add(new JLabel("Building Number:"));
        facultyPanel.add(buildingNumField);
        facultyPanel.add(new JLabel("Office Number:"));
        facultyPanel.add(officeNumField);
        facultyPanel.add(new JLabel("Work Number:"));
        facultyPanel.add(workNumField);
        facultyPanel.add(new JLabel("Cell Number:"));
        facultyPanel.add(cellNumField);
        facultyPanel.add(new JLabel("Office Hours:"));
        facultyPanel.add(officeHrsField);

        int result = JOptionPane.showConfirmDialog(null, facultyPanel,
                "Enter Faculty Information", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String fName = fNameField.getText();
            String lName = lNameField.getText();
            String uName = uNameField.getText();
            String email = emailField.getText();
            String pWord = new String(passwordField.getPassword());
            String encryptedPWord = encrypt(pWord);
            int buildingNum = Integer.parseInt(buildingNumField.getText());
            int officeNum = Integer.parseInt(officeNumField.getText());
            String workNum = workNumField.getText();
            String cellNum = cellNumField.getText();
            String officeHrs = officeHrsField.getText();

            dl.insertFacultyRecord(fName, lName, uName, encryptedPWord, email, buildingNum, officeNum, workNum, cellNum, officeHrs);
        }
    }

    // allows faculty to update their own information based on their ID
    public void updateFaculty() {
        JTextField facIdField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField lastNameField = new JTextField();
        JTextField buildingNumberField = new JTextField();
        JTextField passwordField = new JTextField();
    
        JPanel facultyPanel = new JPanel(new GridLayout(5, 2));
        facultyPanel.add(new JLabel("Faculty ID:"));
        facultyPanel.add(facIdField);
        facultyPanel.add(new JLabel("First Name:"));
        facultyPanel.add(firstNameField);
        facultyPanel.add(new JLabel("Last Name:"));
        facultyPanel.add(lastNameField);
        facultyPanel.add(new JLabel("Building Number:"));
        facultyPanel.add(buildingNumberField);
        facultyPanel.add(new JLabel("Password:"));
        facultyPanel.add(passwordField);
    
        int result = JOptionPane.showConfirmDialog(null, facultyPanel,
                "Enter Faculty Information to Update", JOptionPane.OK_CANCEL_OPTION);
    
        if (result == JOptionPane.OK_OPTION) {
            try {
                // Retrieve values from the fields and call the method to update faculty
                String faculty_ID = facIdField.getText();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                int buildingNumber = Integer.parseInt(buildingNumberField.getText());
                String password = passwordField.getText();
    
                dl.updateFaculty(faculty_ID, firstName, lastName, buildingNumber, password);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid data.");
            }
        }
    }
    
    // allows faculty to add their abstract, they would include title and content and their ID
    public void addAbstract() {
        JTextField facIdField = new JTextField();
        JTextField abstractTitleField = new JTextField();
        JTextField abstractContentField = new JTextField();

        JPanel abstractPanel = new JPanel(new GridLayout(3, 2));
        abstractPanel.add(new JLabel("Faculty ID:"));
        abstractPanel.add(facIdField);
        abstractPanel.add(new JLabel("Abstract Title:"));
        abstractPanel.add(abstractTitleField);
        abstractPanel.add(new JLabel("Abstract Content:"));
        abstractPanel.add(abstractContentField);

        int result = JOptionPane.showConfirmDialog(null, abstractPanel,
                "Enter Abstract Information", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Retrieve values from the fields and call the method to add abstract
            try {
            int facId = Integer.parseInt(facIdField.getText());
            String abstractTitle = abstractTitleField.getText();
            String abstractContent = abstractContentField.getText();

            dl.addAbstract(facId, abstractTitle, abstractContent);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid data.");
            }
        }
    }
    
    // Allows faculty to add a speciality interest (EX. Web-Development)
    public void addSpecialty() {
        JTextField facultyIdField = new JTextField();
        JTextField facSpecialField = new JTextField();

        JPanel specialtyPanel = new JPanel(new GridLayout(2, 2));
        specialtyPanel.add(new JLabel("Faculty ID:"));
        specialtyPanel.add(facultyIdField);
        specialtyPanel.add(new JLabel("Specialty:"));
        specialtyPanel.add(facSpecialField);

        int result = JOptionPane.showConfirmDialog(null, specialtyPanel,
                "Enter Specialty Information", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Retrieve values from the fields and call the method to add specialty
            try {
            int facultyId = Integer.parseInt(facultyIdField.getText());
            String facSpecial = facSpecialField.getText();

            // adds to database
            dl.addFacultySpecialty(facultyId, facSpecial);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid data.");
            }
        }
    }
    
    // returns a student match to faculty who are searching for students who have similar keyword interests
    public void specialtyInterest() {
        JTextField facultyInterestField = new JTextField();

        JPanel interestPanel = new JPanel(new GridLayout(1, 2));
        interestPanel.add(new JLabel("Specialty Interest:"));
        interestPanel.add(facultyInterestField);

        int result = JOptionPane.showConfirmDialog(null, interestPanel,
                "Enter Specialty Interest", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
            // Retrieve the entered Specialty Interest and call the method to search
            String facultyInterest = facultyInterestField.getText();
            String output = dl.searchByInterest(facultyInterest);

            //displays the result of sql search
            JOptionPane.showMessageDialog(null, output, "Student Match Information", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid data.");
            }
        }
    }

    // allows student to add themselves
    public void addStudent() {
        JTextField lastNameField = new JTextField();
        JTextField firstNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField portfolioLinkField = new JTextField();
        JTextField userNameField = new JTextField();
        JTextField passField = new JTextField();

        JPanel studentPanel = new JPanel(new GridLayout(6, 2));
        studentPanel.add(new JLabel("Last Name:"));
        studentPanel.add(lastNameField);
        studentPanel.add(new JLabel("First Name:"));
        studentPanel.add(firstNameField);
        studentPanel.add(new JLabel("Username:"));
        studentPanel.add(userNameField);
        studentPanel.add(new JLabel("Password:"));
        studentPanel.add(passField);
        studentPanel.add(new JLabel("Email:"));
        studentPanel.add(emailField);
        studentPanel.add(new JLabel("Portfolio Link:"));
        studentPanel.add(portfolioLinkField);

        int result = JOptionPane.showConfirmDialog(
            null, studentPanel,
            "Enter Student Information", JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String lastName = lastNameField.getText();
            String firstName = firstNameField.getText();
            String email = emailField.getText();
            String portfolioLink = portfolioLinkField.getText();
            String username = userNameField.getText();
            String password = passField.getText();
            String encryptPass = encrypt(password);

            dl.addStudent(lastName, firstName, username, encryptPass, email, portfolioLink);
        }
    }

    // allows students to declare specialtiies and interests
    public void addStudentSpecialty() {
        JTextField studentIdField = new JTextField();
        JTextField specialtiesField = new JTextField();
    
        JPanel studentSpecialtyPanel = new JPanel(new GridLayout(2, 2));
        studentSpecialtyPanel.add(new JLabel("Student ID:"));
        studentSpecialtyPanel.add(studentIdField);
        studentSpecialtyPanel.add(new JLabel("Specialties (comma-separated):"));
        studentSpecialtyPanel.add(specialtiesField);
    
        int result = JOptionPane.showConfirmDialog(
                null, studentSpecialtyPanel,
                "Enter Student Specialties", JOptionPane.OK_CANCEL_OPTION
        );
    
        if (result == JOptionPane.OK_OPTION) {
            try {
                int student_ID = Integer.parseInt(studentIdField.getText());
                String specialties = specialtiesField.getText();
                dl.addStudentSpecialty(student_ID, specialties);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid student ID.");
            }
        }
    }

    // for faculty to check student speciality pairings
    public void checkSpecialtyPairing() {
        JTextField specialtyField = new JTextField();
    
        JPanel checkSpecialtyPairingPanel = new JPanel(new GridLayout(1, 2));
        checkSpecialtyPairingPanel.add(new JLabel("Specialty:"));
        checkSpecialtyPairingPanel.add(specialtyField);
    
        int result = JOptionPane.showConfirmDialog(
                null, checkSpecialtyPairingPanel,
                "Check Specialty Pairing", JOptionPane.OK_CANCEL_OPTION
        );
    
        if (result == JOptionPane.OK_OPTION) {
            String specialty = specialtyField.getText();
            String output = dl.checkSpecialtyPairing(specialty);

            // displays pairing results
            JOptionPane.showMessageDialog(null, output, "Specialty Pairing Result", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // returns students the information of faculty memebers whose abstract contain the speciality searched word
    public void searchKeyWords() {
        JTextField keyWordField = new JTextField();
    
        JPanel searchKeyWordsPanel = new JPanel(new GridLayout(1, 2));
        searchKeyWordsPanel.add(new JLabel("Search Keyword in abstracts:"));
        searchKeyWordsPanel.add(keyWordField);
    
        int result = JOptionPane.showConfirmDialog(
                null, searchKeyWordsPanel,
                "Search Keywords", JOptionPane.OK_CANCEL_OPTION
        );
    
        if (result == JOptionPane.OK_OPTION) {
            String keyWord = keyWordField.getText();
            String output = dl.searchKeyWords(keyWord);

            // displays the faculty information of matching specialty words from faculty abstracts
            JOptionPane.showMessageDialog(null, output, "Faculty Contacts", JOptionPane.INFORMATION_MESSAGE);

        }
    }
    private void addOrganization() {
        JTextField orgNameField = new JTextField();
        JTextField keywordField = new JTextField();
    
        JPanel orgPanel = new JPanel(new GridLayout(2, 2));
        orgPanel.add(new JLabel("Organization Name:"));
        orgPanel.add(orgNameField);
        orgPanel.add(new JLabel("Keyword:"));
        orgPanel.add(keywordField);
    
        int result = JOptionPane.showConfirmDialog(null, orgPanel,
                "Enter Organization Information", JOptionPane.OK_CANCEL_OPTION);
    
        if (result == JOptionPane.OK_OPTION) {
            String orgName = orgNameField.getText();
            String keyword = keywordField.getText();
    
            dl.addOrganization(orgName, keyword);
        }
    }

    // displays faculty and student information to the organization for matching keywords
    private void getOrganizationKeyword() {
        JTextField orgIdField = new JTextField();
    
        JPanel orgPanel = new JPanel(new GridLayout(1, 2));
        orgPanel.add(new JLabel("Organization ID:"));
        orgPanel.add(orgIdField);
    
        int result = JOptionPane.showConfirmDialog(null, orgPanel,
                "Enter Organization ID", JOptionPane.OK_CANCEL_OPTION);
    
        if (result == JOptionPane.OK_OPTION) {
            try {
                int orgId = Integer.parseInt(orgIdField.getText());
    
                String keyword = dl.getOrganizationKeyword(orgId);
    
                //faculty
                String facAb = dl.checkSpecialtyPairing(keyword);
                String facSp = dl.searchKeyWords(keyword);

                //student
                String stuSp = dl.searchByInterest(keyword);

                String message = "Faculty Matches by Specialty Pairing:\n" + facAb + "\n" +
                             "Faculty Matches by Keywords:\n" + facSp + "\n" +
                             "\nStudent Matches by Interest:\n" + stuSp;

                JOptionPane.showMessageDialog(null, message, "Student and Faculty Matches with " + keyword, JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid Organization ID.");
            }
        }
    }

    // password encryption
    public static String encrypt(String secret) {
        String shal = "";
        String value = new String(secret);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(value.getBytes("utf8"));
            shal = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shal;
    }

    public static void main(String[] args) {
        System.out.println("Created by Group 1");
        new PresentationLayer();
    }
}