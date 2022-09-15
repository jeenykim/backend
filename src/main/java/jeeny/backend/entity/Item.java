package jeeny.backend.entity;


import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
@Table(name = "items")//h2 items와 연결
public class Item {
    @Id//id 프라이머리키
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동 증가값
    private int id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100)
    private String imgPath;

    @Column
    private int price;
}
