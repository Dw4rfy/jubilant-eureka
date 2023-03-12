package io.github.dwarfy.repositories

import io.github.dwarfy.models.Person
import io.github.dwarfy.models.PersonRequest
import org.springframework.stereotype.Component

@Component
class PeopleRepository {
    private val personByIdMap = hashMapOf<Int, Person>()
    private var id = 0

    private fun getNextId(): Int {
        id++
        return id
    }

    fun create(name: String, age: Int): Person {
        val nextId = getNextId()
        val person = Person(nextId, name, age)
        personByIdMap[nextId] = person

        return person
    }

    fun update(personId: Int, data: PersonRequest): Person? {
        val person = personByIdMap[personId] ?: return null
        person.age = data.age
        person.name = data.name

        personByIdMap[person.id] = person

        return person
    }

    fun delete(personId:Int): Boolean = personByIdMap.remove(personId) != null

    fun getListOfPeople(): Collection<Person> =  personByIdMap.values
}