package io.tmdb.rest.model.movielist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.tmdb.rest.model.BaseModel;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieListRequest extends BaseModel {
	private String name;
	private String iso_639_1;

}
