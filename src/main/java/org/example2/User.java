package org.example2;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter @Setter
@ToString(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)

public class User extends Base {
    private String email;


}