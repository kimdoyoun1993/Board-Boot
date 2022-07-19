package org.zerock.b2.entity;

import lombok.*;

import javax.persistence.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class BoardImage {


    private String fileLink;

    //메인 이미지를 구분하기 위한 컬럼
    private int ord;

    public void fixOrd(int ord){
        this.ord = ord;
    }

}
