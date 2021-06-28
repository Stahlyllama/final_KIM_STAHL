package org.launchcode.final_KIM_STAHL;


import org.junit.jupiter.api.Test;
import org.launchcode.final_KIM_STAHL.model.User;
import org.launchcode.final_KIM_STAHL.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.reactive.server.JsonPathAssertions;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

//    private Object assertThat(String email) {
//    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("kim.stahl@gmail.com");
        user.setPassword("kimstahl");
        user.setFirstName("Kim");
        user.setLastName("Stahl");

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

//        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
    }

}

