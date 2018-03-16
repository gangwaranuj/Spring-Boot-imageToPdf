package com.thinksys.dbservice.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thinksys.dbservice.model.UserInfo;

@RestController
@RequestMapping(value = "/rest/user")
public class MainController {




	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public List<String> showWelcomePage(ModelMap model) {

		ArrayList<UserInfo> arr= new ArrayList<UserInfo>();

		UserInfo user = new UserInfo();
		user.setUsername("anuj");
		user.setPassword("anuj");
		arr.add(user);

		UserInfo user1 = new UserInfo();
		user1.setUsername("anuj1");
		user1.setPassword("anuj1");
		arr.add(user1);
		
		
		System.out.println(arr.stream().map(user4 ->{
			System.out.println(user4.getUsername());
			return user4.getUsername();
			
		}).collect(Collectors.toList()));
		
		return arr.stream()
		       .map(UserInfo::getUsername)
		       .collect(Collectors.toList());
	}

}
