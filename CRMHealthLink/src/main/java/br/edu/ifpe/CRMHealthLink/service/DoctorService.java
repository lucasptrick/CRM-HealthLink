package br.edu.ifpe.CRMHealthLink.service;

import br.edu.ifpe.CRMHealthLink.dto.doctorDto.DoctorCreateDto;
import br.edu.ifpe.CRMHealthLink.entity.Doctor;
import br.edu.ifpe.CRMHealthLink.exception.ResourceNotFoundException;
import br.edu.ifpe.CRMHealthLink.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;


    @Transactional
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Transactional(readOnly = true)
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Doctor findById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com id: " + id));
    }

    @Transactional
    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, DoctorCreateDto doctorCreateDto) {
        Doctor doctor = findById(id);

        doctor.setName(doctorCreateDto.getName());
        doctor.setBirthDate(doctorCreateDto.getBirthDate());
        doctor.setCpf(doctorCreateDto.getCpf());
        doctor.setEmail(doctorCreateDto.getEmail());
        doctor.setAcessLevel(doctorCreateDto.getAcessLevel());
        doctor.setLogin(doctorCreateDto.getLogin());
        doctor.setPassword(doctorCreateDto.getPassword());
        doctor.setCRM(doctorCreateDto.getCRM());
        doctor.setSpecialty(doctorCreateDto.getSpecialty());

        doctorRepository.save(doctor);
    }
}