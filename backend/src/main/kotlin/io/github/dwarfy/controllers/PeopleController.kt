package io.github.dwarfy.controllers

import io.github.dwarfy.models.Person
import io.github.dwarfy.models.PersonRequest
import io.github.dwarfy.repositories.PeopleRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/people")
@CrossOrigin(origins = ["http://localhost:5173"])
class PeopleController(private val peopleRepository: PeopleRepository) {
    @GetMapping
    fun getList(): ResponseEntity<Collection<Person>> {
        return ResponseEntity(peopleRepository.getListOfPeople(), HttpStatus.OK)
    }

    @PostMapping
    fun create(@RequestBody input: PersonRequest): ResponseEntity<Person> {
        if (input.name.isEmpty() || input.age <= 0) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val person = peopleRepository.create(input.name, input.age)
        return ResponseEntity(person, HttpStatus.CREATED)
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: Int, @RequestBody data: PersonRequest): ResponseEntity<Person> {
        val person = peopleRepository.update(id, data)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(person, HttpStatus.OK)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<String> {
        if (!peopleRepository.delete(id))
            return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}