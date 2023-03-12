package io.github.dwarfy.repositories

import io.github.dwarfy.models.Person
import org.springframework.stereotype.Component

@Component
class PeopleRepository {
    private val _dataMap = hashMapOf<Int, Person>()
    private var _id = 0

    private fun getNextId(): Int {
        _id++
        return _id
    }

    fun create(name: String, age: Int): Person {
        val nextId = getNextId()
        val person = Person(nextId, name, age)
        _dataMap[nextId] = person

        return person
    }

    fun update(id: Int, data: Person): Person? {
        val person = _dataMap[id] ?: return null
        person.age = data.age
        person.name = data.name

        _dataMap[person.id] = person

        return person;
    }

    fun delete(id:Int): Boolean {
        if(_dataMap.remove(id) == null)
            return false
        return true
    }

    fun getListOfPeople(): Collection<Person>{
        return _dataMap.values;
    }
}