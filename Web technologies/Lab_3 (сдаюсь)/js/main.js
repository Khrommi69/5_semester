
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
// Получаем элементы, с которыми будем работать
const myInterestsLink = document.getElementById('myInterestsLink');
const myInterestsMenu = document.getElementById('myInterestsMenu');

// Функция для скрытия меню
function hideMenu() {
  myInterestsMenu.style.display = 'none';
}

// Функция для отображения меню
function showMenu() {
  myInterestsMenu.style.display = 'block';
}

// Функция для переключения отображения меню
function toggleMenu() {
  if (myInterestsMenu.style.display === 'none') {
    showMenu();
  } else {
    hideMenu();
  }
}

// Скрываем меню при загрузке страницы
hideMenu();

// Обработчик клика по ссылке "Мои интересы"
myInterestsLink.addEventListener('click', (event) => {
  event.preventDefault();
  toggleMenu();
});

// Обработчик клика на любом месте сайта, кроме ссылки "Мои интересы"
document.addEventListener('click', (event) => {
  const target = event.target;
  if (target !== myInterestsLink && !myInterestsMenu.contains(target)) {
    hideMenu();
  }
});

// Обработчики клика на ссылки якорей
const hobbiesLink = document.querySelector('a[href="#hobbies_list"]');
const musicLink = document.querySelector('a[href="#music_list"]');
const filmsLink = document.querySelector('a[href="#films_list"]');

hobbiesLink.addEventListener('click', (event) => {
  event.preventDefault();
  location.href = 'my_interests.html#hobbies_list';
});

musicLink.addEventListener('click', (event) => {
  event.preventDefault();
  location.href = 'my_interests.html#music_list';
});

filmsLink.addEventListener('click', (event) => {
  event.preventDefault();
  location.href = 'my_interests.html#films_list';
});