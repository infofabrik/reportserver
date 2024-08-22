package net.datenwerke.rs.json.service.json

import groovy.json.JsonOutput

class JsonServiceImpl implements JsonService {

   @Override
   public String map2Json(Map map) {
      return JsonOutput.toJson(map);
   }
}