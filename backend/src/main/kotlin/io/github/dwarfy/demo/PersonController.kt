package io.github.dwarfy.demo

import io.github.dwarfy.models.Person
import io.github.dwarfy.utils.IdGenerator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private val dataMap = hashMapOf<Int, Person>()

    init {
        val firstId = IdGenerator.getNextId()
        dataMap[firstId] = Person(firstId, "Joacim", 34)
        val secondId = IdGenerator.getNextId()
        dataMap[secondId] = Person(secondId, "Christoffer", 28)
    }

    @GetMapping("/ping")
    public fun index(): String {
        return "Hello World!";
    }

    @GetMapping
    public fun getList(): ResponseEntity<MutableCollection<Person>> {
        return ResponseEntity(dataMap.values, HttpStatus.OK)
    }

    @PostMapping
    public fun create(@RequestBody person: Person): ResponseEntity<Person> {
        var id = IdGenerator.getNextId()
        person.id = id
        dataMap[id] = person

        return ResponseEntity(person, HttpStatus.CREATED)
    }

    @PatchMapping
    public fun update(@RequestBody data: Person): ResponseEntity<Person> {
        val person = dataMap[data.id] ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        person.age = data.age
        person.name = data.name

        dataMap[person.id] = person

        return ResponseEntity(person, HttpStatus.OK)
    }

    @DeleteMapping("{id}")
    public fun delete(@PathVariable id: Int): ResponseEntity<String> {
        dataMap.remove(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}