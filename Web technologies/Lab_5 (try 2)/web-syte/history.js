let headers = document.getElementById("tableCurrentSession").rows[0].cells;
let cellsCurrentSession = document.getElementById("tableCurrentSession").rows[1].cells;
let cellsAllSessions = document.getElementById("tableAllSessions").rows[1].cells;

updateAllSessionsCells(cellsAllSessions);
updateCurrentSessionCells(cellsCurrentSession);

function updateAllSessionsCells(cells) {
    for (let i = 1; i < cells.length; i++) {
        cells[i].textContent = localStorage.getItem(headers[i].textContent)
    }
}

function updateCurrentSessionCells(cells) {
    for (let i = 1; i < cells.length; i++) {
        cells[i].textContent = ('; '+ document.cookie).split(`; ` + headers[i].textContent + `=`).pop().split(';')[0];
    }
}