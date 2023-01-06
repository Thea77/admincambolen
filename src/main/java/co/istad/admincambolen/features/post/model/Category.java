package co.istad.admincambolen.features.post.model;

import co.istad.admincambolen.features.file.model.File;
import lombok.Data;

@Data
public class Category {
    private Integer id;
    private String name;
    private String description;
    private File icon;
    private Boolean isEnabled;
}
