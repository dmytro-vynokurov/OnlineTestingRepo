/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfiller;

import beans.Answer;
import beans.Question;
import beans.Student;
import beans.Subject;
import beans.Test;
import beans.Tutor;
import dao.AnswerDAO;
import dao.QuestionDAO;
import dao.StudentDAO;
import dao.SubjectDAO;
import dao.TestDAO;
import dao.TutorDAO;
import dao.logic.DAOFactory;
import dictionaries.BeanType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinokurov
 */
public class DBFiller {

    private static final int STUDENT_NUMBER = 150;
    private static final int TUTOR_NUMBER = 30;
    private static final String NAMES_FILE = "namesOut.txt";
    private static final String SURNAMES_FILE = "surnames.txt";
    private static final String SUBJECTS_FILE = "subjects.txt";
    private static int theIndex = 0;  //index for generating login/password

    private static List<String> getList(String fileName) {
        List<String> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String s;
            while ((s = br.readLine()) != null) {
                s = new String(s);
                result.add(s);
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(DBFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private static String getRand(List<String> list) {
        int index = (int) (Math.random() * list.size());
        return list.get(index);
    }

    private static void deployStudents() {
        StudentDAO studentDAO = DAOFactory.getStudentDAO();

        List<String> surnames = getList(SURNAMES_FILE);
        List<String> names = getList(NAMES_FILE);

        String name;
        String surname;
        String middleName;
        String login;
        String password;

        for (int i = 0; i < STUDENT_NUMBER; i++) {
            name = getRand(names);
            surname = getRand(surnames);
            middleName = getRand(names);
            login = "student" + (++theIndex);
            password = "root";

            studentDAO.addStudent(name, surname, middleName, login, password);
        }
    }

    private static void deployTutors() {
        TutorDAO tutorDAO =DAOFactory.getTutorDAO();

        List<String> surnames = getList(SURNAMES_FILE);
        List<String> names = getList(NAMES_FILE);

        String name;
        String surname;
        String middleName;
        String login;
        String password;

        for (int i = 0; i < TUTOR_NUMBER; i++) {
            name = getRand(names);
            surname = getRand(surnames);
            middleName = getRand(names);
            login = "tutor" + (++theIndex);
            password = "root";

            tutorDAO.addTutor(name, surname, middleName, login, password);
        }
    }

    private static void deploySubjects() {
        SubjectDAO subjectDAO =DAOFactory.getSubjectDAO();

        List<String> subjects = getList(SUBJECTS_FILE);
        List<String> nonRepeatedSubjects = new ArrayList<>();

        for (String subject : subjects) {
            if (!(nonRepeatedSubjects.contains(subject))) {
                nonRepeatedSubjects.add(subject);
                subjectDAO.addSubject(subject);
            }
        }
    }

    private static void deployTests() {
        TestDAO testDAO = DAOFactory.getTestDAO();
        SubjectDAO subjectDAO = DAOFactory.getSubjectDAO();
        TutorDAO tutorDAO = DAOFactory.getTutorDAO();

        List<Tutor> tutorList = tutorDAO.getTutors();
        List<Subject> subjectList = subjectDAO.getSubjects();

        int tutorNumber;
        int subjectNumber;


        //creating tests with existing tutors and subjects
        for (int i = 0; i < 50; i++) {
            tutorNumber = (int) (Math.random() * tutorList.size());
            subjectNumber = (int) (Math.random() * subjectList.size());
            testDAO.addTest(tutorList.get(tutorNumber),
                    subjectList.get(subjectNumber));
        }


        //Let's make 3/4 of the tests ready
        List<Test> testList = testDAO.getTests();

        for (int i = 0; i < 0.75 * testList.size(); i++) {
            testDAO.markReady(testList.get(i));
        }

    }

    private static void deployQuestions() {
        QuestionDAO questionDAO = DAOFactory.getQuestionDAO();

        TestDAO testDAO = DAOFactory.getTestDAO();
        List<Test> testList = testDAO.getTests();

        //creating questions with many answers
        for (int i = 0; i < testList.size() / 2; i++) {
            questionDAO.addQuestion(testList.get(i), "Question #" + i, false);
        }

        //creating questions with one addAnswer
        for (int i = testList.size() / 2; i < testList.size(); i++) {
            questionDAO.addQuestion(testList.get(i), "Question #" + i, true);
        }

    }

    private static List<Question> getAllQuestions() {
        QuestionDAO questionDAO = DAOFactory.getQuestionDAO();
        TestDAO testDAO = DAOFactory.getTestDAO();

        List<Question> questionList = new ArrayList<>();
        List<Test> testList = testDAO.getTests();
        for (Test test : testList) {
            questionList.addAll(questionDAO.getQuestions(test));
        }
        return questionList;
    }

    private static void deployAnswers() {
        AnswerDAO answerDAO = DAOFactory.getAnswerDAO();

        List<Question> questionList = getAllQuestions();

        int answersForCurrentQuestion;
        boolean answerIsCorrect;
        for (Question question : questionList) {
            answersForCurrentQuestion = (int) (Math.random() * 5 + 2);
            if (Math.random() > 0.5) {
                answerIsCorrect = true;
            } else {
                answerIsCorrect = false;
            }
            for (int i = 0; i < answersForCurrentQuestion; i++) {
                answerDAO.addAnswer(question, "Answer #" + i + " for Question #"
                        + question.getIDQuestion(), answerIsCorrect);
            }
        }
    }

    private static List<Answer> getAllAnswers() {
AnswerDAO answerDAO = DAOFactory.getAnswerDAO();
        List<Question> questionList = getAllQuestions();
        List<Answer> answerList = new ArrayList<>();
        for (Question question : questionList) {
            answerList.addAll(answerDAO.getAnswers(question));
        }
        return answerList;
    }

    private static void deployStudentAnswer() {
        StudentDAO studentDAO = DAOFactory.getStudentDAO();

        List<Student> studentList = studentDAO.getStudents();
        List<Answer> answerList = getAllAnswers();

        //about 20% of all the answers are chosen by students
        for (Student student : studentList) {
            for (Answer answer : answerList) {
                if (Math.random() > 0.8) {
                    studentDAO.addAnswer(student, answer);
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        deployStudents();
        deployTutors();
        deploySubjects();
        deployTests();
        deployQuestions();
        deployAnswers();
        deployStudentAnswer();
    }
}
