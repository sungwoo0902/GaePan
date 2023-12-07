package co.kr.gaepan.repository.pet;

import co.kr.gaepan.entity.pet.PetRegisterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetBoardRepository extends JpaRepository<PetRegisterEntity, Integer> {

    Page<PetRegisterEntity> findAllByDivision(Pageable pageable, int division);
    Page<PetRegisterEntity> findAllByCateAndDivision(Pageable pageable, int cate, int division);


}
