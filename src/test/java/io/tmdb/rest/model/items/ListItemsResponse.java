package io.tmdb.rest.model.items;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.tmdb.rest.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListItemsResponse extends BaseModel {
    private int status_code;
    private String status_message;
    private boolean success;
    private ArrayList<ItemResults> results;
}
