  let dir = "img/album/";
  let photos = ["1.png", "2.jpg", "3.jpg",
                "4.png", "5.png", "6.jpg",
                "7.png", "8.png", "9.jpg",
                "10.png", "11.jpg", "12.jpg",
                "13.jpg", "14.png", "15.jpg"];
  
  let titles =  ['Расим',
                  'Жуткий коридор СевГУ',
                  'Алексей в электричке',
                  'Озеро волшебное',
                  'Андрей аддихает',
                  'Сушист',
                  'Данила - Томас Шелби',
                  'Наруто-скул',
                  'Шери на паспорт',
                  'Старая Ава в вк',
                  'Помятый',
                  'Не много ли В.В. на стене?',
                  'Ядерный гриб на горизонте',
                  'death-note-скул',
                  'POV: Эмир'];

let albumGrid = $("#album-grid");

main();

function main() {
    // Добавление всех изображений внутрь тега с id album-grid
    for (let i = 0; i < photos.length; i++) {
        albumGrid.append(`<img src='${dir + photos[i]}' alt='${i+1}' title='${titles[i]}' style='z-index: 200'>`);
    }
    
    setListeners(albumGrid);
}

function setListeners(grid) {
    let div;

    grid.children().click(function(event) {
        div = $('#bigImage');
        let image = $(event.target).clone().css('z-index', '2999').hide();
        if (!div.length) {
            div = $('<div id="bigImage" class="biggerImage" style=" display:flex; flex-direction:column;"></div>');
            div .append(image)
                .append(`<div class="imageDescription"><span class="imageText">${$(this).attr("title")} (фото ${$(this).attr("alt")} из ${photos.length})</span></div>`)
                .on("click", function(e) {
                    div.remove();
                });
                image.show("fast");
                div.children(".imageDescription")
                    .prepend(`<img alt="prev" class="arrow" src="img/left.png">`)
                    .append (`<img alt="next" class="arrow" src="img/right.png">`);
                    
                    grid.append(div);
                    div.children(".imageDescription").children("[alt='prev']").click(e => clickPrev(e));
                    div.children(".imageDescription").children("[alt='next']").click(e => clickNext(e));
            
            function clickPrev(e) {
                e.stopImmediatePropagation();
                let index = (div.children("img").attr('alt') - 1 + photos.length - 1) % photos.length; //.match(/\d+/)
                let newImage = $(`<img src=${dir + photos[index]} alt=${index+1} title=${titles[index]} style="z-index:5000;">`).hide();

                div.children('img').fadeOut('slow', function () {
                    div.children('img').replaceWith(newImage);
                    div.children('img').fadeIn('fast');
                });
                div.children('.imageDescription').children('.imageText').text(`${titles[index]} (фото ${(index+1)} из ${photos.length})`);
                
            }
            
            function clickNext(e) {
                e.stopImmediatePropagation();
                let index = (div.children("img").attr('alt') - 1 + photos.length + 1) % photos.length; //.match(/\d+/)
                
                let newImage = $(`<img src=${dir + photos[index]} alt=${index+1} title=${titles[index]} style="z-index:5000;">`).hide();
    
                div.children('img').fadeOut('slow', function () {
                    div.children('img').replaceWith(newImage);
                    div.children('img').fadeIn('fast');
                });
                div.children('.imageDescription').children('.imageText').text(`${titles[index]} (фото ${(index+1)} из ${photos.length})`);
            }
        }
    });
}