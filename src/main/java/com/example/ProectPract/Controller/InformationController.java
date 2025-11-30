package com.example.ProectPract.Controller;

import com.example.ProectPract.DTO.InformationDto;
import com.example.ProectPract.DTO.InformationMapper;
import com.example.ProectPract.DTO.InformationResponseDto;
import com.example.ProectPract.Entity.Information;
import com.example.ProectPract.Service.InformationService;
import com.example.ProectPract.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/information")
@RequiredArgsConstructor
public class InformationController {
    private final InformationService informationService;
    private final InformationMapper informationMapper;

    @PostMapping("/create")
    public ResponseEntity<String> createInformation(@RequestBody InformationDto information){
        informationService.createInformation(information);
        return ResponseEntity.ok("Information created");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<InformationResponseDto> getInformationById(@PathVariable Long id){
        Information information = informationService.getInformationById(id);
        InformationResponseDto dto = informationMapper.toDto(information);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<InformationResponseDto>> getAllInformation(){
        List<InformationResponseDto>  dto = informationService.getAllInformation();
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInformation(@PathVariable Long id){
        informationService.deleteInformationById(id);
        return ResponseEntity.ok("Information deleted");
    }

    @PatchMapping("/change/{id}")
    public ResponseEntity<String> updateInformation(@PathVariable Long id ,@RequestBody InformationDto information){
        informationService.changeInformation(id, information);
        return ResponseEntity.ok("Information updated");
    }


}
