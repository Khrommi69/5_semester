let list=document.querySelector('.nav_panel');
list.addEventListener("mouseenter",e=> checkPlace(e), true);
list.addEventListener("mouseleave",removeMarker, true);
console.log(list);
var isAlreadyMarked=false;


//localStorage.setItem(window.location.href, 0);

function checkPlace(e){
	if(e.target.localName!='li' || e.target.id=="notToShow")
		return;
	isAlreadyMarked=true;
	console.log(e.target);
	//console.log(list.children);
	console.log('Адрес: '+window.location.pathname);
	if(e.target.firstChild.href!=e.target.firstChild.baseURI){
		let img=document.createElement("img");
		img.setAttribute("src", "img/check_img_failed.png");
		img.setAttribute("height", "10px");
		img.setAttribute("id", "checkMark");
		e.target.insertBefore(img, e.target.firstChild);
		return
	}
	let img=document.createElement("img");
	img.setAttribute("src", "img/check_img.png");
	img.setAttribute("height", "10px");
	img.setAttribute("id", "checkMark");
	e.target.insertBefore(img, e.target.firstChild);
	//	e.target.innerHTML="<img src=\"img/check_img.png\" id=\"checkMark\" height=\"10px\">"+e.target.innerHTML;
	//list.children[0].innerHTML="<img src=\"img/check_img.png\" height=\"10px\">"+list.children[0].innerHTML;
}

function removeMarker(){
	if(document.getElementById("checkMark")!=null){
		console.log("remove");
		isAlreadyMarked=false;
		document.getElementById("checkMark").remove();
}

}

let interestsButton=document.querySelector("#myInterestsID");
interestsButton.addEventListener("mouseenter", e=>dropList(e), true);
console.log(interestsButton);

var shouldBeShown=true;
function dropList() {
	if(!shouldBeShown)
		return
	else 
		shouldBeShown=false;
	console.log("should be dropped");
    document.getElementById("myDropdown").classList.toggle("show");
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
	console.log("dropdown -");
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
        shouldBeShown=true;
      }
    }
  }
}


<!-- Часики -->
clockTime();
function clockTime() {
  let date = new Date();
  let day = date.getDate();
  let month = date.toLocaleString('default', { month: 'long' });
  let year = date.getFullYear();
  let time = date.toLocaleTimeString();
  let currentDate = `${day} ${month} ${year}, ${time}`;
  let clocks = document.querySelector(".clocks");
  clocks.innerHTML = currentDate;
  setTimeout(clockTime, 1000);
}


function printCalendar(){
	let month = new FormData(document.querySelector("#contact_form")).get("dateMonth");
	let year= new FormData(document.querySelector("#contact_form")).get("dateYear");
	console.log(month+":"+year);
	if(month=="" || year==""){
		closeCalendar();
		return;
	}
	if(!document.querySelector(".calendar").classList.contains('show'))
		document.querySelector(".calendar").classList.toggle("show");
	console.log("Календарь виден "+ document.querySelector(".calendar").classList);
	createCalendar(document.querySelector(".calendar"),year, month);
}	

document.querySelector("#dateMonth").addEventListener("focus", printCalendar, true);
document.querySelector("#dateYear").addEventListener("focus", printCalendar, true);
document.querySelector("#dateMonth").addEventListener("keyup", printCalendar, true);
document.querySelector("#dateYear").addEventListener("keyup", printCalendar, true);
document.querySelector("#dateMonth").addEventListener("change", printCalendar, true);
document.querySelector("#dateYear").addEventListener("change", printCalendar, true);
document.querySelector("#dateMonth").addEventListener("focusout", closeCalendar, true);
document.querySelector("#dateYear").addEventListener("focusout", closeCalendar, true);
let onCalendar=false;


function createCalendar(elem, year, month) {

      let mon = month - 1; // месяцы в JS идут от 0 до 11, а не от 1 до 12
      let d=new Date(year, mon);
      let cnt=0; 
      let table = '<table><tr><th>пн</th><th>вт</th><th>ср</th><th>чт</th><th>пт</th><th>сб</th><th>вс</th></tr><tr>';
      // пробелы для первого ряда
      // с понедельника до первого дня месяца
      // * * * 1  2  3  4
      for (let i = 0; i < getDay(d); i++) {
        table += '<td></td>';
      }
      // <td> ячейки календаря с датами
    
      while (d.getMonth() == mon) {
        table += '<td><button id=\"calendarButton'+ d.getDate()+'\">' + d.getDate() + '</button></td>';
        cnt=d.getDate();
        if (getDay(d) % 7 == 6) { // вс, последний день - перевод строки
          table += '</tr><tr>';
        }
        d.setDate(d.getDate() + 1);
      }

      // добить таблицу пустыми ячейками, если нужно
      // 29 30 31 * * * *
      if (getDay(d) != 0) {
        for (let i = getDay(d); i < 7; i++) {
          table += '<td></td>';
        }
      }
      // закрыть таблицу
      table += '</tr></table>';
      elem.innerHTML = table;
      for(let i=1; i<cnt; i++){
      	console.log("debug "+i+"  "+document.querySelector("#calendarButton"+i));
       	document.querySelector("#calendarButton"+i).addEventListener("click",day=>dataOutput(day) ,true);
       	document.querySelector("#calendarButton"+i).addEventListener("mouseenter", e=>{onCalendar=true}, true);
       	document.querySelector("#calendarButton"+i).addEventListener("mouseleave", e=>{onCalendar=false}, true);
       	console.log("Button"+document.querySelector("#calendarButton"+i));
      }
      
    }

     function getDay(date) { // получить номер дня недели, от 0 (пн) до 6 (вс)
      let day = date.getDay();
      if (day == 0) day = 7; // сделать воскресенье (0) последним днем
      return day - 1;
    }

    function closeCalendar(){
    	if(document.querySelector("#dateMonth")==document.activeElement || 
    		document.querySelector("#dateYear")==document.activeElement || onCalendar)
    		return;
    	if(document.querySelector(".calendar").classList.contains('show'))
    		document.querySelector(".calendar").classList.remove('show');
    }

    function dataOutput(day){
    	let month = new FormData(document.querySelector("#contact_form")).get("dateMonth");
		let year= new FormData(document.querySelector("#contact_form")).get("dateYear");
    	alert("Data: "+day.target.innerHTML+":"+month+":"+year);
    }