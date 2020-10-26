/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.utilities.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 *
 * @author
 */
@Data
public class Pager<T> {

    private String code;
    private String message;
    private T content;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PageDetails pageDetails;

}
