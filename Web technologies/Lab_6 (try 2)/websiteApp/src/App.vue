<script>
    export default {
        data: () => ({
            formData: {
                name: "",
                surname: "",
                age: null,
                sex: "",
                frameworks:[]
            },

            sexOptions: Object.freeze({
                M: "М",
                F: "Ж",
                S: "паркет"
            }),

            frameWorksOptions: Object.freeze({
                VUE: "котики",
                ANGULAR: "муравьи",
                SVELTE: "черви",
                REACT: "человеки"
            }),
        }),

        computed: {
            errors() {
                let errors = [];
                if (!this.formData.name) errors.push("Введите ИМЯ, АЛО! ");
                else errors.filter((error) => error.field !== "name");
                
                if (!this.formData.surname) errors.push("Введите ФАМИЛИЮ, АЛО! ");
                else errors.filter((error) => error.field !== "surname");
                
                if ((!this.formData.age && typeof this.formData.age !== "number") || (this.formData.age < 1)) errors.push("Укажите цифры пожалуйста! " );
                else errors.filter((error) => error.field !== "age");
                
                if (!this.formData.sex) errors.push("Какой у вас пол?! Надо бы указать...");
                else errors.filter((error) => error.field !== "sex");
                
                let result = Array.from(errors.values()).join(" ");

                return result;
            },

            result() {
                return "" 
                  + this.formData.name + " " 
                  + this.formData.surname + " " 
                  + " с уровнем IQ равным - " + this.formData.age
                  + ", является представителем \"" + this.formData.sex  + "\" пола"
                  + ((this.formData.frameworks != "") ? ". Так же ему известны кто такие " + this.formData.frameworks.join(", ") : "")
                  + ".";
            }
        },

        methods: {
            checkAndSubmitForm: function(event) {
                if (this.errors != "") event.preventDefault();
                else {
                  window.location.href = "mailto:danilkhromdewil@gmail.com?subject=Form&body=" + this.result;
                }
            }
        }
    }
</script>

<template>
    <form novalidate="true">
        <label for="firstname"> ВВЕДИТЕ ИМЯ </label> 
        <input type="text" v-model="formData.name" id="firstname"/>
        
        <label for="surname"> ВВЕДИТЕ ФАМИЛИЮ </label>
        <input type="text" v-model="formData.surname" id="surname"/>
        
        <label for="age"> УКАЖИТЕ СВОЙ IQ (по результатам iq-теста из VK) </label>
        <input type="number" v-model="formData.age" id="age"/>
        
        <br><br>
        <div style="display: flex; text-align: center">
            
            <div v-for="option in sexOptions" :key='option' style="flex: 1">
                <label :for="option">{{option}}</label>
                <input type="radio" :value="option" v-model="formData.sex" id="sex"> 
            </div>
        </div>
        
        <br><br>
        <div style="display: flex; text-align: center">
            <div v-for="option in frameWorksOptions" :key='option' style="flex: 1">
                <label :for="option">{{option}}</label>
                <br>
                <input type="checkbox" :value="option" v-model="formData.frameworks" id="frameworks">
            </div>
        </div>

        <br>
        <input type="button" value="Отправить" id="submitButton" @click="checkAndSubmitForm">

        <p v-if="errors != ''" id="resultError"> {{errors}} </p>
        <p v-else id="resultOk"> {{result}} </p>
    </form>
</template>