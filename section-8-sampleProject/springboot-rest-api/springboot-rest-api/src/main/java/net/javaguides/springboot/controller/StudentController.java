package net.javaguides.springboot.controller;

import net.javaguides.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
@RequestMapping("students") - Define this at class, and remove "students" from all request,
as marking this at class level makes API's inside this class inherit this
*/
@RestController
@RequestMapping("students")
public class StudentController {

    //http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student studentDetails = new Student(
                1,
                "Ayush",
                "Shukla"
        );
        return ResponseEntity.ok()
                .header("custom-header","ayush")
                .body(studentDetails);
        /*
        Either return like this passing specific status code or use pre-defined methods
        return new ResponseEntity<>(studentDetails,HttpStatus.OK);

         */
    }
    /*
    //WIthout ResponseEntity or Direct returning class; cannot return object or multiple values, such as object and status code
    public Student getStudent() {
        Student studentDetails = new Student(
                1,
                "Ayush",
                "Shukla"
        );
        return studentDetails;
    }
     */

    //http://localhost:8080/students
    @GetMapping
    public List<Student> getStudents() {
        List<Student> allStudents = new ArrayList<>();
        allStudents.add(new Student(2,"Ramesh","Tendulkar"));
        allStudents.add(new Student(3,"Rajesh","Tendulkar"));
        allStudents.add(new Student(4,"Ayush","Tendulkar"));
        allStudents.add(new Student(5,"Rakesh","Tendulkar"));

        return allStudents;
    }

    //Spring Boot API with Path variable
    //{id} - URI Path variable
    //http://localhost:8080/students/3
    @GetMapping("{id}")
    public Student getStudentById(@PathVariable("id") Integer studentId) {
        /*As the name of path variable is different "id" than the argument "studentId"
            so we can path that by passing the path variable in argument, as shown above
            ,or it will give exception.
        */
        Student studentData = new Student( studentId, "Ayush", "Shukla");
        return studentData;
    }

    //Spring Boot API with multiple Path variable
    //{id} - URI Path variable
    //http://localhost:8080/students/3/ayush/Shukla
    @GetMapping("{id}/{first-name}/{last-name}")
    public Student getStudentByDetails(@PathVariable("id") Integer studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        //We can pass multiple details in path variable as shown aboue

        Student studentData = new Student( studentId, firstName, lastName);
        return studentData;
    }

    //Spring boot REST API for Request Params
    //http://localhost:8080/students/query?id=3
    @GetMapping("query")
    public Student studentRequestVariable(@RequestParam Integer id) {
        Student student = new Student(id, "ayush", "shukla");
        return student;
    }

    //Spring boot REST API for multiple Request Params
    //The sequence of parameters can change, no effect to api working
    //http://localhost:8080/students/query-multiple?id=3&firstName=Ayush&lASTnaMe=Shukla
    @GetMapping("query-multiple")
    public Student studentRequestMultipleVariable(@RequestParam Integer id,
                                          @RequestParam String firstName,
                                          @RequestParam("lASTnaMe") String lastName) {
        Student student = new Student(id, firstName, lastName);
        return student;
    }

    //Spring Boot API that handle POST Request
    //http://localhost:8080/students/create

    /*
    Request format - POST
    Body > raw > JSON
    {
        "id":3,
        "firstName":"ayush",
        "lastName":"shukla"
    }
    */
    // send a http response code -201 on created.
    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student) {
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //Spring Boot API for PUT Request
    ////http://localhost:8080/students/3/update
    /*
    Request format - PUT
    Body > raw > JSON
    {
        "firstName":"ayush",
        "lastName":"shukla"
    }
    */
    @PutMapping("{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId) {
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    //Spring Boot API for DELETE Request
    //http://localhost:8080/students/2/delete
    @DeleteMapping("{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId) {
        return "Student deleted successfully.";
    }

}
