package com.ftms.ftmsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue( value="employee" )
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee extends User {
}
