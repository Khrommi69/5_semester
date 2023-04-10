
// КАРТИНОЧКИ ВОЗЛЕ ССЫЛОК НАВИГАЦИОННОЙ ПАНЕЛИ

// получаем все элементы li меню
const menuItems = document.querySelectorAll('.navigation_bar li');

// добавляем обработчик событий для каждого элемента меню
menuItems.forEach(item => {
  item.addEventListener('mouseenter', () => {
    // создаем элемент img
    const img = document.createElement('img');
    img.src = item.querySelector('a').href.includes(window.location.pathname) ? 'img/yellow.png' : 'img/red.png';
    img.alt = 'Signal';
    img.width = 10;
    img.height = 10;
    img.style.marginRight = '10px';
    // добавляем элемент img перед текстом ссылки
    item.insertBefore(img, item.firstChild);
  });
  
  item.addEventListener('mouseleave', () => {
    // удаляем элемент img
    item.removeChild(item.querySelector('img'));
  });
});



// ВЫПАДАЮЩЕЕ МЕНЮ ДЛЯ СТРАНИЦЫ "МОИ ИНТЕРЕСЫ"
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











