package org.saiku.query;

import org.olap4j.impl.Named;
import org.olap4j.metadata.Member;

/**
 * Created by bugg on 07/07/15.
 */
public class RootMember implements Named {
  private final Member member;
  private final QueryHierarchy hierarchy;

  public RootMember(QueryHierarchy queryHierarchy, Member member) {
    super();
    this.member = member;
    this.hierarchy = queryHierarchy;

  }

  @Override
  public String getName() {
    return this.member.getName();
  }

  public String getUniqueName(){
    return this.member.getUniqueName();
  }

  public String getCaption(){
    return this.member.getCaption();
  }

  public String getDescription(){
    return this.member.getDescription();
  }

}
