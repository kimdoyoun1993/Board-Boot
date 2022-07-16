package org.zerock.b2.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_board")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto_Increment 기능
    private Integer bno;


    //컬럼의 사이즈 지정해주고 not null 지정
    @Column(length = 200, nullable = false)
    private String title;

    private String content;

    private String writer;



    public void changeTitle(String title){
        this.title = title;
    }
    public void changeContent(String content){
        this.content = content;
    }

}