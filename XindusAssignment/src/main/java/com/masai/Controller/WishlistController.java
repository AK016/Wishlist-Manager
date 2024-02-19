package com.masai.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Entity.User;
import com.masai.Entity.WishlistItem;
import com.masai.Repository.UserRepository;
import com.masai.Repository.WishlistItemRepository;

@RestController
@RequestMapping("/wishlists")
public class WishlistController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WishlistItemRepository wishlistItemRepository;

	@GetMapping("/getUserWishlist")
	public ResponseEntity<List<WishlistItem>> getUserWishlist() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName(); 

		Optional<User> userOptional = userRepository.findByUsername(username);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			List<WishlistItem> wishlistItems = wishlistItemRepository.findByUserId(user.getId());
			return ResponseEntity.ok(wishlistItems);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping("/addItem")
	public ResponseEntity<?> addWishlistItem(@RequestBody WishlistItem wishlistItem) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		Optional<User> userOptional = userRepository.findByUsername(username);
		if (userOptional.isPresent()) {
			User user = userOptional.get();

			wishlistItem.setUser(user);
			WishlistItem savedItem = wishlistItemRepository.save(wishlistItem);
			return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteWishlistItem(@PathVariable Long id) {
		Optional<WishlistItem> wishlistItemOptional = wishlistItemRepository.findById(id);
		if (wishlistItemOptional.isPresent()) {
			wishlistItemRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wishlist item not found.");
		}
	}
}
