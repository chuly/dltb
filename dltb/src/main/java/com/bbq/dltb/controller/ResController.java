package com.bbq.dltb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bbq.dltb.service.ResService;

@Controller
@RequestMapping(value = "download")
public class ResController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(ResController.class);

    @Autowired
    private ResService resService;


    @RequestMapping(value = "/{resCode}")
    public ModelAndView toRes(Model model,@PathVariable("resCode") String resCode) {
    	log.info("下载页面---resCode:"+resCode);
        ModelAndView mv = new ModelAndView("resdownload.ftl");
        model.addAttribute("resCode", resCode);
        String resName = resService.getResName(resCode);
        model.addAttribute("resName", resName);
        return mv;
    }
    
    @ResponseBody
    @RequestMapping(value = "/do/{resCode}")
    public String downloadRes(Model model,@PathVariable("resCode") String resCode) {
    	log.info("下载resCode:"+resCode);
        return resService.downloadRes( resCode);
    }

}
