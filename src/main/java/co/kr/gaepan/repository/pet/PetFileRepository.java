package co.kr.gaepan.repository.pet;

import co.kr.gaepan.entity.pet.PetFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetFileRepository extends JpaRepository<PetFileEntity, Integer> {

}
