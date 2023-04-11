
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
