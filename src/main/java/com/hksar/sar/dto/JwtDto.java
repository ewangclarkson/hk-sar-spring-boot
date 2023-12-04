package com.hksar.sar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtDto {
   String accessToken;
   UserDto userDetails;
   String expiresIn;

}
