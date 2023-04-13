// function verify(event) {
// 	const errorBox = document.querySelector(".test_error_box");
// 	const form = document.querySelector(".physics_test_form");
// 	const username = form.elements.full-name;
// 	...

// 	// Проверка ФИО
// 	if (!validateUsername(username.value)) {
// 	  errorBox.innerHTML = "Ошибка при заполнении формы. Введите корректное ФИО";
// 	  console.log("Ошибка при заполнении формы. Введите корректное ФИО");
// 	  username.focus();
// 	  event.preventDefault();
// 	  return;
// 	}

// 	// Проверка ...
// 	...

// 	// Если все проверки прошли успешно, очищаем errorBox
// 	errorBox.innerHTML = "";
// }

function verify(event) {
  const errorBox = document.querySelector(".test_error_box");
  const form = document.querySelector(".physics_test_form");
  const username = form.elements["full-name"];
  const symbol = form.elements["symbol"];
  const kirchhoffLaw = form.elements["kirchhoff-law"];

  // Проверка ФИО
  if (!validateUsername(username.value)) {
    errorBox.innerHTML = "Ошибка при заполнении формы. Введите корректное ФИО";
    console.log("Ошибка при заполнении формы. Введите корректное ФИО");
    username.focus();
    event.preventDefault();
    return;
  }

  // Проверка выбранного значения "Сила тока обозначается символом..."
  if (!symbol.value) {
    errorBox.innerHTML = "Ошибка при заполнении формы. Выберите значение для силы тока";
    console.log("Ошибка при заполнении формы. Выберите значение для силы тока");
    event.preventDefault();
    return;
  }

  // Проверка поля "Первый закон Кирхгофа (Закон токов)"
  if (!validateKirchhoffLaw(kirchhoffLaw.value)) {
    errorBox.innerHTML = "Ошибка при заполнении формы. Поле 'Первый закон Кирхгофа' должно содержать минимум 20 слов";
    console.log("Ошибка при заполнении формы. Поле 'Первый закон Кирхгофа' должно содержать минимум 20 слов");
    kirchhoffLaw.focus();
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

// Функция проверки поля "Первый закон Кирхгофа (Закон токов)"
function validateKirchhoffLaw(kirchhoffLaw) {
  const words = kirchhoffLaw.split(" ");
  return words.length >= 20;
}
