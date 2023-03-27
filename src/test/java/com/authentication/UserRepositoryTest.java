package com.authentication;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	private User createTestUser() {
		// Create private test for user first
		User user = new User();
		user.setEmail("russ@gmail.com");
		user.setPassword("russ123");
		user.setFirstName("Russell");
		user.setLastName("Ho");
		return user;
	}
	
    @Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository repo;
	
	//Test methods
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("elonmusk@gmail.com");
		user.setPassword("elonmusk");
		user.setFirstName("Elon");
		user.setLastName("Musk");
		
		User savedUser = repo.save(user);
		
		User existUser = entityManager.find(User.class, savedUser.getId());
		
		assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
		
	}
	
	@Test
	public void testFindByEmail() {
		// Create and save the test user
		User testUser = createTestUser();
		entityManager.persist(testUser);

		String email = "russ@gmail.com";
		User user = repo.findByEmail(email);
		
		assertThat(user.getEmail()).isEqualTo(email);
	}
}
