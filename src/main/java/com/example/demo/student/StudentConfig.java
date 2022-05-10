package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {


    @Bean
    CommandLineRunner commandLineRunner(studentRepository studentRepository){
        return args -> {
            Student rahul = new Student(
                    "Rahul",
                    "r@gmail.com",
                    LocalDate.of(2000, Month.FEBRUARY, 24)
            );
            Student siva = new Student(
                    "Siva",
                    "s@gmail.com",
                    LocalDate.of(2006, Month.MARCH, 5)
            );

            studentRepository.saveAll(
                    List.of(rahul, siva)
            );
        };
    }
}
