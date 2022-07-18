package org.zerock.b2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "t_bimage")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    private String uuid;

    private String fileName;
    //이미지 여부
    private boolean img;

    //메인 이미지를 구분하기 위한 컬럼
    private int ord;

    public void fixOrd(int ord){
        this.ord = ord;
    }

}
