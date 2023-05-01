package com.rodriguez.novi.controllers;


import com.rodriguez.novi.entities.UserProfileEntity;
import com.rodriguez.novi.repositories.UsersProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;


@RestController //This allows you to exchange data with the inout and output en json format
public class UserProfileController {
    @Value("${app.pessage}")
    String message;


    private final Logger log = LoggerFactory.getLogger(UserProfileController.class); //build a variable to connect with logger which already is integrated in the logger class

    //attribute
    private UsersProfileRepository usersProfileRepository;

    //constructor

    public UserProfileController(UsersProfileRepository usersProfileRepository) {
        this.usersProfileRepository = usersProfileRepository;
    }


    //CRUD METHOD

    // search all persons in a list of persons

    /***
     * http://localhost:8080/api/get/users
     * @return
     ***/

    @GetMapping("/api/get/users")
    public List<UserProfileEntity> findAll() {
        //get person and give all person
        return usersProfileRepository.findAll();

    }


    /***
     * SEARCH A PERSON IN DATA BASE
     * http://localhost:8080/api/get/user/1
     * @param id
     * @return
     ***/
    @GetMapping("/api/get/user/{id}") //The OathVariable is to link with id, otherwise it not gonna work
    public ResponseEntity<UserProfileEntity> findOneById(@PathVariable Long id) {
        System.out.println(message);// Ths is jus to see if we running the soft in development mode or production
        Optional<UserProfileEntity> personOne = usersProfileRepository.findById(id); //It allows also to enter cero and in this case will give us a type of Optional data
        //Option one
//        if(personOne.isPresent()){
//            return personOne.get();
//        }else{
//            return null;
//        }

        //Option 2
        //return personOne.orElse(null);

        //Option 3
        if (personOne.isPresent()) {
            return ResponseEntity.ok(personOne.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }




    /***

     * http://localhost:8080/api/save/user
     * @return
     * RequestBody is to receive information coming from the extern
     */

    /***
     *  CREATE A PERSON IN DATA BASE
     * @param person
     * @param headers
     * @return
     */

    @PostMapping("/api/save/user")
    public ResponseEntity<UserProfileEntity> create(@RequestBody UserProfileEntity person, @RequestHeader HttpHeaders headers ){
        System.out.println(headers.get("User-Agent")); // This is usefully to see from witch browser of sort information is coming the input

        //check if the book does not exist in the database, if this exists than it have to be update
      if(person.getId() != null){ //if the id exist the book not be created
          log.warn("trying to create a user that already exist in the database");
          System.out.println("trying to create a user that already exist in the database");
          return ResponseEntity.badRequest().build();
      }
        UserProfileEntity result = usersProfileRepository.save(person);
        //save the book coming via de parameter in the database
        return ResponseEntity.ok(person);
    }


    /***
     * Update a person
     * http://localhost:8080/api/update/user
     * @param person
     * @return
     */
    @PutMapping("api/update/user")
    public ResponseEntity<UserProfileEntity> update(@RequestBody UserProfileEntity person){
        if(person.getId()==null){
            log.warn("Trying to update non existent person");
            return ResponseEntity.badRequest().build();
        }// it will check if the object does not exist than ik show and bad request message
        if(!usersProfileRepository.existsById(person.getId())){
            log.warn("not found the persoon in the database");
            return ResponseEntity.notFound().build();
        }
        UserProfileEntity result = usersProfileRepository.save(person); // it will update de person in case that the object has an id.
        return ResponseEntity.ok(result);
    }





    /***
     * delete a person in de database
     * http://localhost:8080/api/delete/user/1
     * @param id
     * @return
     */
    @ApiIgnore
    @DeleteMapping("api/delete/user/{id}")
    public ResponseEntity<UserProfileEntity> delete(@PathVariable Long id){

        if(usersProfileRepository.existsById(id)){
            log.warn("no person with this id in de database");
            return ResponseEntity.notFound().build();
        }

        usersProfileRepository.deleteById(id);

        return ResponseEntity.noContent().build();


    }

    /***
     * http://localhost:8080/api/delete/users
     * @return
     */
    @ApiIgnore
    @DeleteMapping("api/delete/users")
    public ResponseEntity<UserProfileEntity> deleteAll(){
        log.info("REST request allBooks");
        usersProfileRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }



}







