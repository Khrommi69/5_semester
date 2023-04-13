// Для печати списков моих интересов на странице "Мои интересы"
function list_printer(listId, ...items) {
	const list = document.getElementById(listId);
	items.forEach(item => {
		const li = document.createElement('li');
		li.textContent = item;
		list.appendChild(li);
		console.log("Напечаталась строка" + li.textContent);
	});
}