package io.tmdb.rest.model.items;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.tmdb.rest.model.BaseModel;
import io.tmdb.rest.model.list.MediaType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TMDBListItem extends BaseModel {

    private String media_id;

    private MediaType media_type;

    private String comment;
}

