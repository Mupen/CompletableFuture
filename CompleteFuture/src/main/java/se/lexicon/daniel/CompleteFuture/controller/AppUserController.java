package se.lexicon.daniel.CompleteFuture.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import se.lexicon.daniel.CompleteFuture.model.AppUser;
import se.lexicon.daniel.CompleteFuture.service.AppUserService;
import static se.lexicon.daniel.CompleteFuture.config.AsyncConfig.CONTROLLER_POOL;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
public class AppUserController {
	
	private AppUserService service;

	@Autowired
	public AppUserController(AppUserService service) {
		this.service = service;
	}
	
	@Async(value = CONTROLLER_POOL)
	@GetMapping("/api/user/{id}")
	public CompletableFuture<ResponseEntity<AppUser>> getById(@PathVariable("id")int id){
		
		CompletableFuture<Optional<AppUser>> future = service.findById(id);
		
		return future
				.thenApplyAsync(optional -> optional.orElseThrow(RuntimeException::new))
				.handleAsync((user, exception) -> {
					if(exception != null) {
						return ResponseEntity.notFound().build();
					}else {
						return ResponseEntity.ok(user);
					}
				});
						
	}
}
