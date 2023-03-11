import type { Person } from "@/types/person";
import axios from "axios";
import { defineStore } from "pinia";
import { computed, ref } from "vue";

export const usePersonStore = defineStore("person", () => {
    // STATE
    const personList = ref<Person[]>([]);

    // GETTERS
    const getAll = computed<Person[]>(() => personList.value);

    // ACTIONS
    const fetchAll = async () => {
        const request = await axios.get("http://localhost:8080/api/person");
        personList.value = request.data;
    }

    const createPerson = async (person: Person) => {
        const request = await axios.post("http://localhost:8080/api/person", person)
        const data = request.data;
        personList.value.pop();
        personList.value.push(data);
    }

    const createNewPerson = () => {
        if(personList.value.length > 0 && !personList.value[personList.value.length - 1].id)
            return;

        personList.value.push({name: "", age: 0})
    }

    const updatePerson = async (personUpdated: Person) => {
        const request = await axios.patch(`http://localhost:8080/api/person/${personUpdated.id}`, personUpdated)
        const data: Person = request.data;
        const index = personList.value.findIndex((x)=> x.id == data.id);
        personList.value[index] = data;
    }

    const deletePerson = async (id: number) => {
        await axios.delete(`http://localhost:8080/api/person/${id}`);
        const index = personList.value.findIndex((x)=> x.id == id);
        console.log(index);
        console.log(personList.value.splice(index,1));
    }

    return {
        getAll,
        fetchAll,
        createPerson,
        createNewPerson,
        updatePerson,
        deletePerson
    }
})