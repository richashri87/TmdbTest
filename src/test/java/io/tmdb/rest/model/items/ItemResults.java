package io.tmdb.rest.model.items;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.tmdb.rest.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemResults extends BaseModel {

    private int sort_order;
    private String media_id;
    private boolean success;
    private MediaType media_type;

}
