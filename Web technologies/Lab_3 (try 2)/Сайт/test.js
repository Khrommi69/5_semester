function validateForm() {
    let form = document.forms[0];
    const mapForm = new Map([
        ["ФИО", form.elements.name.value],
        ["Группа", form.elements.group.value],
        ["Вопрос№1", form.elements.question1.value],
        ["Вопрос№2", form.elements.question2.value],
        ["Вопрос№3", form.elements.question3.value],
    ]);

    if (!validateEmptyInputs(mapForm)) return false;
    if (!validateName(mapForm.get("ФИО"))) return false;
    if (!validateTextArea(mapForm.get("Вопрос№3"))) return false;

    alert("Форма успешно отправлена!");
    return true;
}

function validateEmptyInputs(mapForm) {
    let isFilled = true;
    let formElement; // Текущий проверяемый элемент
    mapForm.forEach((value, key) => { // Сначала значение, потом ключ
        if (!isFilled) {
            return false;
        }
        if (value == "" || value == null) {
            formElement = key;
            isFilled = false;
        }
    });
    if (!isFilled) {
        alert("Пожалуйста, заполните поле " + formElement);
        document.getElementById(formElement).focus();
    }
    return isFilled;
}

function validateName(name) {
    let isValid = true;
    let regName = /^[А-Яа-яЁё]+ [А-Яа-яЁё]+ [А-Яа-яЁё]+$/;
    isValid = regName.test(name);
    if (!isValid) {
        alert("Неверное значение поля ФИО");
        document.getElementById("ФИО").focus();
    }
    return isValid;
}

function validateTextArea(textarea) {
    let isValid = true;
    let regArea = /^(?:[A-Za-zА-я]+[\s\r\n.,!?:-]*){1,30}$/;
    
    isValid = regArea.test(textarea);
    if (!isValid) {
        alert("Требуется ввести не более 30 слов");
        document.getElementById("Вопрос№3").focus();
    }
    return isValid;
}