package umc.spring.domain.common;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public abstract class Address {
    private String city;
    private String street;
    private String zipcode;
}
