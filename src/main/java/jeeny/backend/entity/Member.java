package jeeny.backend.entity;


import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
@Table(name = "members")//h2 items와 연결
public class Member {
    @Id//id 프라이머리키
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동 증가값
    private int id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;
}
