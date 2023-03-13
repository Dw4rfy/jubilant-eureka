import type { Person } from "@/types/person";
import axios from "axios";
import { defineStore } from "pinia";
import { computed, ref } from "vue";
// @ts-ignore
import { createToaster } from "@meforma/vue-toaster";

export const usePersonStore = defineStore("person", () => {
    const toaster = createToaster();
    const baseUrl: string = "http://localhost:8080/api/people";

    // STATE
    const personList = ref<Person[]>([]);

    // GETTERS
    const getAll = computed<Person[]>(() => personList.value);

    // ACTIONS
    const fetchAll = async () => {
        const request = await axios.get(baseUrl);
        personList.value = request.data;
    }

    const reloadList = async () => {
        personList.value = [];
        await fetchAll();
    }

    const createPerson = async (person: Person) => {
        const request = await axios.post(baseUrl, person)
        const data = request.data;
        personList.value.pop();
        personList.value.push(data);
        toaster.success(`Person with id ${data.id} added`, {position: "bottom", duration: 2000});
    }

    const createNewPerson = () => {
        if(personList.value.length > 0 && !personList.value[personList.value.length - 1].id)
            return;

        personList.value.push({name: "", age: 0})
    }

    const updatePerson = async (personUpdated: Person) => {
        const request = await axios.put(`${baseUrl}/${personUpdated.id}`, personUpdated)
        const data: Person = request.data;
        const index = personList.value.findIndex((x)=> x.id == data.id);
        personList.value[index] = data;
        toaster.success(`Person with id ${data.id} updated`, {position: "bottom", duration: 2000});
    }

    const deletePerson = async (id: number) => {
        await axios.delete(`${baseUrl}/${id}`);
        const index = personList.value.findIndex((x)=> x.id == id);
        index;
        personList.value.splice(index,1);
        toaster.success(`Person with id ${id} deleted`, {position: "bottom", duration: 2000});
    }

    return {
        getAll,
        fetchAll,
        reloadList,
        createPerson,
        createNewPerson,
        updatePerson,
        deletePerson
    }
})