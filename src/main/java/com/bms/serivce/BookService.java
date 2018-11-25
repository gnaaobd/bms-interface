package com.bms.serivce;

import java.util.List;

import com.bms.model.Book;
import com.jfinal.plugin.activerecord.Page;

public class BookService {
	
	/**
	 * 所有的 dao 对象也放在 Service 中，并且声明为 private，避免 sql 满天飞
	 * sql 只放在业务层，或者放在外部 sql 模板，用模板引擎管理
	 */
	private Book dao = new Book().dao();
	
	public Page<Book> paginate(int pageNumber, int pageSize) {
		return dao.paginate(pageNumber, pageSize, "select *", "from book order by id asc");
	}
	
	public List<Book> findAll(){
		return dao.find("select * from book order by id asc");
	}
	
	public Book findById(int id) {
		return dao.findById(id);
	}
	
	public boolean deleteById(int id) {
		return dao.deleteById(id);
	}
	
	public boolean save() {
		return dao.save();
	}

	public boolean update() {
		return dao.update();
	}
}
