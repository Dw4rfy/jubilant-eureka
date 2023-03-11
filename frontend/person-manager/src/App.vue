<template>
  <div class="main">
    <div class="person-container">
      <div v-for="person in personStore.getAll" :key="person.id">
        <PersonListItem :id="person.id" :name="person.name" :age="person.age" @on-save="createPerson" @on-update="updatePerson" @on-delete="deletePerson"></PersonListItem>
      </div>
        <div class="button-footer">
          <v-btn color="blue" @click="onAdd"><font-awesome-icon icon="fa-regular fa-square-plus" /></v-btn>
          <v-btn color="blue-grey" @click="onReload"><font-awesome-icon icon="fa-regular fa-circle-down" /></v-btn>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted } from "vue";
import PersonListItem from "./components/PersonListItem.vue";
import { usePersonStore } from "./stores/person.store";
import type { Person } from "./types/person";

const personStore = usePersonStore();

onMounted(() => {
  personStore.fetchAll();
})

const onAdd = () => {
  personStore.createNewPerson();
}

const onReload = () => {
  personStore.fetchAll();
}

const createPerson = (person: Person) => {
  personStore.createPerson(person);
}

const updatePerson = (person: Person) => {
  personStore.updatePerson(person);
}

const deletePerson = (id: number) => {
  personStore.deletePerson(id);
} 

</script>

<style scoped>
.main {
  display: flex;
  height: 100%;
  justify-content: center;
}

.person-container {
  height: 100%;
  min-width: 50vw;
}

.button-footer{
  padding: 0rem 0.5rem;
  margin: 1rem 0rem;
  display: flex;
  justify-content: space-between;
}
</style>
