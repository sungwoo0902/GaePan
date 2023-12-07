package co.kr.gaepan.entity.pet;

import co.kr.gaepan.dto.pet.PetRegisterDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "gp_pet")
public class PetRegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String uid;
    private String title;
    private String thumb;
    private String content;
    private String name;
    private int cate;
    private int type;
    private String regip;
    @CreationTimestamp
    private LocalDateTime regDate;
    private int division;

    public PetRegisterDTO  toDTO() {
        return PetRegisterDTO.builder()
              .no(no)
              .uid(uid)
              .title(title)
              .thumb(thumb)
              .content(content)
              .name(name)
              .cate(cate)
              .type(type)
              .regip(regip)
              .regDate(regDate)
              .division(division)
              .build();
    }

}
