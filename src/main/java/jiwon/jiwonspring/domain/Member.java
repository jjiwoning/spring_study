package jiwon.jiwonspring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {
    // 요구사항 : 회원 이름, ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 시스템에서 정해주는 id

    private String name; // 고객이 적어주는거

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
