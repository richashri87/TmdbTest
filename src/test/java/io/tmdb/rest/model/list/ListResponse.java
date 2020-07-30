package io.tmdb.rest.model.list;

import io.tmdb.rest.model.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ListResponse extends BaseModel {
    private int status_code;
    private String status_message;
    private boolean success;
    private int id;
}
