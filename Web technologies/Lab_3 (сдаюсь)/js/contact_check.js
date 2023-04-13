const colorAccept= '#66FFA5';
const colorDeny = '#E6B3B3';
const primaryColor = '#004640';
const secondaryColor = '#e8ecec';
const hoverColor = '#D2DADA';

// const Months = { "Jan":0, "Feb":1, "Mar":2, "Apr":3, "May":4, "Jun":5, "Jul":6, "Aug":7, "Sep":8, "Oct":9, "Nov":10, "Dec":11 };
// const weekDayNames0 = ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"];
// const weekDayNames1 = ["Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"];
// const weekDayNames2 = ["Tu", "We", "Th", "Fr", "Sa", "Su", "Mo"];
// const weekDayNames3 = ["We", "Th", "Fr", "Sa", "Su", "Mo", "Tu"];
// const weekDayNames4 = ["Th", "Fr", "Sa", "Su", "Mo", "Tu", "We"];
// const weekDayNames5 = ["Fr", "Sa", "Su", "Mo", "Tu", "We", "Th"];
// const weekDayNames6 = ["Sa", "Su", "Mo", "Tu", "We", "Th", "Fr"];
// let weekDayNames = weekDayNames1;

// let defaultDate = new Date();

function main() {
    FormFocusoutValidation();
    // setupCalendar();
}

function FormFocusoutValidation(event) {
  const form = document.querySelector('.contact_form');
  const formElements = form.querySelectorAll('.input_block input, .input_block textarea');

  formElements.forEach(element => {
    element.addEventListener('focusout', function(event) {
      const input = event.target;
      let isValid = validateEmptyInput(input);

      if (input.id === 'email') {
        isValid = validateEmail(input.value);
      } else if (input.id === 'phone') {
        isValid = validatePhoneNumber(input.value);
      } else if (input.id === 'age') {
        isValid = validateAge(input.value);
      }

      if (isValid) {
        input.classList.remove('invalid');
        input.classList.add('valid');
      } else {
        input.classList.remove('valid');
        input.classList.add('invalid');
        const errorMessage = input.parentElement.querySelector('.error-message');
        if (errorMessage) {
          errorMessage.remove();
        }
        const error = document.createElement('span');
        error.classList.add('error-message');
        if (input.id === 'email') {
          error.textContent = 'Введите корректный email.';
        } else if (input.id === 'phone') {
          error.textContent = 'Введите корректный номер телефона.';
        } else if (input.id === 'age') {
          error.textContent = 'Введите корректный возраст.';
        } else {
          error.textContent = 'Это поле обязательно для заполнения.';
        }
        input.parentElement.appendChild(error);
      }
    });
  });
}

function formOnSubmitValidation(event) {
  const form = document.querySelector('.contact_form');
  const formElements = form.querySelectorAll('.input_block input, .input_block textarea');
  let isValid = true;

  formElements.forEach(element => {
    const input = element;
    let inputIsValid = true;

    if (input.id === 'name' || input.id === 'email') {
      inputIsValid = validateEmptyInput(input) && validateEmail(input.value);
    } else if (input.id === 'phone') {
      inputIsValid = validatePhoneNumber(input.value);
    } else if (input.id === 'age') {
      inputIsValid = validateAge(input.value);
    } else {
      inputIsValid = validateEmptyInput(input);
    }

    if (inputIsValid) {
      input.classList.remove('invalid');
      input.classList.add('valid');
    } else {
      input.classList.remove('valid');
      input.classList.add('invalid');
      isValid = false;
      const errorMessage = input.parentElement.querySelector('.error-message');
      if (errorMessage) {
        errorMessage.remove();
      }
      const error = document.createElement('span');
      error.classList.add('error-message');
      if (input.id === 'email') {
        error.textContent = 'Введите корректный email.';
      } else if (input.id === 'phone') {
        error.textContent = 'Введите корректный номер телефона.';
      } else if (input.id === 'age') {
        error.textContent = 'Введите корректный возраст.';
      } else {
        error.textContent = 'Это поле обязательно для заполнения.';
      }
      input.parentElement.appendChild(error);
    }
  });

  const errorBox = document.querySelector('.error_box');
  errorBox.innerHTML = '';

  if (!isValid) {
    event.preventDefault();
    errorBox.textContent = 'Пожалуйста, заполните все обязательные поля и исправьте ошибки.';
  }
}


function validateEmptyInput(element) {    
    if (!element.value.trim()) {
        element.placeholder = "Заполните поле";
        element.style.backgroundColor = colorDeny;
        return false;
    } else {
        element.style.backgroundColor = colorAccept;
        return true;
    }
}

function validateName(element) {
		let result = true;
    let regName = /^[a-zA-Zа-яА-Я]+\s[a-zA-Zа-яА-Я]+\s[a-zA-Zа-яА-Я]+$/;
    result = regName.test(element.value);
    if (!result) {
        element.value = "";
        element.placeholder = "Некорректное ФИО";
        element.style.backgroundColor = colorDeny;
        result = false;
        return result;
    } else {
        element.style.backgroundColor = colorAccept;
    }
    return result;
}

function validateEmail(email) {
  const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return re.test(email);
}

function validatePhoneNumber(phone) {
  const re = /^\+7\s\d{3}\s\d{3}-\d{2}-\d{2}$/;
  return re.test(phone);
}

function validateAge(age) {
  return age > 0 && age < 149;
}

function didTapResetButton() {
    const formElements = ["name", "age", "email", "phone", "message"]; //, "date"

    // document.getElementById("calendar").children[0].classList.add("hide");
    formElements.forEach(elementName => {
        element = document.getElementById(elementName);
        element.style.backgroundColor = secondaryColor;
    });
}


// //КАЛЕНДАРЬ Е****Й
// function setupCalendar() {
//     let birthDateInput = document.getElementById("Birth-date");

//     let birthDate = document.getElementById("calendar");

//     let calendar = document.createElement("div");
//     calendar.classList.add("divCalendar");
//     calendar.classList.add("hide");

//     let monthYear = document.createElement("div");
//     monthYear.classList.add("divMonthYear");

//     let dates = document.createElement("div");
//     dates.classList.add("divDates");


//     let monthSelect = document.createElement("select");
//     monthSelect.setAttribute('id', "monthSelect");
//     let yearSelect = document.createElement("select");
//     yearSelect.setAttribute('id', "yearSelect");

//     let defaultOption = document.createElement("option");
//     defaultOption.setAttribute('value', "Jan");
//     defaultOption.appendChild(document.createTextNode("Jan"));
//     monthSelect.appendChild(defaultOption);
//     defaultOption.setAttribute('selected', "selected");
//     const monthNames = ["Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
//     monthNames.forEach(element => {
//         let option = document.createElement("option");
//         option.setAttribute('value', element);
//         option.appendChild(document.createTextNode(element));
//         monthSelect.appendChild(option);
//     });
    
//     defaultOption = document.createElement("option");
//     defaultOption.setAttribute('value', "2007");
//     defaultOption.appendChild(document.createTextNode("2007"));
//     yearSelect.appendChild(defaultOption);
//     defaultOption.setAttribute('selected', "selected");
//     for (let index = 2006; index >= 1900; index--) {
//         let option = document.createElement("option");
//         option.setAttribute('value', index);
//         option.appendChild(document.createTextNode(index));
//         yearSelect.appendChild(option);
//     }

//     weekDayNames.forEach(element => {
//         let date = document.createElement("div");
//         date.classList.add("divWeekDay");
//         date.appendChild(document.createTextNode(element));
//         dates.appendChild(date);
        
//     });

//     for (let i = 1; i <= 31; i++) {
//         let date = document.createElement("div");
//         date.classList.add("divDate");
//         date.setAttribute('id', i);
//         date.appendChild(document.createTextNode(i));
//         date.addEventListener('mouseover', over =>  over.target.style.backgroundColor = hoverColor);
//         date.addEventListener('mouseout', out =>  out.target.style.backgroundColor = secondaryColor);
//         date.addEventListener('click', click => birthDateInput.value =  document.getElementById("monthSelect").value + "/" + parseInt(click.target.textContent) +  "/" + document.getElementById("yearSelect").value);
//         dates.appendChild(date);
//     }

//     monthYear.appendChild(monthSelect);
//     monthYear.appendChild(yearSelect);

//     calendar.appendChild(monthYear);
//     calendar.appendChild(dates);

//     birthDate.appendChild(calendar);

//     birthDateInput.addEventListener('click', () =>  calendar.classList.toggle("hide"));
//     calendar.addEventListener('click', () =>  calendar.classList.toggle("hide"));
//     yearSelect.addEventListener('click', () =>  calendar.classList.toggle("hide"));

//     const month30days = ["Apr", "Jun", "Sep", "Nov"];
//     const month31days = ["Jan", "Mar", "May", "Jul", "Aug", "Oct", "Dec"];
//     monthSelect.addEventListener('click', click => {
//         calendar.classList.toggle("hide");
//          if (month31days.includes(click.target.value)) {
//             [29, 30, 31].forEach( element => document.getElementById(element).classList.remove("hide"));
//          } else if (month30days.includes(click.target.value)){
//             [29, 30, 31].forEach( element => document.getElementById(element).classList.remove("hide"));
//            document.getElementById("31").classList.add("hide");
//          } else {
//             [29, 30, 31].forEach( element => document.getElementById(element).classList.add("hide"));
//          }
//          updateWeekDaysAndLeapYear();
//     });
//     yearSelect.addEventListener("click", click => {
//         updateWeekDaysAndLeapYear();
//     });
// }

// function updateWeekDaysAndLeapYear() {
//     if (document.getElementById("monthSelect") != null || document.getElementById("yearSelect") != null) {
//         let month = document.getElementById("monthSelect").value;
//         let year = document.getElementById("yearSelect").value;
//         defaultDate.setFullYear(parseInt(year));
//         defaultDate.setMonth(Months[`${month}`]);
//         defaultDate.setDate(1);
//         switch(parseInt(defaultDate.getDay())) {
//             case 0: weekDayNames = weekDayNames0; break;
//             case 1: weekDayNames = weekDayNames1; break;
//             case 2: weekDayNames = weekDayNames2; break;
//             case 3: weekDayNames = weekDayNames3; break;
//             case 4: weekDayNames = weekDayNames4; break;
//             case 5: weekDayNames = weekDayNames5; break;
//             case 6: weekDayNames = weekDayNames6; break;
//         }
//         for (let i = 0; i < 7; i++) {
//             document.getElementsByClassName("divWeekDay")[i].textContent = weekDayNames[i];
//         }
        
//         const isLeapYear = new Date(year, 1, 29).getDate() === 29;
//         (isLeapYear)? document.getElementById("29").classList.remove("hide") 
//                     : document.getElementById("29").classList.add("hide"); 
//     }
// }



main();























// function formOnSubmitValidation() {
// 	const errorBoxes = document.querySelectorAll(".error_box");
// 	const form = document.querySelector(".contact_form");
// 	const username = form.elements.name;
// 	const gender = form.elements.gender;
// 	const age = form.elements.age;
// 	const email = form.elements.email;
// 	const tel = form.elements.phone;
// 	const message = form.elements.message;
// 	const date = form.elements.date;

// 	// Проверка ФИО
// 	username.addEventListener('blur', () => {
// 		if (!validateUsername(username.value) || username.value.trim() === '') {
// 			errorBoxes[0].innerHTML = "Введите корректное ФИО";
// 			console.log("Введите корректное ФИО");
// 			username.style.border = "2px solid red";
// 			return false;
// 		} else {
// 			errorBoxes[0].innerHTML = "";
// 			username.style.border = "2px solid green";
// 			return true;
// 		}
// 	});

// 	// Проверка выбора пола
// 	gender.addEventListener('blur', () => {
// 		if (!gender.value || gender.value.trim() === '') {
// 			errorBoxes[1].innerHTML = "Укажите свой пол";
// 			console.log("Укажите свой пол");
// 			gender.style.border = "2px solid red";
// 			return false;
// 		} else {
// 			errorBoxes[1].innerHTML = "";
// 			gender.style.border = "2px solid green";
// 			return true;
// 		}
// 	});

// 	// Проверка возраста
// 	age.addEventListener('blur', () => {
// 		if (isNaN(age.valueAsNumber) || age.valueAsNumber < 3 || age.valueAsNumber > 140 || age.value.trim() === '') {
// 			errorBoxes[2].innerHTML = "Введите корректный возраст";
// 			console.log("Введите корректный возраст");
// 			age.style.border = "2px solid red";
// 			return false;
// 		} else {
// 			errorBoxes[2].innerHTML = "";
// 			age.style.border = "2px solid green";
// 			return true;
// 		}
// 	});

// 	// Проверка date
// 	date.addEventListener('blur', () => {
// 		if (!date.value || date.value.trim() === '') {
// 			errorBoxes[3].innerHTML = "Введите корректную дату";
// 			console.log("Введите корректную дату");
// 			date.style.border = "2px solid red";
// 			return false;
// 		} else {
// 			errorBoxes[3].innerHTML = "";
// 			date.style.border = "2px solid green";
// 			return true;
// 		}
// 	});

// 	// Проверка email
// 	email.addEventListener('blur', () => {
// 		if (!validateEmail(email.value) || email.value.trim() === '') {
// 			errorBoxes[4].innerHTML = "Введите корректный email";
// 			console.log("Введите корректный email");
// 			email.style.border = "2px solid red";
// 			return false;
// 		} else {
// 			errorBoxes[4].innerHTML = "";
// 			email.style.border = "2px solid green";
// 			return true;
// 		}
// 	});

// 	// Проверка номера телефона
// 	tel.addEventListener('blur', () => {
// 		if (!validatePhoneNumber(tel.value) || tel.value.trim() === '') {
// 			errorBoxes[5].innerHTML = "Введите корректный номер телефона";
// 			console.log("Введите корректный номер телефона");
// 			tel.style.border = "2px solid red";
// 			return false;
// 		} else {
// 			errorBoxes[5].innerHTML = "";
// 			tel.style.border = "2px solid green";
// 			return true;
// 		}
// 	});

// 	// Проверка текстового поля
// 	message.addEventListener('blur', () => {
// 		if (!message.value || message.value.trim() === '') {
// 			errorBoxes[6].innerHTML = "Введите текст сообщения";
// 			console.log("Введите текст сообщения");
// 			message.style.border = "2px solid red";
// 			return false;
// 		} else {
// 			errorBoxes[6].innerHTML = "";
// 			message.style.border = "2px solid green";
// 			return true;
// 		}
// 	});

// // Функция проверки ФИО
// function validateUsername(name) {
// 	const regex = /^[a-zA-Zа-яА-Я]+\s[a-zA-Zа-яА-Я]+\s[a-zA-Zа-яА-Я]+$/;
// 	return regex.test(name);
// }

// // Функция проверки email
// function validateEmail(email) {
//   const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//   return regex.test(email);
// }

// // Функция проверки номера телефона
// function validatePhoneNumber(phone) {
//   const regex = /^\+7\s?(\(\d{3}\)|\d{3})\s?\d{3}(-|\s)?\d{2}(-|\s)?\d{2}$/;
//   return regex.test(phone);
// }






















// const colorAccept= '#66FFA5';
// const colorDeny = '#E6B3B3';
// const primaryColor = '#004640';
// const secondaryColor = '#e8ecec';
// const hoverColor = '#D2DADA';

// const Months = { "Jan":0, "Feb":1, "Mar":2, "Apr":3, "May":4, "Jun":5, "Jul":6, "Aug":7, "Sep":8, "Oct":9, "Nov":10, "Dec":11 };
// const weekDayNames0 = ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"];
// const weekDayNames1 = ["Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"];
// const weekDayNames2 = ["Tu", "We", "Th", "Fr", "Sa", "Su", "Mo"];
// const weekDayNames3 = ["We", "Th", "Fr", "Sa", "Su", "Mo", "Tu"];
// const weekDayNames4 = ["Th", "Fr", "Sa", "Su", "Mo", "Tu", "We"];
// const weekDayNames5 = ["Fr", "Sa", "Su", "Mo", "Tu", "We", "Th"];
// const weekDayNames6 = ["Sa", "Su", "Mo", "Tu", "We", "Th", "Fr"];
// let weekDayNames = weekDayNames1;

// let defaultDate = new Date();

// main();

// function main() {
//     setupFormFocusoutValidation();
//     setupCalendar();
// }

// function formOnSubmitValidation() {
//     let result = true;
//     const formElements = ["Gender", "Age", "Birth-date", "Message", "Mail"];

//     formElements.forEach(element => {
//         if (!validateEmptyInput(document.getElementById(element))) result = false;
//     });

//     if (!validateName(document.getElementById("Name"))) result = false;
//     if (!validatePhoneNumber(document.getElementById("Phone-number"))) result = false;
//     if (!validateMail(document.getElementById("Mail"))) result = false;

//     return result;
// }

// function setupFormFocusoutValidation() {
//     const formElements = ["Gender", "Age", "Birth-date", "Message", "Mail"];

//     formElements.forEach(element => {
//         document.getElementById(element).addEventListener('focusout', out =>  validateEmptyInput(out.target));
//     });

//     document.getElementById("Name").addEventListener('focusout', out =>  validateName(out.target));
//     document.getElementById("Phone-number").addEventListener('focusout', out =>  validatePhoneNumber(out.target)); 
//     document.getElementById("Mail").addEventListener('focusout', out =>  validateMail(out.target)); 
// }

// function validateEmptyInput(element) {    
//     if (element.value == "" || element.value == null) {
//         element.placeholder = "Заполните поле ";
//         element.style.backgroundColor = colorDeny;
//         return false;
//     } else {
//         // element.placeholder = "...";
//         element.style.backgroundColor = colorAccept;
//         return true;
//     }
// }

// function validateName(element) {
//     let result = true;
//     let regName = /^[A-Za-zА-я]+ [A-Za-zА-я]+ [A-Za-zА-я]+$/;
//     result = regName.test(element.value);
//     if (!result) {
//         element.value = "";
//         element.placeholder = "Некорректные данные";
//         element.style.backgroundColor = colorDeny;
//     } else {
//         // element.placeholder = "...";
//         element.style.backgroundColor = colorAccept;
//     }
//     return result;
// }

// function validatePhoneNumber(element) {
//     let result = true;
//     let regPhoneNumber = /^[\+][7|3][\d]{7,9}[\d]$/;
//     result = regPhoneNumber.test(element.value);
//     if (!result) {
//         element.value = "";
//         element.placeholder = "Некорректные данные";
//         element.style.backgroundColor = colorDeny;
//     } else {
//         // element.placeholder = "...";
//         element.style.backgroundColor = colorAccept;
//     }
//     return result;
// }

// function validateMail(element) {
//     let result = true;
//     let regvalidateMail = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g;
//     result = regvalidateMail.test(element.value);
//     if (!result) {
//         element.value = "";
//         element.placeholder = "Некорректные данные";
//         element.style.backgroundColor = colorDeny;
//     } else {
//         // element.placeholder = "...";
//         element.style.backgroundColor = colorAccept;
//     }
//     return result;
// }

// function didTapResetButton() {
//     const formElements = ["Name", "Gender", "Age", "Birth-date", "Message", "Mail", "Phone-number"];

//     document.getElementById("calendar").children[0].classList.add("hide");
//     formElements.forEach(elementName => {
//         element = document.getElementById(elementName);
//         // element.placeholder = "...";
//         element.style.backgroundColor = secondaryColor;
//     });
// }

// function setupCalendar() {
//     let birthDateInput = document.getElementById("Birth-date");

//     let birthDate = document.getElementById("calendar");

//     let calendar = document.createElement("div");
//     calendar.classList.add("divCalendar");
//     calendar.classList.add("hide");

//     let monthYear = document.createElement("div");
//     monthYear.classList.add("divMonthYear");

//     let dates = document.createElement("div");
//     dates.classList.add("divDates");


//     let monthSelect = document.createElement("select");
//     monthSelect.setAttribute('id', "monthSelect");
//     let yearSelect = document.createElement("select");
//     yearSelect.setAttribute('id', "yearSelect");

//     let defaultOption = document.createElement("option");
//     defaultOption.setAttribute('value', "Jan");
//     defaultOption.appendChild(document.createTextNode("Jan"));
//     monthSelect.appendChild(defaultOption);
//     defaultOption.setAttribute('selected', "selected");
//     const monthNames = ["Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
//     monthNames.forEach(element => {
//         let option = document.createElement("option");
//         option.setAttribute('value', element);
//         option.appendChild(document.createTextNode(element));
//         monthSelect.appendChild(option);
//     });
    
//     defaultOption = document.createElement("option");
//     defaultOption.setAttribute('value', "2007");
//     defaultOption.appendChild(document.createTextNode("2007"));
//     yearSelect.appendChild(defaultOption);
//     defaultOption.setAttribute('selected', "selected");
//     for (let index = 2006; index >= 1900; index--) {
//         let option = document.createElement("option");
//         option.setAttribute('value', index);
//         option.appendChild(document.createTextNode(index));
//         yearSelect.appendChild(option);
//     }

//     weekDayNames.forEach(element => {
//         let date = document.createElement("div");
//         date.classList.add("divWeekDay");
//         date.appendChild(document.createTextNode(element));
//         dates.appendChild(date);
        
//     });

//     for (let i = 1; i <= 31; i++) {
//         let date = document.createElement("div");
//         date.classList.add("divDate");
//         date.setAttribute('id', i);
//         date.appendChild(document.createTextNode(i));
//         date.addEventListener('mouseover', over =>  over.target.style.backgroundColor = hoverColor);
//         date.addEventListener('mouseout', out =>  out.target.style.backgroundColor = secondaryColor);
//         date.addEventListener('click', click => birthDateInput.value =  document.getElementById("monthSelect").value + "/" + parseInt(click.target.textContent) +  "/" + document.getElementById("yearSelect").value);
//         dates.appendChild(date);
//     }

//     monthYear.appendChild(monthSelect);
//     monthYear.appendChild(yearSelect);

//     calendar.appendChild(monthYear);
//     calendar.appendChild(dates);

//     birthDate.appendChild(calendar);

//     birthDateInput.addEventListener('click', () =>  calendar.classList.toggle("hide"));
//     calendar.addEventListener('click', () =>  calendar.classList.toggle("hide"));
//     yearSelect.addEventListener('click', () =>  calendar.classList.toggle("hide"));

//     const month30days = ["Apr", "Jun", "Sep", "Nov"];
//     const month31days = ["Jan", "Mar", "May", "Jul", "Aug", "Oct", "Dec"];
//     monthSelect.addEventListener('click', click => {
//         calendar.classList.toggle("hide");
//          if (month31days.includes(click.target.value)) {
//             [29, 30, 31].forEach( element => document.getElementById(element).classList.remove("hide"));
//          } else if (month30days.includes(click.target.value)){
//             [29, 30, 31].forEach( element => document.getElementById(element).classList.remove("hide"));
//            document.getElementById("31").classList.add("hide");
//          } else {
//             [29, 30, 31].forEach( element => document.getElementById(element).classList.add("hide"));
//          }
//          updateWeekDaysAndLeapYear();
//     });
//     yearSelect.addEventListener("click", click => {
//         updateWeekDaysAndLeapYear();
//     });
// }

// function updateWeekDaysAndLeapYear() {
//     if (document.getElementById("monthSelect") != null || document.getElementById("yearSelect") != null) {
//         let month = document.getElementById("monthSelect").value;
//         let year = document.getElementById("yearSelect").value;
//         defaultDate.setFullYear(parseInt(year));
//         defaultDate.setMonth(Months[`${month}`]);
//         defaultDate.setDate(1);
//         switch(parseInt(defaultDate.getDay())) {
//             case 0: weekDayNames = weekDayNames0; break;
//             case 1: weekDayNames = weekDayNames1; break;
//             case 2: weekDayNames = weekDayNames2; break;
//             case 3: weekDayNames = weekDayNames3; break;
//             case 4: weekDayNames = weekDayNames4; break;
//             case 5: weekDayNames = weekDayNames5; break;
//             case 6: weekDayNames = weekDayNames6; break;
//         }
//         for (let i = 0; i < 7; i++) {
//             document.getElementsByClassName("divWeekDay")[i].textContent = weekDayNames[i];
//         }
        
//         const isLeapYear = new Date(year, 1, 29).getDate() === 29;
//         (isLeapYear)? document.getElementById("29").classList.remove("hide") 
//                     : document.getElementById("29").classList.add("hide"); 
//     }
// }
















// function FormFocusoutValidation(event) {
//     const form = document.querySelector('.contact_form');
//     const formElements = form.querySelectorAll('.input_block input, .input_block textarea');

// 		formElements.forEach(element => {
//         element.addEventListener('focusout', function(event) {
//             const input = event.target;
//             const isValid = validateEmptyInput(input);

//             if (isValid) {
//                 input.classList.remove('invalid');
//                 input.classList.add('valid');
//             } else {
//                 input.classList.remove('valid');
//                 input.classList.add('invalid');
//                 const errorMessage = input.parentElement.querySelector('.error-message');
//                 if (errorMessage) {
//                     errorMessage.remove();
//                 }
//                 const error = document.createElement('span');
//                 error.classList.add('error-message');
//                 error.textContent = 'Это поле обязательно для заполнения.';
//                 input.parentElement.appendChild(error);
//             }
//         });
//     });

// 		const phoneInput = form.querySelector('#phone');
// 	    phoneInput.addEventListener('focusout', function(event) {
// 	        const input = event.target;
// 	        const isValid = validatePhoneNumber(input);

// 	        if (isValid) {
// 	            input.classList.remove('invalid');
// 	            input.classList.add('valid');
// 	        } else {
// 	            input.classList.remove('valid');
// 	            input.classList.add('invalid');
// 	            const errorMessage = input.parentElement.querySelector('.error-message');
// 	            if (errorMessage) {
// 	                errorMessage.remove();
// 	            }
// 	            const error = document.createElement('span');
// 	            error.classList.add('error-message');
// 	            error.textContent = 'Введите корректный номер телефона.';
// 	            input.parentElement.appendChild(error);
// 	        }
// 	    });

//         const emailInput = form.querySelector('#email');
//    			emailInput.addEventListener('focusout', function(event) {
//         const input = event.target;
//         const isValid = validateEmail(input);

//         if (isValid) {
//             input.classList.remove('invalid');
//             input.classList.add('valid');
//         } else {
//             input.classList.remove('valid');
//             input.classList.add('invalid');
//             const errorMessage = input.parentElement.querySelector('.error-message');
//             if (errorMessage) {
//                 errorMessage.remove();
//             }
//             const error = document.createElement('span');
//             error.classList.add('error-message');
//             error.textContent = 'Введите корректный адрес электронной почты.';
//             input.parentElement.appendChild(error);
//         }
//     });

//     const messageInput = form.querySelector('#message');
//     messageInput.addEventListener('focusout', function(event) {
//         const input = event.target;
//         const isValid = validateEmptyInput(input);

//         if (isValid) {
//             input.classList.remove('invalid');
//             input.classList.add('valid');
//         } else {
//             input.classList.remove('valid');
//             input.classList.add('invalid');
//             const errorMessage = input.parentElement.querySelector('.error-message');
//             if (errorMessage) {
//                 errorMessage.remove();
//             }
//             const error = document.createElement('span');
//             error.classList.add('error-message');
//             error.textContent = 'Это поле обязательно для заполнения.';
//             input.parentElement.appendChild(error);
//         }
//     });
// }