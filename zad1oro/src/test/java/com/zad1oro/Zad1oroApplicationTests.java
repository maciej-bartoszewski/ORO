package com.zad1oro;

import com.zad1oro.entity.Category;
import com.zad1oro.entity.Order;
import com.zad1oro.entity.Part;
import com.zad1oro.entity.User;
import com.zad1oro.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
@SpringBootTest
class Zad1oroApplicationTests {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	PartRepository partRepository;

	static Category category1, category2;
	static Part part1, part2, part3, part4, part5;
	static User user1, user2;
	static Order order1, order2, order3, order4, order5, order6;

	@BeforeAll
	static void setUp(@Autowired CategoryRepository categoryRepository,
					  @Autowired PartRepository partRepository,
					  @Autowired UserRepository userRepository,
					  @Autowired OrderRepository orderRepository) {

		category1 = categoryRepository.save(new Category().withName("Opony"));
		category2 = categoryRepository.save(new Category().withName("Felgi"));

		part1 = partRepository.save(new Part().withName("Opona zimowa").withPrice(200).withCategory(category1));
		part2 = partRepository.save(new Part().withName("Opona letnia").withPrice(150).withCategory(category1));
		part3 = partRepository.save(new Part().withName("Felga aluminiowa").withPrice(300).withCategory(category2));
		part4 = partRepository.save(new Part().withName("Felga stalowa").withPrice(150).withCategory(category2));
		part5 = partRepository.save(new Part().withName("Felga żeliwna").withPrice(200).withCategory(category2));

		user1 = userRepository.save(new User().withName("Jan Kowalski").withEmail("jankowalski@gmail.com"));
		user2 = userRepository.save(new User().withName("Piotr Nowak").withEmail("piotnowak@gmail.com"));

		order1 = orderRepository.save(new Order().withUser(user1).withPart(part1));
		order2 = orderRepository.save(new Order().withUser(user1).withPart(part2));
		order3 = orderRepository.save(new Order().withUser(user1).withPart(part3));
		order4 = orderRepository.save(new Order().withUser(user2).withPart(part3));
		order5 = orderRepository.save(new Order().withUser(user2).withPart(part4));
		order6 = orderRepository.save(new Order().withUser(user2).withPart(part5));
	}

	@Test
	void testOrderCount() {
		long orderCount = orderRepository.count();
		assertThat(orderCount).isEqualTo(6);
		log.info("Liczba wszystkich zamówień: {}", orderCount);
	}

	@Test
	void testOrderCountForSpecificPart() {
		long orderCountPart3 = orderRepository.countOrdersByPart(part3);
		assertThat(orderCountPart3).isEqualTo(2);
		log.info("Liczba zamówień dla produktu '{}': {}", part3.getName(), orderCountPart3);
	}

	@Test
	void testOrderCountForSpecificUser() {
		long orderCountUser1 = orderRepository.countOrdersByUser(user1);
		assertThat(orderCountUser1).isEqualTo(3);
		log.info("Liczba zamówień dla użytkownika '{}': {}", user1.getName(), orderCountUser1);
	}

	@Test
	void testOrderCountForUserByEmail() {
		long orderCountUserByEmail = orderRepository.countOrdersByUser_Email(user1.getEmail());
		assertThat(orderCountUserByEmail).isEqualTo(3);
		log.info("Liczba zamówień dla użytkownika według maila '{}': {}", user1.getEmail(), orderCountUserByEmail);
	}

	@Test
	void testPartsCountInPriceRange() {
		int minPrice = 150;
		int maxPrice = 200;
		List<Part> parts = partRepository.findPartsByPriceBetween(minPrice, maxPrice);
		assertThat(parts.size()).isEqualTo(4);
		log.info("Liczba części w przedziale cenowym <{},{}>: {}", minPrice, maxPrice, parts.size());
		parts.forEach(part -> log.info("{} {} {}", part.getId(), part.getName(), part.getPrice()));
	}
}
