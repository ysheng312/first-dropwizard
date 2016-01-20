package com.yaxuansheng.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 * Created by shengy on 1/19/16.
 */
public class SayingRepresentation {
    private long id;

    @Length(max = 3)
    private String content;

    public SayingRepresentation() {
        // Jackson deserialization
    }

    public SayingRepresentation(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
