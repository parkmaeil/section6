package com.example.controller;

import com.example.entity.BookDTO;
import com.example.repository.BookDAOMyBatis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookUpdatePostController implements  Controller {
    @Override
    public String requestHandler(HttpServletRequest req, HttpServletResponse resp)
                                                           throws ServletException, IOException {
        try {
            req.setCharacterEncoding("utf-8");
            // 폼에서 넘어온 파라메터를 수집(DTO)
            // 1. 유효성검사
            String reqNum = req.getParameter("num");
            String title = req.getParameter("title");
            String reqPrice =req.getParameter("price"); // "10"->10, "AAA"->?
            String author = req.getParameter("author");
            String reqPage =req.getParameter("page");
            if(title==null || title.trim().isEmpty() || author==null || author.trim().isEmpty()){
                System.out.println("1. 제목과 저자는 필수 입력항목입니다.");
                // resp.sendRedirect("/MF01/error?msg=1"); // error.jsp(화면에 오류 메시지 출력)
                return "redirect:/error?msg=1";
            }
            int price=0;
            int page=0;
            Long num=null;
            try{
                price=Integer.parseInt(reqPrice);
                page=Integer.parseInt(reqPage);
                num=Long.parseLong(reqNum);
            }catch(NumberFormatException e){
                System.out.println("2. 가격과 페이지수는 정수여야 합니다.");
                //resp.sendRedirect("/MF01/error?msg=2"); // error.jsp(화면에 오류 메시지 출력)
                return "redirect:/error?msg=2";
            }
            if(price<=0 || page <=0){
                System.out.println("3. 가격과 페이지수는 양의 정수여야 합니다.");
                //resp.sendRedirect("/MF01/error?msg=3"); // error.jsp(화면에 오류 메시지 출력)
                return "redirect:/error?msg=3";
            }

            BookDTO dto=new BookDTO(num,title,price,author,page);
            BookDAOMyBatis dao=new BookDAOMyBatis();
            int cnt=dao.bookUpdate(dto);
            if(cnt>0){
                // 다시 리스트보기 페이지로 이동(redirect)
                // resp.sendRedirect("/MF01/list");
                //resp.sendRedirect("/MF01/view?num="+num);
                return "redirect:/view.do?num="+num;
            }else{
                System.out.println("수정실패");
            }

        }catch(Exception e){
           e.printStackTrace();
           // resp.sendRedirect("/MF01/error?msg=0");
            return  "redirect:/error?msg=0";
        }
        return null;
    }
}
