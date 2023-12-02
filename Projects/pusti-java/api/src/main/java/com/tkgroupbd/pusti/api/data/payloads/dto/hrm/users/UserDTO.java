package com.tkgroupbd.pusti.api.data.payloads.dto.hrm.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  private String userName;
  String password;
}
