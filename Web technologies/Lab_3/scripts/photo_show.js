// Определяем обработчик события для клика по изображению
document.addEventListener('click', function (event) {
  if (event.target.tagName === 'IMG') {
    // Создаем элемент для увеличенной версии изображения
    const overlay = document.createElement('div');
    const enlargedImg = document.createElement('img');
    overlay.classList.add('overlay');
    enlargedImg.classList.add('enlarged-img');
    // Добавляем увеличенную версию изображения в элемент
    enlargedImg.src = event.target.src;
    overlay.appendChild(enlargedImg);

    // Добавляем элемент на страницу
    document.body.appendChild(overlay);

    // Определяем обработчик события для клика по оверлею
    overlay.addEventListener('click', function () {
      // Удаляем оверлей
      document.body.removeChild(overlay);
    });

  }
});

// Добавляем стили для увеличенной версии изображения
const style = document.createElement('style');
style.textContent = `
.overlay {
position: fixed;
top: 0;
left: 0;
width: 100%;
height: 100%;
background-color: rgba(0, 0, 0, 0.8);
display: flex;
justify-content: center;
align-items: center;
z-index: 999;
}

.enlarged-img {
max-width: 100%;
max-height: 100%;
object-fit: contain;
}
`;

// Добавляем стили на страницу
document.head.appendChild(style);