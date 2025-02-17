package br.edu.ifpe.CRMHealthLink.repository;

import br.edu.ifpe.CRMHealthLink.domain.entity.Doctor;
import br.edu.ifpe.CRMHealthLink.domain.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
    //Doctor findByNameAndCpf(String name,String cpf);
    List<Doctor> findAllBySpeciality(Speciality speciality);

    Optional<Doctor> findByCRM(String crm);

    Optional<Doctor> findByEmail(String email);
}
