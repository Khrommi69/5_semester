function updateLocalStorageAndCookies() {
    let title = document.title
    if (title == "Главная") {
        const pages = ["Главная", "Обо мне", "Мои интересы", "Учёба", "Фотоальбом", "Контакты", "Тест по инженерной графике", "История просмотра"];
        if (localStorage.getItem("init") != "YES") {
            localStorage.setItem("init", "YES");
            pages.forEach(element => {
                localStorage.setItem(element, 0);
            });
        }
    }
    let visits = parseInt(localStorage.getItem(title));
    localStorage.setItem(title, ++visits); 

    visits = ('; '+ document.cookie).split(`; ` + title + `=`).pop().split(';')[0];
    if (visits == null) {
        document.cookie = title + "=" + 0 + "; path=/";
        visits = parseInt(('; '+ document.cookie).split(`; ` + title + `=`).pop().split(';')[0]);
    }
    document.cookie = title + "=" + (++visits) + "; path=/";
}