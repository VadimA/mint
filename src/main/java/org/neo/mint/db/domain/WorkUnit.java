package org.neo.mint.db.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by vanosov on 20.03.2018.
 */
@Getter
@Setter
@EqualsAndHashCode
public class WorkUnit {

    private String name;

    private double unitPrice;

    private double area;

    private double total;

    private double payment;

    private String status;

    private Person worker;
}
