package se.lexicon.daniel.CompleteFuture;

import org.springframework.beans.factory.annotation.Autowired;
import se.lexicon.daniel.CompleteFuture.persistance.AsyncAppUserRepo;
import se.lexicon.daniel.CompleteFuture.model.AppUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {
	private AsyncAppUserRepo repo;

	@Autowired
	public BootStrap(AsyncAppUserRepo repo) {
		this.repo = repo;
	}

	@Override
	public void run(String... args) throws Exception {
		AppUser user = new AppUser("Nils", "Andersson", "nisse@gmail.com", "070-1234567");
		repo.save(user);
	}
}
