package org.example.isvalidapi.domains.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.isvalidapi.annotations.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PasswordValidReqDTO {

    @MinLength(9)
    @ContainsDigit
    @ContainsLowercase
    @ContainsUppercase
    @ContainsSpecialCharacter
    @NoRepeatedCharacters
    private String password;

}
