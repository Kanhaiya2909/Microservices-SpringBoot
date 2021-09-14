package com.ratingdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratingdataservice.model.Rating;
import com.ratingdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingdata")
public class RatingResources {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 67);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId){
		List<Rating> rating  = Arrays.asList(
				new Rating("Titanic",9),
				new Rating("Gravity", 8)
				);
		UserRating userRating = new UserRating();
		userRating.setUserRating(rating);
		return userRating;
	}
}
