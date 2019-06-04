package se.lexicon.daniel.CompleteFuture.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import se.lexicon.daniel.CompleteFuture.model.AppUser;
import se.lexicon.daniel.CompleteFuture.persistance.AsyncAppUserRepo;
import static se.lexicon.daniel.CompleteFuture.config.AsyncConfig.SERVICE_POOL;

@Service
public class AppUserServiceImpl implements AppUserService {

	private AsyncAppUserRepo appUserRepo;

	@Autowired
	public AppUserServiceImpl(AsyncAppUserRepo appUserRepo) {
		this.appUserRepo = appUserRepo;
	}

	@Override
	@Async(value = SERVICE_POOL)
	@Transactional
	public CompletableFuture<Optional<AppUser>> findById(int id) {
		CompletableFuture<AppUser> fromRepo = appUserRepo.findById(id);
		return fromRepo.thenApplyAsync(Optional::ofNullable);
	}
}
