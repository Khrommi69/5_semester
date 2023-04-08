function verify(event){
	error_box=document.getElementById('error_box');
	form=document.getElementById('test_form');
	username=form.elements.name;
	first_question=form.elements.f_quest;
	second_question=form.elements.sec_quest;
	third_question=form.elements.third_quest;
	if(username.value==''){
		error_box.innerHTML="Поле ФИО не может быть пустым";
		username.focus();
		event.preventDefault();
		return;
	}
	if(first_question.value==''){
		error_box.innerHTML="Ответьте на 1 вопрос";
		first_question.focus();
		event.preventDefault();
		return;
	}
	if(second_question.value==''){
		error_box.innerHTML="Ответьте на 2 вопрос";
		second_question.focus();
		event.preventDefault();
		return;
	}
	if(third_question.value=='' || countWords(third_question.value)<20){
		error_box.innerHTML="Ответьте на 3 вопрос (длина не менее 20 слов)";
		third_question.focus();
		event.preventDefault();
		return;
	}
	error_box.innerHTML = '';
	form=document.getElementById('contact_form').submit();
}

function countWords(str) {
  // Используем регулярное выражение, чтобы разбить строку на слова
  // Это выражение ищет любую последовательность символов, кроме пробелов и символов табуляции
  // и считает это за одно слово
  let words = str.split(/\s+/);
  return words.length;
}