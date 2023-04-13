main();

function main() {
    loadAnchors(
    ['#hobby', 'Любимое хобби'],
    ['#music', 'Любимая музыка'],
    ['#videogames', 'Любимые видеоигры']/*,
    ['#books', 'Любимые книги'],
    ['#movies', 'Любимые фильмы']*/
    );
}

function loadAnchors(...entries) {
    
    let anchorMap = new Map(entries);
    
    // Нахождение тега aside
    let aside = document.getElementsByTagName("aside")[0];
    // Создание тега ol
    let ol = document.createElement("ol");
    
    // Добавление якорных ссылок вовнутрь ol
    for (let el of anchorMap) {
        let li = document.createElement("li");
        let h3 = document.createElement("h3");
        let a = document.createElement("a");
    
        a.href = el[0]; 
        a.innerText = el[1];
    
        h3.appendChild(a);
        li.appendChild(h3);
        ol.appendChild(li);
    }
    // Добавление ol вовнутрь aside
    aside.appendChild(ol);

}