package com.xxl.mq.broker.controller;

import com.xxl.mq.broker.controller.annotation.PermessionLimit;
import com.xxl.mq.broker.core.result.ReturnT;
import com.xxl.mq.broker.service.IXxlMqMessageService;
import com.xxl.mq.client.message.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Base 
 * @author xuxueli 2016-3-19 13:56:28
 */
@Controller
@RequestMapping("/mq")
public class MqController {

	@Resource
	private IXxlMqMessageService xxlMqMessageService;
	
	@RequestMapping("")
	@PermessionLimit
	public String index(Model model){
		model.addAttribute("status", Message.Status.values());
		return "mq/index";
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	@PermessionLimit
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
		@RequestParam(required = false, defaultValue = "10") int length, String name){

		return xxlMqMessageService.pageList(start, length, name, null);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	@PermessionLimit
	public ReturnT<String> delete(int id){
		return xxlMqMessageService.delete(id);
	}

	@RequestMapping("/update")
	@ResponseBody
	@PermessionLimit
	public ReturnT<String> update(int id, String data, String delayTime, String status){
		return xxlMqMessageService.update(id, data, delayTime, status);
	}

}
