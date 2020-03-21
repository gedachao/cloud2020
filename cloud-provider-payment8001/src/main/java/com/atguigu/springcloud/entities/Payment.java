package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author gedachao
 * @description
 * @date 2020/3/21 14:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private static final long serialVersionUID = -3152084572264175328L;
    private Long id;
    private String serial;

}
