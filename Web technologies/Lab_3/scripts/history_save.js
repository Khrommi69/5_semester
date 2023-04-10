  init();
  function init(){
   console.log("history: "+window.history.length);
  if(window.history.length<=2){
    localStorage.clear();
  }
  console.log("Кол-во записей для данной страницы "+localStorage.getItem(window.location.href));
  localStorage.setItem(window.location.href, Number(localStorage.getItem(window.location.href))+1);
  console.log("Local st len: "+localStorage.length);
  console.log("Кол-во записей для данной страницы "+localStorage.getItem(window.location.href));
  setCookie(window.location.href, getCookie(window.location.href), 1);
  console.log("Cookies: "+ getCookie(window.location.href));
}

function setCookie(name, value, options) {
  options = options || {};
  if(value==undefined) {
      value=1;
  }
  else
    value=Number(value)+1;
  console.log("Value to be set: "+value);
  var expires = options.expires;

  "user=John; path=/; expires=Tue, 19 Jan 2038 03:14:07 GMT"

  value = encodeURIComponent(value);

  var updatedCookie = name + "=" + value+"; path=/; expires=Tue, 19 Jan 2038 03:14:07 GMT";

  document.cookie = updatedCookie;
  console.log("Записываемые кукисы "+updatedCookie);
}

function getCookie(name) {
	console.log("Кукисы: "+document.cookie);
  // alert(document.cookie);
  var matches = document.cookie.match(new RegExp(
    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
  ));
  return matches ? (matches[1]) : undefined;
}