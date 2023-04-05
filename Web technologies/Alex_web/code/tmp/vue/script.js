import { computed, createApp } from "../../lib/script/vuejs/vue.esm-browser.js";

const app = createApp({
  data()
  {
    return {
      formData:
      {
        name: "",
        lastName: "",
        age: 18,
        sex: "",
        frameworks: []
      },

      sexOptions: Object.freeze({
        M: "M",
        F: "F"
      }),

      frameworksOptions: Object.freeze({
        Vue: "Vue",
        Angular: "Angular",
        Svelte: "Svelte",
        React: "React"
      })
    };
  },

  computed: {
    errors()
    {
      const errors = [];

      if (!this.formData.name)
      {
        errors.push({
          field: "name", message: "Введите имя!"
        });
      }
      else
      {
        errors.filter((error) => error.field !== "name");
      }
      if (!this.formData.surname)
        errors.push({
          field: "surname", message: "Введите Фамилию!"
        });
      else
        errors.filter((error) => error.field !== "surname");
      if (!this.formData.age && typeof this.formData.age !==
        "number")
        errors.push({
          field: "age", message: "Укажите возраст!"
        });
      else errors.filter((error) => error.field !== "age");
      if (!this.formData.name)
        errors.push({
          field: "sex", message: "Укажите пол!"
        });
      else errors.filter((error) => error.field !== "sex");
      return errors;
    }
  }
});

app.mount("#app");
