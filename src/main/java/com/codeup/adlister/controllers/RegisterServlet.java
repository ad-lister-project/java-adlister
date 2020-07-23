package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt(12));
        String passwordConfirmation = request.getParameter("confirm_password");


//        String hashPassword = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt(12));
//        boolean pwMatch2 = BCrypt.checkpw("qwerty", hash2);
//        boolean validAttempt = password.equals(user.getPassword());


        // validate input
        boolean inputHasErrors = username.isEmpty()
            || email.isEmpty()
            || password.isEmpty()
            || (! password.equals(passwordConfirmation));

        if (inputHasErrors) {
            response.sendRedirect("/register");
            return;
        }

        // create and save a new user
        User user = new User(username, email, password);
        DaoFactory.getUsersDao().insert(user);

//        if(invalidRegister){
//            response.sendRedirect("/register");
//        } else {
//            request.getSession().setAttribute("user", username);
//            User user = new User(username, email, password);
//            DaoFactory.getUserDao().insert(user);
//            response.sendRedirect("/profile");
//        }
        response.sendRedirect("/login");
    }
}
