package se.lexicon.daniel.CompleteFuture.persistance;

import java.util.concurrent.CompletableFuture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import se.lexicon.daniel.CompleteFuture.model.AppUser;
import static se.lexicon.daniel.CompleteFuture.config.AsyncConfig.PERSISTANCE_POOL;

public interface AsyncAppUserRepo extends CrudRepository<AppUser, Integer> {

	@Async(value = PERSISTANCE_POOL)
	CompletableFuture<AppUser> findById(int id);

}
