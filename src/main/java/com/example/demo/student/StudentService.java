package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final studentRepository studentRepository;

    @Autowired
    public StudentService(com.example.demo.student.studentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentOption = studentRepository.findStudentByEmail(student.getEmail());

        if(studentOption.isPresent()){
            throw new IllegalStateException("emailTaken");
        }

        studentRepository.save(student);

    }

    public void deleteStudent(Long studentId) {
       boolean exist = studentRepository.existsById(studentId);
       if(!exist){
           throw new IllegalStateException(
                   "student with provided id: " + studentId + " does not exist "
           );
       }

       studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Optional<Student> student = studentRepository.findById(studentId);

        if(student.isEmpty()){
            throw new IllegalStateException("student with given id: " + studentId + " is not present");
        }

        if(name != null && name.length() > 0 && !Objects.equals(student.get().getName(), name)){
            student.get().setName(name);
        }

        if(name != null && email.length() > 0 && !Objects.equals(student.get().getEmail(), email)){
            student.get().setEmail(email);
        }


    }
}
