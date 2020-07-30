package io.tmdb.rest.model.items;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.tmdb.rest.model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListItemsResponse extends BaseModel {
    private int status_code;
    private String status_message;
    private boolean success;
    private ArrayList<ItemResults> results;
}
