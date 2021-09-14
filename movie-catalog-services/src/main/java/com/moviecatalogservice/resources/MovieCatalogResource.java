package com.moviecatalogservice.resources;

//import java.lang.reflect.ParameterizedType;
//import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import com.moviecatalogservice.model.CatalogItem;
import com.moviecatalogservice.model.Movie;
//import com.moviecatalogservice.model.Rating;
import com.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		
		
//		List<Rating> rating  = Arrays.asList(
//				new Rating("Titanic",9),
//				new Rating("Gravity", 8)
//				);
		
		UserRating rating = restTemplate.getForObject("http://localhost:8082/ratingdata/users/"+ userId, UserRating.class);
		
//		return rating.stream().map(ratings ->{
			return rating.getUserRating().stream().map(ratings ->{	
			Movie movie = restTemplate.getForObject("http://localhost:9898/movies/" + ratings.getMovieId(), Movie.class);
			
//			Movie movie = webClientBuilder.build()
//			.get()
//			.uri("http://localhost:8081/movies/moviId")
//			.retrieve()
//			.bodyToMono(Movie.class)
//			.block();
//			
				return new CatalogItem(movie.getName(),"Desc", ratings.getRating());
		
			})
			.collect(Collectors.toList());
		
		
		
		
		
		
		
		
	}
		
		
	

}
