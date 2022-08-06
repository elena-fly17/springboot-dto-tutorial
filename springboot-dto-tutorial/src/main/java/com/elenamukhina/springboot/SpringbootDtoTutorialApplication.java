package com.elenamukhina.springboot;

import com.elenamukhina.springboot.model.Location;
import com.elenamukhina.springboot.model.User;
import com.elenamukhina.springboot.repository.LocationRepository;
import com.elenamukhina.springboot.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootDtoTutorialApplication implements CommandLineRunner{

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDtoTutorialApplication.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LocationRepository locationRepository;

	// сохранили местоположение в БД, и двух юзеров
	// метод интерфейса CommandLineRunner - благодаря использованию этого интерфейса сначала
	// выполнится метод run (т.е. по сути до запуска приложения), а потом отработает метод main
	// в метод run поместили логику инициализации
	// вот что об этом на стековерфлоу:
	// вы можете разделить инициализацию и настройку, разделив код между поведением инициализации и ядра
	// еще из стековерфлоу про CommandLineRunner для инициализации до запуска приложения:
	// Я использую это для заполнения моих данных по умолчанию. Обычно создаю
	// ApplicationInitializerкласс, расширяющий CommandLineRunner. У меня есть методы
	// createDefaultUser() и createDefaultSytemData() и т.д. Таким образом, я не полагаюсь
	// на sql-файлы для заполнения БД - МОЕ: т.e. он вместо использования sql-файлов для заполнения БД
	// использует указанные им методы и с помощью CommandLineRunner запускает их выполнение до того, как
	// сработает/отработает/запустится приложение
	@Override
	public void run(String... args) throws Exception {
		Location location = new Location();
		location.setPlace("Pune");
		location.setDescription("Pune is great place to live");
		location.setLongitude(40.5);
		location.setLatitude(30.6);
		locationRepository.save(location);

		User user1 = new User();
		user1.setFirstName("Ramesh");
		user1.setLastName("Fadatare");
		user1.setEmail("ramesh@gmail.com");
		user1.setPassword("secret");
		user1.setLocation(location);
		userRepository.save(user1);

		User user2 = new User();
		user2.setFirstName("John");
		user2.setLastName("Cena");
		user2.setEmail("john@gmail.com");
		user2.setPassword("secret");
		user2.setLocation(location);
		userRepository.save(user2);
	}
}
