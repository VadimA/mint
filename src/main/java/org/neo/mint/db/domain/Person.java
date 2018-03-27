package org.neo.mint.db.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by vanosov on 16.03.2018.
 */
@Getter
@Setter
@EqualsAndHashCode
public class Person {

    private String name;
    private int age;
    private String city;

}
