printHistory();
function printHistory() {
	
	if(window.history.length==1){
		localStorage.clear();
		localStorage.setItem(window.location.href, Number(localStorage.getItem(window.location.href))+1);
	}
	for(let i=0; i<localStorage.length;i++){
		let cell=document.createElement('td');
		let secondCell=document.createElement('td');
		console.log("Debug"+localStorage.key(i)+" "+localStorage.getItem(localStorage.key(i)));
		cell.innerHTML=localStorage.key(i);
		secondCell.innerHTML=localStorage.getItem(localStorage.key(i));
		document.querySelector('#tableHistory').appendChild(cell);
		document.querySelector('#tableHistory').appendChild(secondCell);
		document.querySelector('#tableHistory').appendChild(document.createElement('tr'));
		console.log(document.querySelector('#tableHistory'));
	}
}

printWholeHistory();
function printWholeHistory(){
	var obj = {};
	var cookies = document.cookie.split(/;/);
	console.log("Split: "+cookies);
	for (var i = 0, len = cookies.length; i < len; i++) {
		var cookie = cookies[i].split(/=/);
		obj[cookie[0]] = cookie[1];
		console.log("OBJ: "+cookie[0]+"Value"+obj[cookie[0]])
	}
	for(i in obj){
		console.log("Данные: "+obj[i]+":"+i);
		let cell=document.createElement('td');
		let secondCell=document.createElement('td');
		cell.innerHTML=i;
		secondCell.innerHTML=Number(obj[i]);
		document.querySelector('#wholeHistory').appendChild(cell);
		document.querySelector('#wholeHistory').appendChild(secondCell);
		document.querySelector('#wholeHistory').appendChild(document.createElement('tr'));
		console.log(document.querySelector('#wholeHistory'));
	}
}