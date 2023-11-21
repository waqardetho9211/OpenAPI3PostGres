package com.exampleHotel.demo.util;

import com.exampleHotel.demo.web.error.BadRequestException;
import io.micrometer.common.util.StringUtils;

import java.util.UUID;

public class ControllerUtils {
  public static UUID translateStringToUUID(String id){
    if(StringUtils.isBlank(id)){
      throw new BadRequestException("id cannot be null or empty");
    }
    try{
      return UUID.fromString(id);
    }catch(IllegalArgumentException iae){
      throw new BadRequestException("cannot convert string to uuid");
    }
  }
}
