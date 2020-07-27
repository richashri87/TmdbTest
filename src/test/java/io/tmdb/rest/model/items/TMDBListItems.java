package io.tmdb.rest.model.items;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.tmdb.rest.model.BaseModel;
import io.tmdb.rest.model.items.TMDBListItem;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Data
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TMDBListItems extends BaseModel {

    private ArrayList<TMDBListItem> items;
}

