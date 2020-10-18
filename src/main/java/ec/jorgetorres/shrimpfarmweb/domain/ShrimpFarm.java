package ec.jorgetorres.shrimpfarmweb.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class ShrimpFarm {
  @Id
  @GeneratedValue
  @Column(name="idshrimpfarm")
  private Long id;
  private String name;

  @SuppressWarnings("unused")
  public ShrimpFarm(){}

  public ShrimpFarm(String mame){
    this.name = mame;
  }

  @Override
  public String toString() {
    return "ShrimpFarm [name=" + name + "]";
  }
}
