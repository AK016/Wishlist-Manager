package com.masai.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<WishlistItem> getUserWishlist(Authentication authentication) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return wishlistItemRepository.findByUserId(user.getId());
	}

	@PostMapping("/addItem")
	public WishlistItem addWishlistItem(@RequestBody WishlistItem wishlistItem) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		Optional<User> userOptional = userRepository.findByUsername(username);
		if (userOptional.isPresent()) {
			User user = userOptional.get();

			wishlistItem.setUser(user);
			return wishlistItemRepository.save(wishlistItem);
		} else {
			throw new RuntimeException("User not found.");
		}
	}

	@DeleteMapping("/{id}")
	public void deleteWishlistItem(@PathVariable Long id) {
		wishlistItemRepository.deleteById(id);
	}
}
