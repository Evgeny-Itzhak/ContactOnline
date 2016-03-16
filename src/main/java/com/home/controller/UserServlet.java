package com.home.controller;

import com.home.dao.UserDao;
import com.home.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Itzhak-Miryam on 14.03.2016.
 */
public class UserServlet extends HttpServlet {

    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/listUser.jsp";
    private static String START_PAGE = "/index.jsp";
    private UserDao userDao;

    public UserServlet() {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            forward = LIST_USER;
            int userId = Integer.parseInt(request.getParameter("userId"));
            userDao.deleteUser(userId);
            request.setAttribute("users", userDao.getAllUsers());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = userDao.getUserById(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")) {
            forward = LIST_USER;
            request.setAttribute("users", userDao.getAllUsers());
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        user.setUserId(Integer.parseInt(request.getParameter("userId")));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        try {
            Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dob"));
            user.setDob(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setEmail(request.getParameter("email"));
        String userId = request.getParameter("userId");

        List<User> users = userDao.getAllUsers();
        boolean userAlreadyIsInDb = false;

        for (User userFromDb : users) {
            if (userFromDb.getUserId() == user.getUserId()) {
                userAlreadyIsInDb = true;
            }
        }

        if (userId != null && !userAlreadyIsInDb) {
            userDao.addUser(user);
        } else {
            user.setUserId(Integer.parseInt(userId));
            userDao.updateUser(user);
        }

        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        request.setAttribute("users", userDao.getAllUsers());
        view.forward(request, response);

    }
}
