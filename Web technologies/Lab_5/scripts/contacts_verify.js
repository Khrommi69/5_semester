
function verify(event){
	error_box=document.getElementById('error_box');
	form=document.getElementById('contact_form');
	username=form.elements.name;
	sex=form.elements.sex;
	age=form.elements.age;
	email=form.elements.email;
	tel=form.elements.telephone;
	messege=form.elements.messege;
	console.log('Строка:'+isNaN(age.valueAsNumber));
	if(username.value=='' || countWords(username.value)!==3 || countSpaces(username.value)!==2){
		error_box.innerHTML="Ошибка при заполнении формы. Введите корректное ФИО";
		username.focus();
		event.preventDefault();
		return;
	}
	if(sex.value==''){
		error_box.innerHTML="Ошибка при заполнении формы. Введите корректный гендер";
		sex.focus();
		event.preventDefault();
		return;
	}
	if(isNaN(age.valueAsNumber) || age.valueAsNumber<16 || age.valueAsNumber>96){
		error_box.innerHTML="Ошибка при заполнении формы. Введите корректный возраст";
		age.focus();
		event.preventDefault();
		return;
	}
	if(!/^.+@.+\..+$/.test(email.value)){
		error_box.innerHTML="Ошибка при заполнении формы. Введите корректный email";
		email.focus();
		event.preventDefault();
		return;
	}
	console.log(tel.value);
	if(!checkPhoneNumber(tel.value)){
		error_box.innerHTML="Ошибка при заполнении формы. Введите корректный номер телефона в формате \'+X (XXX) XXX-XX-XX\'";
		tel.focus();
		event.preventDefault();
		return;
	}
	if(messege.value==''){
		error_box.innerHTML="Ошибка при заполнении формы. Введите корректное значение в текстовое поле";
		messege.focus();
		event.preventDefault();
		return;
	}
	error_box.innerHTML = '';
	form=document.getElementById('contact_form').submit();
}

function checkPhoneNumber(phoneNumber) {
  let phoneRegex = /^\+\d \(\d{3}\) \d{3}-\d{2}-\d{2}$/;
  return phoneRegex.test(phoneNumber);
}

function countSpaces(str){
	counter=0;
	for (var i = 0; i < str.length; i++) {
		if(str[i]==' ')
			counter++;
	}
	return counter;
}

function countWords(str) {
  // Используем регулярное выражение, чтобы разбить строку на слова
  // Это выражение ищет любую последовательность символов, кроме пробелов и символов табуляции
  // и считает это за одно слово
  let words = str.split(/\s+/);
  return words.length;
}