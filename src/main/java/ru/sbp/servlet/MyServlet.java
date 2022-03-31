package ru.sbp.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sbp.compare.Person;
import sbp.compare.PersonExceptions;
import sbp.jdbc.PersonDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Класс MyServlet содержит реализацию сервлета
 *
 * @version 1.0
 * @autor Sergey Proshchaev
 */
public class MyServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(MyServlet.class);

    /**
     * Переопределение метода doGet
     *
     * @param - HttpServletRequest req
     * @param - HttpServletResponse resp
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("doGet() started");

        PersonDAO personDAO = new PersonDAO();

        Collection<Person> allPersonList = personDAO.selectFromTableAllPerson();
        req.setAttribute("allPersonList", allPersonList);

        if (req.getRequestURI().equalsIgnoreCase("/app/api")) {
            logger.info("forward /allPersons.jsp");
            getServletContext().getRequestDispatcher("/allPersons.jsp").forward(req, resp);
        }

        if (req.getRequestURI().equalsIgnoreCase("/app/api/create")) {
            logger.info("forward /createPerson.jsp");
            getServletContext().getRequestDispatcher("/createPerson.jsp").forward(req, resp);
        }

        logger.info("doGet() completed");
    }

    /**
     * Переопределение метода doPost
     *
     * @param - HttpServletRequest req
     * @param - HttpServletResponse resp
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("doPost() started");

        PersonDAO personDAO = new PersonDAO();

        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String city = req.getParameter("city");

        try {
            Person person = new Person(name, city, age);
            personDAO.addToTablePerson(person);
            logger.info("forward /allPersons.jsp");
            getServletContext().getRequestDispatcher("/allPersons.jsp").forward(req, resp);
        } catch (PersonExceptions ex) {
            ex.printStackTrace();
            logger.error("Error: " + ex);
        }

        logger.info("doPost() completed");

    }
}