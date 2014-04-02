package com.paloit.manager;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class LoginManager {
 
  @RequestMapping(value="/login", method = RequestMethod.GET)
  public String printUser(ModelMap model) {
 
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String name = auth.getName(); //get logged in username
 
      model.addAttribute("username", name);
      return "hello";
 
  }
}
