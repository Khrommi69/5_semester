<template>
  <radio-options :options="[ { label: 'Мужской', value: 'male' }, { label: 'Женский', value: 'female' }]" v-model="form.sex" />
  <div>Selected sex: {{ form.sex }}</div>
</template>

<script>
import RadioOptionsVue from "./components/radio-options.vue";
export default {
  name: "App",
  components: {
    RadioOptions: RadioOptionsVue,
  },
  data: () => {
    return {
      form: {
        sex: ""
      }
    };
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
