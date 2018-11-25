package com.bms.controller;

import java.util.ArrayList;
import java.util.List;

import com.bms.model.Book;
import com.bms.serivce.BookService;
import com.bms.utils.APIResult;
import com.bms.validator.BookValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;

public class BookController extends Controller{
	
	@Inject
	BookService bookService;
	
	/**
	 * 解决跨域问题
	 */
	private void crossDomain() {
		this.getResponse().addHeader("Access-Control-Allow-Origin", "*");
	}
	
	public void index() {
		this.crossDomain();
		this.renderText("");
	}
	
	public void query() {
		this.crossDomain();
		
		List<Book> books = new ArrayList<>();
		Integer id = this.getParaToInt("id");
		if(id != null ) {
			Book book = bookService.findById(id);
			books.add(book);
		}else {
			books = bookService.findAll();
		}
		this.renderJson(new APIResult<List<Book>>(200, "查询成功", books));
	}
	
	@Before(BookValidator.class)
	public void update() {
		this.crossDomain();
		
		Book book = this.getModel(Book.class, "");
//		bookService.update();
		boolean isUpdate = book.update();
		this.renderJson(new APIResult<Boolean>(200, "", isUpdate));
	}
	
	@Before(BookValidator.class)
	public void add() {
		this.crossDomain();
		
		Book book = this.getModel(Book.class, "");
//		bookService.update();
		boolean isAdd = book.dao().save();
		this.renderJson(new APIResult<Boolean>(200, "", isAdd));
	}
	
	public void delete() {
		this.crossDomain();
		
		Integer id = this.getParaToInt("id");
		boolean isDelete = bookService.deleteById(id);
		this.renderJson(new APIResult<Boolean>(200, "", isDelete));
	}
	
}
