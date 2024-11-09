package org.example.microgestormonopatin.Service;

import org.example.microgestormonopatin.Repository.MonopatinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.microgestormonopatin.Dto.MonopatinDto;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class MonopatinService {

    @Autowired
    private MonopatinRepository MonopatinRepository;

    public List<MonopatinDto> getAll(){
        return MonopatinRepository.findAll().stream().map(MonopatinDto::new).collect(Collectors.toList());
    }


