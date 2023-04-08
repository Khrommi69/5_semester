'use strict';
const kTableColumnCount = 4;
var photos = [
 new Photo("Image 1", "/lib/image/album/image1.png"),
 new Photo("Image 2", "/lib/image/album/image2.jpg"),
 new Photo("Image 3", "/lib/image/album/image3.jpg"),
 new Photo("Image 4", "/lib/image/album/image4.jpg"),
 new Photo("Image 5", "/lib/image/album/image5.jpg"),
 new Photo("Image 6", "/lib/image/album/image6.jpg"),
 new Photo("Image 7", "/lib/image/album/image7.jpg"),
 new Photo("Image 8", "/lib/image/album/image8.png"),
 new Photo("Image 9", "/lib/image/album/image9.jpg"),
 new Photo("Image 10", "/lib/image/album/image10.jpg"),
 new Photo("Image 11", "/lib/image/album/image11.png"),
 new Photo("Image 12", "/lib/image/album/image12.jpg"),
 new Photo("Image 13", "/lib/image/album/image13.jpg"),
 new Photo("Image 14", "/lib/image/album/image14.jpg"),
 new Photo("Image 15", "/lib/image/album/image15.jpg")
]
document.addEventListener("DOMContentLoaded", () =>
{
 let tableBody = document.getElementById("table-content");
 let preparedPhotos = photos.map((value, index, array) => { return new PhotoTableCell(value).asDOMString(); });
 for (let i = 0; i < kTableColumnCount; ++i)
 {
 let startPosition = i * kTableColumnCount;
 tableBody.append(
 new PhotoTableCells(
 preparedPhotos.slice(startPosition, startPosition + kTableColumnCount)
 ).joinIntoTableRow()
 );
 }
});