package br.edu.ifpe.CRMHealthLink.controller;

import br.edu.ifpe.CRMHealthLink.controller.request.AppointmentCreateDTO;
import br.edu.ifpe.CRMHealthLink.controller.request.AvailabilityDTO;
import br.edu.ifpe.CRMHealthLink.controller.request.DoctorCreateDTO;
import br.edu.ifpe.CRMHealthLink.controller.response.DoctorResponseDTO;
import br.edu.ifpe.CRMHealthLink.domain.entity.*;
import br.edu.ifpe.CRMHealthLink.domain.repository.DoctorAvailabilityRepository;
import br.edu.ifpe.CRMHealthLink.domain.useCase.IDoctorService;
import br.edu.ifpe.CRMHealthLink.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("doctor")
@Tag(name = "Doctor API", description = "API para gestão de médicos")
public class DoctorController {

    private IDoctorService doctorService;
    private UserService userService;

    public DoctorController(IDoctorService doctorService,
                            UserService userService) {
        this.doctorService = doctorService;
        this.userService = userService;
    }

    @Operation(summary = "cria médico")
    @PostMapping
    public ResponseEntity<Void> createDoctor(@RequestBody @Valid DoctorCreateDTO doctorDTO){

        doctorService.save(doctorDTO.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "marca disponibilidade do médico")

    @PostMapping("/available")
    public ResponseEntity<Void> addAvailability(@RequestBody @Valid AvailabilityDTO availabilityDTO){
        var doctor = getDoctorByEmail(availabilityDTO.doctorEmail());

        if(doctor == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        doctorService.addAvailability(availabilityDTO.toEntity(doctor));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> getAllDoctors(boolean withAvaiabilities){
        return ResponseEntity.ok(
                doctorService.getAll().stream()
                        .map(doctor -> new DoctorResponseDTO(doctor,withAvaiabilities))
                        .toList());
    }


    private Doctor getDoctorByEmail(String email){
        return (Doctor) userService.getUserByEmail(email);
    }
}
