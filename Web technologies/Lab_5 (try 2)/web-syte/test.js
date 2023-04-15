function validateForm() {
    const mapForm = new Map([
        ["ФИО", $("[name=name]")],
        ["Группа", $("[name=group]")],
        ["Вопрос№1", $("[name=question1]")],
        ["Вопрос№2", $("[name=question2]")],
        ["Вопрос№3", $("[name=question3]")]
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
        $('#'+formElement).focus();
    }
    return isFilled;
}

function validateName(name) {
    let isValid = true;
    let regName = /^[А-Яа-яЁё]+ [А-Яа-яЁё]+ [А-Яа-яЁё]+$/;
    isValid = regName.test(name);
    if (!isValid) {
        alert("Неверное значение поля ФИО");
        $("#ФИО").focus();
    }
    return isValid;
}

function validateTextArea(textarea) {
    let isValid = true;
    let regArea = /^(?:[A-Za-zА-я]+[\s\r\n.,!?:-]*){1,30}$/;
    
    isValid = regArea.test(textarea);
    if (!isValid) {
        alert("Требуется ввести не более 30 слов");
        $("#Вопрос№3").focus();
    }
    return isValid;
}