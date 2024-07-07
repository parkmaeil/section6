package com.example.controller;

import com.example.entity.BookDTO;
import com.example.repository.BookDAOMyBatis;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookDeleteController implements  Controller{
    @Override
    public String requestHandler(HttpServletRequest req, HttpServletResponse resp)
                                                           throws ServletException, IOException {

          int num=Integer.parseInt(req.getParameter("num")); // "3"->3
          BookDAOMyBatis dao=new BookDAOMyBatis();
          int cnt=dao.bookDelete(num);
          return "redirect:/list.do"; //   -> X /WEB-INF/views//MF01/list.jsp / redirect -> /MF01/list
    }
}
