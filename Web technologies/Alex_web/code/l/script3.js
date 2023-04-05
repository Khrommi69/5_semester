class CardNumber
{
  constructor(num)
  {
    this.num = num;
  }

  isVisa()
  {
    return this.num[0] == "4";
  }

  isMasterCard()
  {
    return this.num[0] == "5";
  }
}

document.addEventListener("DOMContentLoaded", () => 
{
  const cardForms = document.querySelectorAll(".card-form");
  for (let formNumber = 0; formNumber < cardForms.length; ++formNumber)
  {
    cardForms[formNumber].addEventListener("input", (event) =>
    {
      if (event.target.size == event.target.value.length)
      {
        if (event.target.nextElementSibling != null)
        {
          event.target.nextElementSibling.focus();
        }
      }
      else if (event.target.size < event.target.value.length && event.data != null)
      {
        let s = event.target.value;
        event.target.value = s.substring(0, event.target.size - 1) + event.data;
      }

      if (event.target.name == "pan")
      {
        let brandPlaceholders = cardForms[formNumber].getElementsByClassName("brand");
        let cardNumber = new CardNumber(event.target.value);
        let symbol = cardNumber.isVisa() ? "V" : cardNumber.isMasterCard() ? "MC" : "❌";

        for (let i = 0; i < brandPlaceholders.length; ++i)
        {
          brandPlaceholders[i].innerHTML = symbol;
        }
      }
    });
    cardForms[formNumber].addEventListener("keydown", (event) =>
    {
      if (event.target.value.length == 0 && event.key == "Backspace" && event.target.previousElementSibling != null)
      {
        event.target.previousElementSibling.focus();
      }
    });
  }
});
