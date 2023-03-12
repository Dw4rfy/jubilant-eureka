import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
// @ts-ignore
import Toaster from "@meforma/vue-toaster";
// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

/* import the fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core'
/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faTrashCan, faFloppyDisk, faPenToSquare, faSquarePlus, faCircleDown } from '@fortawesome/free-regular-svg-icons'

library.add(faTrashCan, faFloppyDisk, faPenToSquare, faSquarePlus, faCircleDown);

import './assets/main.css'

const vuetify = createVuetify({
  components,
  directives,
})

const app = createApp(App)

app.use(createPinia()).use(vuetify).component('font-awesome-icon', FontAwesomeIcon).use(Toaster)

app.mount('#app')
