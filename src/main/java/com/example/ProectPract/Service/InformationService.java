package com.example.ProectPract.Service;

import com.example.ProectPract.DTO.InformationDto;
import com.example.ProectPract.DTO.InformationMapper;
import com.example.ProectPract.DTO.InformationResponseDto;
import com.example.ProectPract.Entity.Information;
import com.example.ProectPract.Entity.User;
import com.example.ProectPract.Repository.InformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InformationService {
    private final InformationRepository informationRepository;
    private final InformationMapper informationMapper;

    @Transactional
    public void createInformation(InformationDto information){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User curUser = (User) auth.getPrincipal();

        Information entityInformation = informationMapper.toEntity(information, curUser);
        informationRepository.save(entityInformation);
    }

    public List<InformationResponseDto> getAllInformation(){
        List<Information> informations = informationRepository.findAll();
        return informations.stream().map(informationMapper::toDto).collect(Collectors.toList());
    }

    public Information getInformationById(Long id){
       return informationRepository.findById(id).orElseThrow(()-> new RuntimeException("Information not found"));
    }

    public void deleteInformationById(Long id){
        Information inf = informationRepository.findById(id).orElseThrow(()-> new RuntimeException("Information not found"));
        User user = inf.getUser();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User curUser = (User) auth.getPrincipal();

        if(!user.equals(curUser)){
            throw new RuntimeException("Only owner can delete this post");
        }

        informationRepository.deleteById(id);
    }

}
