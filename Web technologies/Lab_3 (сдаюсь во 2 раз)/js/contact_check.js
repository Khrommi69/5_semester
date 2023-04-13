function verify(event) {
	const errorBox = document.querySelector(".error_box");
	const form = document.querySelector(".contact_form");
	const username = form.elements.name;
	const gender = form.elements.gender;
	const age = form.elements.age;
	const email = form.elements.email;
	const tel = form.elements.phone;
	const message = form.elements.message;

	// Проверка ФИО
	if (!validateUsername(username.value)) {
	  errorBox.innerHTML = "Ошибка при заполнении формы. Введите корректное ФИО";
	  console.log("Ошибка при заполнении формы. Введите корректное ФИО");
	  username.focus();
	  event.preventDefault();
	  return;
	}

	// Проверка выбора пола
	if (!gender.value) {
	  errorBox.innerHTML = "Ошибка при заполнении формы. Укажите свой пол";
	  console.log("Ошибка при заполнении формы. Укажите свой пол");
	  gender[0].focus();
	  event.preventDefault();
	  return;
	}

	// Проверка возраста
	if (isNaN(age.valueAsNumber) || age.valueAsNumber < 3 || age.valueAsNumber > 140) {
	  errorBox.innerHTML = "Ошибка при заполнении формы. Введите корректный возраст";
	  console.log("Ошибка при заполнении формы. Введите корректный возраст");
	  age.focus();
	  event.preventDefault();
	  return;
	}

	// Проверка email
	if (!validateEmail(email.value)) {
	  errorBox.innerHTML = "Ошибка при заполнении формы. Введите корректный email";
	  console.log("Ошибка при заполнении формы. Введите корректный email");
	  email.focus();
	  event.preventDefault();
	  return;
	}

	// Проверка номера телефона
	if (!validatePhoneNumber(tel.value)) {
	  errorBox.innerHTML = "Ошибка при заполнении формы. Введите корректный номер телефона";
	  console.log("Ошибка при заполнении формы. Введите корректный номер телефона");
	  tel.focus();
	  event.preventDefault();
	  return;
	}

	// Проверка текстового поля
	if (!message.value) {
	  errorBox.innerHTML = "Ошибка при заполнении формы. Введите текст сообщения";
	  console.log("Ошибка при заполнении формы. Введите текст сообщения");
	  message.focus();
	  event.preventDefault();
	  return;
	}

	// Если все проверки прошли успешно, очищаем errorBox
	errorBox.innerHTML = "";
}

// Функция проверки ФИО
function validateUsername(name) {
	const regex = /^[a-zA-Zа-яА-Я]+\s[a-zA-Zа-яА-Я]+\s[a-zA-Zа-яА-Я]+$/;
	return regex.test(name);
}

// Функция проверки email
function validateEmail(email) {
  const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return regex.test(email);
}

// Функция проверки номера телефона
function validatePhoneNumber(phone) {
  const regex = /^\+7\s?(\(\d{3}\)|\d{3})\s?\d{3}(-|\s)?\d{2}(-|\s)?\d{2}$/;
  return regex.test(phone);
}