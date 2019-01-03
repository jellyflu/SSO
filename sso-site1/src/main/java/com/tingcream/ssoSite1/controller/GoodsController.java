package com.tingcream.ssoSite1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品controller  不用登陆也可以访问
 * @author jelly
 *
 */
@Controller
public class GoodsController {
	
	@RequestMapping("/goods_list")
	public String goods_list(HttpServletRequest request,
			HttpServletResponse response ) {
		
		return "/goods/goods_list.jsp";
	}

}
