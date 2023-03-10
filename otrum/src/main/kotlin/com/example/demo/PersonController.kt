package com.example.demo

import com.example.models.Person
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private val data = mutableListOf<Person>(Person("Joacim", 34), Person("Christoffer", 28))

    @GetMapping("/ping")
    public fun index(): String {
        return "Hello World!";
    }

    @GetMapping()
    public fun getList(): ResponseEntity<List<Person>> {
        return ResponseEntity(data, HttpStatus.OK)
    }

    @PostMapping()
    public fun create(@RequestBody person: Person): ResponseEntity<Person> {
        data.add(person)
        return ResponseEntity(person, HttpStatus.CREATED)
    }
}