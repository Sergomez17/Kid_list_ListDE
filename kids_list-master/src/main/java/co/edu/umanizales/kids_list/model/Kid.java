package co.edu.umanizales.kids_list.model;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Kid {
    private String id;
    private String name;
    private byte age;
    private char gender;
}