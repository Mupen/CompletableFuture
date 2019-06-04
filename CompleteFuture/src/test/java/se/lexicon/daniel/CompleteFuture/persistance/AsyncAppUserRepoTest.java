package se.lexicon.daniel.CompleteFuture.persistance;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import se.lexicon.daniel.CompleteFuture.model.AppUser;

@DataJpaTest
@RunWith(SpringRunner.class)
@Transactional
public class AsyncAppUserRepoTest {
	@Autowired AsyncAppUserRepo underTest;
	
	AppUser user;
	int id;
	
	@Before
	public void init() {
		user = new AppUser("Nils", "Andersson", "nisse@gmail.com", "070-1234567");
		user = underTest.save(user);
		id = user.getId();
	}
	
	@Test
	public void givenIdShouldReturnUser() throws InterruptedException, ExecutionException {
		
		CompletableFuture<AppUser> future = underTest.findById(id);
		AppUser result = future.get();
		assertEquals(user, result);
				
	}
}
