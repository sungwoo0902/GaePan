package co.kr.gaepan.entity.pet;

import co.kr.gaepan.dto.pet.PetFileDTO;
import jakarta.persistence.*;
import lombok.*;


@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "gp_file")
public class PetFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String oName;
    private String sName;

    public PetFileDTO toDTO() {
        return PetFileDTO.builder()
             .no(no)
             .oName(oName)
             .sName(sName)
             .build();
    }

}
