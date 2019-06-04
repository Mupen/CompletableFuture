package se.lexicon.daniel.CompleteFuture.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import se.lexicon.daniel.CompleteFuture.model.AppUser;

public interface AppUserService {

	CompletableFuture<Optional<AppUser>> findById(int id);

}
