
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


//ДАТА И ВРЕМЯ
function updateDateTime() {
    var date = new Date();
    var day = date.getDate();
    var monthNames = ['января', 'февраля', 'марта', 'апреля', 'мая', 'июня', 'июля', 'августа', 'сентября', 'октября', 'ноября', 'декабря'];
    var monthIndex = date.getMonth();
    var year = date.getFullYear();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();

    document.getElementById('current-date').innerHTML = day + ' ' + monthNames[monthIndex] + ' ' + year;
    document.getElementById('current-time').innerHTML = hours + ':' + (minutes < 10 ? '0' + minutes : minutes) + ':' + (seconds < 10 ? '0' + seconds : seconds);
}

updateDateTime();
setInterval(updateDateTime, 1000);


// для выпадающего меню вкладки "Мои интересы"
forDropdownMenu();

function forDropdownMenu() {
    setupNavBarDropShadow();
    setupNavBarHobbiesDropdownMenu();
}

function setupNavBarDropShadow() {
    Array.from(document.getElementById('navList').children).forEach(function(elem) {
        elem.addEventListener('mouseover', function(over) {
            over.target.style.textShadow = "0px 0px 10px var(--backgroundColor)";
        });
        elem.addEventListener('mouseout', function(out) {
            out.target.style.textShadow = "none";
        });
    });
}

function setupNavBarHobbiesDropdownMenu() {
    let link = document.getElementById("myInterestsLink");
    let ul = document.createElement("ul");
    
    let hobbies = document.createElement("li");
    let music = document.createElement("li");
    let films = document.createElement("li");
    
    let hobbies_h5 = document.createElement("h5");
    let music_h5 = document.createElement("h5");
    let films_h5 = document.createElement("h5");

    let hobbiesLink = document.createElement("a");
    let musicLink = document.createElement("a");
    let filmsLink = document.createElement("a");
    
    hobbiesLink.textContent = "Хобби";
    hobbiesLink.style.color = "var(--primaryColor)";
    hobbiesLink.setAttribute('href', "my_interests.html#hobbies_list");
    musicLink.textContent = "Музыка";
    musicLink.style.color = "var(--primaryColor)";
    musicLink.setAttribute('href', "my_interests.html#music_list");
    filmsLink.textContent = "Фильмы";
    filmsLink.style.color = "var(--primaryColor)";
    filmsLink.setAttribute('href', "my_interests.html#films_list");
    
    
    hobbies_h5.appendChild(hobbiesLink);
    music_h5.appendChild(musicLink);
    films_h5.appendChild(filmsLink);
    
    hobbies.appendChild(hobbies_h5);
    music.appendChild(music_h5);
    films.appendChild(films_h5);

    ul.appendChild(hobbies);
    ul.appendChild(music);
    ul.appendChild(films);
    ul.classList.add("myInterestsLink");
    link.appendChild(ul);

    link.addEventListener('click', () => ul.classList.toggle("show-link"));
}