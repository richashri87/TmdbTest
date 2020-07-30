package io.tmdb.rest.model.list;

import io.tmdb.rest.model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ListResponse extends BaseModel {
    private int status_code;
    private String status_message;
    private boolean success;
    private int id;
}
