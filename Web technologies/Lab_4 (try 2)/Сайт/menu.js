main();

function main() {
    setupNavBarDropShadow();
    setupNavBarHobbiesDropdownMenu();
    setupMenuClock();
    setInterval(updateMenuClock, 1000);
}

function setupNavBarDropShadow() {
    $('#navList li a').add($('.dropdownMenu'))
                      .mouseenter(function() {
        $(this)
            .css('text-shadow', "0px 0px 10px var(--backgroundColor)");  
        });
        
    $('#navList li a').add($('.dropdownMenu'))
                      .mouseleave(function() {
        $(this)
            .css('text-shadow', "none");
    });
}

function setupNavBarHobbiesDropdownMenu() {
    let dropdownMenu = $('<ul style="cursor:default" class="dropdownMenu"></ul>');
    
    dropdownMenu
        .append($('<li> <h5> <a style="color:var(--primaryColor)" href="hobbies.html#hobby"> Любимое хобби </a> </h5> </li> <br>'))
        .append($('<li> <h5> <a style="color:var(--primaryColor)" href="hobbies.html#music"> Любимая музыка </a> </h5> </li> <br>'))
        .append($('<li> <h5> <a style="color:var(--primaryColor)" href="hobbies.html#videogames"> Любимые видеоигры </a> </h5> </li> <br>'));
    
    $('#hobbiesDropdownMenu')
        .append(dropdownMenu)
        .click(function(e) {
            $('.dropdownMenu').toggle(300);
        });

}

function setupMenuClock() {

    let time = new Date();
    let day = time.getDate();
    let month = Number(time.getMonth()) + 1;
    let year = time.getFullYear();
    let weekday = "";
    switch(Number(time.getDay())) {
        case 0: weekday = "Воскресенье"; break;
        case 1: weekday = "Понедельник"; break;
        case 2: weekday = "Вторник"; break;
        case 3: weekday = "Среда"; break;
        case 4: weekday = "Четверг"; break;
        case 5: weekday = "Пятница"; break;
        case 6: weekday = "Суббота"; break;
    };
    $('#navList').append($(`<li id="clock">${day}.${month}.${year} ${weekday}</li>`));
}

function updateMenuClock() {
    let time = new Date()
    let day = time.getDate();
    let month = Number(time.getMonth()) + 1;
    let year = time.getFullYear();
    let weekday = "";
    switch(Number(time.getDay())) {
        case 0: weekday = "Воскресенье"; break;
        case 1: weekday = "Понедельник"; break;
        case 2: weekday = "Вторник"; break;
        case 3: weekday = "Среда"; break;
        case 4: weekday = "Четверг"; break;
        case 5: weekday = "Пятница"; break;
        case 6: weekday = "Суббота"; break;
    };
    $("#clock").text(`${day}.${month}.${year} ${weekday}`);
}