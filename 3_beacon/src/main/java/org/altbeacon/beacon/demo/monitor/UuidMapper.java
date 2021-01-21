package org.altbeacon.beacon.demo.monitor;

import java.util.ArrayList;
import java.util.List;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.Region;

public class UuidMapper {

  public static Region constructRegion(String uuid) {
    String[] parts = uuid.split(":");
    List<Identifier> identifierList = new ArrayList<>();
    identifierList.add(Identifier.parse(parts[0]));
    identifierList.add(Identifier.parse(parts[1]));
    identifierList.add(Identifier.parse(parts[2]));
    return new Region(uuid, identifierList);
  }
}
