package io.github.dwarfy.controllers

import io.github.dwarfy.models.Person
import io.github.dwarfy.repositories.PeopleRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/people")
@CrossOrigin(origins = ["http://localhost:5173"])
public class PeopleController(val peopleRepository: PeopleRepository) {
    init {
        peopleRepository.create("Joacim", 34);
        peopleRepository.create("Christoffer", 28)
    }

    @GetMapping("/hello")
    public fun index(): String {
        return "Hello World!";
    }

    @GetMapping
    public fun getList(): ResponseEntity<Collection<Person>> {
        return ResponseEntity(peopleRepository.getListOfPeople(), HttpStatus.OK)
    }

    @PostMapping
    public fun create(@RequestBody input: Person): ResponseEntity<Person> {
        if (input.name.isNullOrEmpty() || input.age <= 0) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        var person = peopleRepository.create(input.name, input.age)
        return ResponseEntity(person, HttpStatus.CREATED)
    }

    @PatchMapping("{id}")
    public fun update(@PathVariable id: Int, @RequestBody data: Person): ResponseEntity<Person> {
        var person = peopleRepository.update(id, data)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(person, HttpStatus.OK)
    }

    @DeleteMapping("{id}")
    public fun delete(@PathVariable id: Int): ResponseEntity<String> {
        if (!peopleRepository.delete(id))
            return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}