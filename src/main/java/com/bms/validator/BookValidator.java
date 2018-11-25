package com.bms.validator;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class BookValidator extends Validator{

	@Override
	protected void validate(Controller c) {
		validateRequiredString("name", "nameMsg", "请输入书籍名称");
	}

	@Override
	protected void handleError(Controller c) {
		// TODO Auto-generated m∂ethod stub
		
	}

}
