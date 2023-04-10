photo_printer();

function photo_printer() {
	let table = document.querySelector(".img_table");
	let titles = [
		'5 этаж в стиле TLOU',
		'Жуткий коридор СевГУ',
		'Алексей в электричке',
		'Эмир из меня демона слепил в стиле КРД',
		'ЭГО?',
		'Сушист',
		'Здоровья погибшим',
		'Шери',
		'Шери на паспорт',
		'Исчезаю...',
		'Помятый',
		'Не много ли В.В. на стене?',
		'Ядерный гриб на горизонте',
		'тип New-York',
		'Ч/Б life :D',
		'POV: Эмир'];
	let imgs = [
		'img/1.jpg',
		'img/2.jpg',
		'img/3.jpg',
		'img/4.jpg',
		'img/5.jpg',
		'img/6.jpg',
		'img/7.jpg',
		'img/8.jpg',
		'img/9.jpg',
		'img/10.jpg',
		'img/11.jpg',
		'img/12.jpg',
		'img/13.jpg',
		'img/14.jpg',
		'img/15.jpg',
		'img/16.jpg'];

	let row;
	for (let i = 0; i < imgs.length; i++) {
		if (i % 3 == 0) {
			// начинаем новую строку таблицы каждые три изображения
			row = document.createElement("tr");
			table.appendChild(row);
		}

		// создаем ячейку таблицы для каждого изображения
		let cell = document.createElement("td");
		cell.classList.add("img_container");
		row.appendChild(cell);

		// создаем изображение
		let img = document.createElement("img");
		img.src = imgs[i];
		img.alt = titles[i];
		cell.appendChild(img);

		// создаем оверлей с заголовком
		let overlay = document.createElement("div");
		overlay.classList.add("img_overlay");
		cell.appendChild(overlay);

		let overlayText = document.createElement("p");
		overlayText.textContent = titles[i];
		overlay.appendChild(overlayText);
	}
}