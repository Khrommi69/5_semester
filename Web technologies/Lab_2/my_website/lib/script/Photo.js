class PhotoTableCell
{
 constructor(photo)
 {
 this.photo = photo;
 }
 asDOMString()
 {
 return `
 <td>
 <a href="${this.photo.url}">
 <div class="image-frame">
 <div class="if-content"><img src="${this.photo.url}" alt="${this.photo.title}" title="$
{this.photo.title}"></div>
 <div class="if-title">/${this.photo.title}</div>
 </div>
 </a>
 </td>
 `;
 }
}
class PhotoTableCells
{
 constructor(photos)
 {
 this.photos = photos;
 }
 joinIntoTableRow()
 {
 let ret = document.createElement("tr");
 ret.innerHTML = this.photos.join("")
 return ret
 }
}