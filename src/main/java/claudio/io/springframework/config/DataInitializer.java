package claudio.io.springframework.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import claudio.io.springframework.entity.User;
import claudio.io.springframework.repository.UserRepository;

@Component
public class DataInitializer implements  ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		List<User> users = userRepository.findAll();
		
		if(users.isEmpty()) {
		
		createUser("Claudio", "claudio.lindemberg@hotmail.com");
		createUser("Lindemberg", "claudiolindemberg22@gmail.com");
		createUser("Emilia", "emiliadantas19@gmail.com");
		
		}
		
		User user = userRepository.findByEmailQualquerCoisa("claudio.lindemberg@hotmail.com");
		
		System.out.println(user.getName());
		
	}
	
	public void createUser(String name, String email){
		
		User user = new User(name, email);
		
		userRepository.save(user);
	}

}
