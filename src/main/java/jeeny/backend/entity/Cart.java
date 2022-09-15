package jeeny.backend.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "carts")//h2 items와 연결
public class Cart {
    @Id//id 프라이머리키
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동 증가값
    private int id;

    @Column
    private int memberId;

    @Column
    private int itemId;
}
