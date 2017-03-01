package com.bbq.dltb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bbq.dltb.common.htmlparse.bean.Magnet;
import com.bbq.dltb.common.htmlparse.bean.MagnetDetail;
import com.bbq.dltb.common.htmlparse.bean.MagnetUl;
import com.bbq.dltb.service.MagnetService;

@Controller
@RequestMapping(value = "mag")
public class MagnetController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(MagnetController.class);

    @Autowired
    private MagnetService magnetService;


    @RequestMapping(value = "/search")
    public ModelAndView search(Model model, String keyWord, Integer type, Integer page) {
    	log.info("搜索---keyWord:"+keyWord+",type="+type + ",page=" + page);
        ModelAndView mv = new ModelAndView("magnetList.ftl");
        model.addAttribute("keyWord", keyWord);
        if(type == null || keyWord == null || "".equals(keyWord.trim())){
        	return mv;
        }
        if(page == null || page<=0){
        	page = 1;
        }
        MagnetUl mu = magnetService.search(keyWord, type, page);
        List<Magnet> magList = mu.getMagnetList();
        model.addAttribute("magList", magList);
        model.addAttribute("totlePage", mu.getTotlePage());
        model.addAttribute("page", page);
        return mv;
    }
    
    @RequestMapping(value = "/detail")
    public ModelAndView detail(Model model,String detailCode, int type) {
    	log.info("明细--detailCode:"+detailCode+",type="+type);
    	ModelAndView mv = new ModelAndView("magnetDetail.ftl");
        model.addAttribute("detailCode", detailCode);
        model.addAttribute("type", type);
        MagnetDetail md = magnetService.detail(detailCode, type);
        model.addAttribute("md", md);
        return mv;
    }

}
