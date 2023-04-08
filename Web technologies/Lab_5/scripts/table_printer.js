table_printer();


function table_printer() {
	let table=document.querySelector('#table_id');
	let titles=['5 этаж в стиле TLOU','Жуткий коридор СевГУ','Алексей в электричке','Эмир из меня демона слепил в стиле КРД','ЭГО?', 'Сушист', 'Здоровья погибшим',
	 'Шери', 'Шери на паспорт', 'Исчезаю...', 'Помятый', 'Не много ли В.В. на стене?', 'Ядерный гриб на горизонте', 'тип New-York', 'Ч/Б life :D', 'POV: Эмир'];
	let imgs=['img/1.jpg', 'img/2.jpg', 'img/3.jpg', 'img/4.jpg', 'img/5.jpg', 'img/6.jpg', 'img/7.jpg',
	 'img/8.jpg', 'img/9.jpg', 'img/10.jpg', 'img/11.jpg', 'img/12.jpg', 'img/13.jpg', 'img/14.jpg', 'img/15.jpg', 'img/16.jpg'];
	str='';
	for(let i=0; i<4; i++){
		var tr=document.createElement("tr");
		table.appendChild(tr);
		for(let j=0; j<4; j++){
			var cell=document.createElement('td');
			tr.appendChild(cell);
			var container=document.createElement('div');
			container.setAttribute("class", "img_frame");
			cell.appendChild(container);
			console.log('<img src="'+imgs[i*4+j]+'"'+'title="1", alt=img'+i*4+j+'><div class="img_title">'+titles[i*4+j]+'</div>');
			container.innerHTML='<img src="'+imgs[i*4+j]+'"'+'title="1", alt=img'+i*4+j+'><div class="img_title">'+titles[i*4+j]+'</div>';
		}
	}
}
