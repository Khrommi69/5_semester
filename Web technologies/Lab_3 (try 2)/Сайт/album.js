main();
function main() {
  let dir = "img/album/";
  let photos = ["1.jpg", "2.jpg", "3.jpg",
                "4.jpg", "5.jpg", "6.jpg",
                "7.jpg", "8.jpg", "9.jpg",
                "10.jpg", "11.jpg", "12.jpg",
                "13.jpg", "14.jpg", "15.jpg"];
  
  let titles =  ['5 этаж в стиле TLOU',
                  'Жуткий коридор СевГУ',
                  'Алексей в электричке',
                  'Эмир из меня демона слепил в стиле КРД',
                  'ЭГО?',
                  'Сушист',
                  'Здоровья погибшим',
                  'Шери',
                  'Шери на паспорт',
                  'Исчезаю...',
                  'Помятый',
                  'Не много ли В.В. на стене?',
                  'Ядерный гриб на горизонте',
                  'тип New-York',
                  'POV: Эмир'];
  
  let albumGrid = document.getElementById("album-grid");
  
  // Добавление всех изображений внутрь тега с id album-grid
  for (let i = 0; i < photos.length; i++) {
      let image = new Image();
      image.src = dir + photos[i];
      image.alt = titles[i];
      image.title = titles[i];
      albumGrid.appendChild(image);
      setListeners(albumGrid);
  }
}

function setListeners(grid) {
  let div;

  Array.from(grid.children).forEach(elem => {
      elem.addEventListener('click', click => {
          div = document.getElementById("bigImage");
          if (div == null) {
              div = document.createElement('div');
              div.setAttribute("id", "bigImage");
              div.appendChild(click.target.cloneNode(true));
              div.classList.add("biggerImage");
              div.addEventListener('click', () => div.remove(true));
              grid.appendChild(div);
          } 
      })
  });
}