package com.nextstep.ecomerce.repo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ApiResponse<U> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("message")
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("statusCode")
    private int statusCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("object")
    private U users; // object

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("list")
    private List<U> usersList; //list

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("token")
    private String token;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("userId")
    private String userId;
}
