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
    
    let ol = $("<ol></ol>");
    // Добавление якорных ссылок вовнутрь ol
    for (let el of anchorMap) {
        ol.append(`<li><h3><a href=${el[0]}> ${el[1]} </a></h3></li>`);
    }
    // Добавление ol вовнутрь aside
    $("aside").append(ol);
}