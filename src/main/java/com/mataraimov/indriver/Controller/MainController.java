package com.mataraimov.indriver.Controller;
import com.mataraimov.indriver.Entity.Order;
import com.mataraimov.indriver.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@Controller
@EnableWebMvc
public class MainController {

    @Autowired
    OrderRepository or;

    @GetMapping("/main")
    public ModelAndView main(){
        ModelAndView mav = new ModelAndView("list-targets");
        List<Order> orders = or.findAll();
        mav.addObject("orders", orders);
        return mav;
    }

    @GetMapping("/")
    public String zero(){
        return "redirect:main";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long orderId){
        or.deleteById(orderId);
        return "redirect:main";
    }

    @GetMapping("/add")
    public ModelAndView yes(){
        ModelAndView mav = new ModelAndView("form");
        Order order = new Order();
        mav.addObject("new_order", order);
        return mav;
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Order order){
        or.save(order);
        return "redirect:main";
    }
}
