main();

function main() {
    setupNavBarDropShadow();
    setupNavBarHobbiesDropdownMenu();
    setupMenuClock();
    setInterval(updateMenuClock, 1000);
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
    let link = document.getElementById("hobbiesDropdownMenu");
    let ul = document.createElement("ul");
    
    let hobby = document.createElement("li");
    let music = document.createElement("li");
    let videogames = document.createElement("li");
    
    let hobby_h5 = document.createElement("h5");
    let music_h5 = document.createElement("h5");
    let videogames_h5 = document.createElement("h5");

    let hobbyLink = document.createElement("a");
    let musicLink = document.createElement("a");
    let videogamesLink = document.createElement("a");
    
    hobbyLink.textContent = "Любимое хобби";
    hobbyLink.style.color = "var(--primaryColor)";
    hobbyLink.setAttribute('href', "hobbies.html#hobby");
    musicLink.textContent = "Любимая музыка";
    musicLink.style.color = "var(--primaryColor)";
    musicLink.setAttribute('href', "hobbies.html#music");
    videogamesLink.textContent = "Любимые видеоигры";
    videogamesLink.style.color = "var(--primaryColor)";
    videogamesLink.setAttribute('href', "hobbies.html#videogames");
    
    
    hobby_h5.appendChild(hobbyLink);
    music_h5.appendChild(musicLink);
    videogames_h5.appendChild(videogamesLink);
    
    hobby.appendChild(hobby_h5);
    music.appendChild(music_h5);
    videogames.appendChild(videogames_h5);

    ul.appendChild(hobby);
    ul.appendChild(music);
    ul.appendChild(videogames);
    ul.classList.add("dropdownMenu");
    link.appendChild(ul);

    link.addEventListener('click', () => ul.classList.toggle("show-link"));
}

function setupMenuClock() {

    let list = document.getElementById("navList");
    let time = new Date();
    let clock = document.createElement('li');
    day = time.getDate();
    month = Number(time.getMonth()) + 1;
    year = time.getFullYear();
    weekday = "";
    switch(Number(time.getDay())) {
        case 0: weekday = "Воскресенье"; break;
        case 1: weekday = "Понедельник"; break;
        case 2: weekday = "Вторник"; break;
        case 3: weekday = "Среда"; break;
        case 4: weekday = "Четверг"; break;
        case 5: weekday = "Пятница"; break;
        case 6: weekday = "Суббота"; break;
    };
    clock.textContent = day + "." + month + "." + year + " " + weekday;
    clock.classList.add("clock");
    list.appendChild(clock);
}

function updateMenuClock() {
    let time = new Date()
    day = time.getDate();
    month = Number(time.getMonth()) + 1;
    year = time.getFullYear();
    weekday = "";
    switch(Number(time.getDay())) {
        case 0: weekday = "Воскресенье"; break;
        case 1: weekday = "Понедельник"; break;
        case 2: weekday = "Вторник"; break;
        case 3: weekday = "Среда"; break;
        case 4: weekday = "Четверг"; break;
        case 5: weekday = "Пятница"; break;
        case 6: weekday = "Суббота"; break;
    };
    document.getElementsByClassName("clock")[0].textContent = day + "." + month + "." + year + " " + weekday;
}