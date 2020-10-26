/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovex.Innovex.utilities.utility;

import lombok.Data;

/**
 *
 * @author
 */
@Data
public class PageDetails {

    private Integer page;
    private Integer perPage;
    private Integer totalPage;
    private Long totalElements;
    private String reportName;
}
