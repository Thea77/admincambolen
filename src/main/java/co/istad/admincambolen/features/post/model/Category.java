package co.istad.admincambolen.features.post.model;

import lombok.Data;

@Data
public class Category {
    private Integer id;
    private String name;
    private String description;
    private File icon;
    private Boolean isEnabled;
}
