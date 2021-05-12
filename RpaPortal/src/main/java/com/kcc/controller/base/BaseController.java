package com.kcc.controller.base;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.CtField.Initializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

public class BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		// JSON Object 반환시 기본 256개 까지 바인딩 처리되나, 10000개 설정
		binder.setAutoGrowNestedPaths(true);
		binder.setAutoGrowCollectionLimit(10000);
		// registerCustomEditor 이용하면, 파라미터 변수 형 변환 가능
	}
}