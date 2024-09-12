package br.edu.ifpe.CRMHealthLink.controller;

import br.edu.ifpe.CRMHealthLink.controller.request.PatientCreateDTO;
import br.edu.ifpe.CRMHealthLink.domain.useCase.IPatientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("crmhealthlink/api/patient")
@Tag(name = "Patient API", description = "API para gestão de Pacientes")
public class PatientController {

    private IPatientService patientService;

    public ResponseEntity<Void> createPatient(@RequestBody @Valid PatientCreateDTO patientDTO){
        patientService.save(patientDTO.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
