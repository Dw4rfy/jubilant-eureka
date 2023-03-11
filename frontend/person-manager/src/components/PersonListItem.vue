<template>
    <div class="container">
        <div class="text-container">
            <v-text-field variant="outlined" :hide-details="true" v-model="name" label="Name"></v-text-field>
        </div>
        <v-text-field variant="outlined" :hide-details="true" v-model="age" label="Age"
            @keypress="onNumberInput"></v-text-field>
        <v-btn color="green" @click="onSave"><font-awesome-icon icon="fa-regular fa-floppy-disk" /></v-btn>
        <v-btn color="red" @click="onDelete"><font-awesome-icon icon="fa-regular fa-trash-can" /></v-btn>
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const props = defineProps<{
    name?: string,
    age?: number,
    id?: number
}>();

const inputAge = () => {
    if(!props.age || props.age === 0)
        return "";
    return `${props.age}`;
}

const name = ref(props.name);
const age = ref(inputAge());

const emit = defineEmits(["onSave", "onDelete", "onUpdate"]);

const onSave = () => {
    if(!props.id)
        emit("onSave", { name: name.value, age: Number.parseInt(age.value) });
    else
        emit("onUpdate", {id: props.id, name: name.value, age: Number.parseInt(age.value) })   
}

const onDelete = () => {
    emit("onDelete", props.id);
}

const onNumberInput = (event: KeyboardEvent) => {
    const number = Number.parseInt(event.key);
    if (Number.isNaN(number)) {
        event.preventDefault();
    } else {
        return true;
    }
}
</script>

<style scoped>
.container {
    display: flex;
    align-items: center;
    background-color: #383a40;
    padding: 0.75rem;
    margin: 0.5rem;
    border-radius: 0.25rem;
}

div>* {
    margin: 0rem 0.25rem;
}

.text-container {
    flex: 1 0 50%;
}
</style>