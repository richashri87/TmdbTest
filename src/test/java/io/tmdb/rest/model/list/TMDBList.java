package io.tmdb.rest.model.list;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.tmdb.rest.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Builder
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TMDBList extends BaseModel {

    private String iso_639_1;
    private String name;
    private int comments;
    private String description;

    @Override
    public String toString() {
        return name;
    }

    public TMDBList(String description) {
        this.description = description;
    }
}
