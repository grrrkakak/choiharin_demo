package graduation.choiharin;

import com.fasterxml.jackson.datatype.hibernate5.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.config.*;

import java.util.*;

/*
* import com.fasterxml.jackson.datatype.hibernate5.*;
* */

@EnableJpaAuditing //스프링 부트 설정 클래스에 적용해야한다. (DATA JPA)
@SpringBootApplication
public class ChoiharinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChoiharinApplication.class, args);
	}

//	@Bean
//	public AuditorAware<String> auditorProvider() {
//		//시큐리티를 사용하면 여기서 세션 아이디 등을 꺼내야함
//		return () -> Optional.of(UUID.randomUUID().toString());
//	}

//	@Bean
//	Hibernate5Module hibernate5Module() {
//		Hibernate5Module hibernate5Module = new Hibernate5Module();
//		//강제 지연 로딩 설정
////		hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
//		return hibernate5Module;
//	}
}
