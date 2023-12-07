package co.kr.gaepan.dto.pet;

import co.kr.gaepan.entity.pet.PetFileEntity;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PetFileDTO {

    private int no;
    private String oName;
    private String sName;

    public PetFileEntity toEntity() {
        return PetFileEntity.builder()
              .no(no)
              .oName(oName)
              .sName(sName)
              .build();
    }

}
