package org.sid.outCome.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {
        @Id
        private Integer id;
        private String name;
        private String description;
        private Integer userId;


}
