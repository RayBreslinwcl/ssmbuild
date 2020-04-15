package com.ray.controller;

import com.ray.pojo.Books;
import com.ray.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

    /**
     * 查询全部书籍
     *
     * @param model
     * @return
     */
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();
        model.addAttribute("list", list);
        return "allBook";
    }


    /**
     * 添加书籍
     */
    @RequestMapping("/toAddBook")
    public String toAddPaper() {
        return "addBook";
    }

    @RequestMapping("/addBook")
    public String addPaper(Books books) {
        System.out.println(books);
        bookService.addBook(books);
        return "redirect:/book/allBook";
    }


    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Model model, int id) {
        Books books = bookService.queryBookById(id);
        System.out.println(books);
        model.addAttribute("book",books );
        return "updateBook";
    }

    @RequestMapping("/updateBook")
    public String updateBook(Model model, Books book) {
        System.out.println(book);
        bookService.updateBook(book);
        Books books = bookService.queryBookById(book.getBookID());
        model.addAttribute("books", books);
        return "redirect:/book/allBook";
    }


    @RequestMapping("/del/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id) {
        bookService.deleteBookById(id);
        return "redirect:/book/allBook";
    }


    /**
     * 查询书籍
     * @param queryBookName
     * @param model
     * @return
     */
    @RequestMapping("/queryBook")
    public String queryBook(String queryBookName,Model model){

        Books books=bookService.queryBookByName(queryBookName);
        List<Books> list=new ArrayList<>();
        //为了和allBook接受的书籍类型一致，所以新建一个list
        list.add(books);

        //当输入为空的时候，查询所有的书籍
        if(books==null){
            list=bookService.queryAllBook();
            //同时报错
            model.addAttribute("error","未查询到！");
        }
        model.addAttribute("list",list);
        return "allBook";

    }


}
