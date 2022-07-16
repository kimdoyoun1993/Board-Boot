package org.zerock.b2.entity;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(value = {AuditingEntityListener.class}) //엔티티에 대한 변경이 감지되면 동작함 ,더티체킹
public class BaseEntity {

    @CreatedDate//jpa의 어노테이션 , DB가 아닌 서버의 시간으로 맞춰줌
    @Column(name = "regdate",updatable = false) //생성할때만 들어가고 나중에 갱신을 못하게
    private LocalDateTime regDate;

    @LastModifiedDate//jpa의 어노테이션 , DB가 아닌 서버의 시간으로 맞춰줌
    private LocalDateTime modDate;
}