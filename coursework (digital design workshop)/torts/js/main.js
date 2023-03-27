let defProduct = {
    name: "Ай-Петри",
    cost: 1000,
    ctn: 1
}
let cart = [];
$(document).ready(() => {
    loadCart();
    reRenderCart()
    let modalForOrder = {
        doc: "#order_mod",
        opened: false,
        open: () => {
            $(modalForOrder.doc).show();
            modalForOrder.opened = true
        },
        close: () => {
            $(modalForOrder.doc).css({display: "none"});
            modalForOrder.opened = false
        },
    }
    let modalForOrderCart = {
        doc: "#order_mod_cart",
        opened: false,
        open: () => {
            $(modalForOrderCart.doc).show();
            modalForOrderCart.opened = true
        },
        close: () => {
            $(modalForOrderCart.doc).css({display: "none"});
            modalForOrderCart.opened = false
        },
    }
    let modalCart = {
        doc: "#cart_mod",
        opened: false,
        open: () => {
            $(modalCart.doc).show();
            modalCart.opened = true
        },
        close: () => {
            $(modalCart.doc).css({display: "none"});
            modalCart.opened = false
        },
    }
    let cartAlert = {
        doc: '#inCart',
        open: () => {
            let elem = $(cartAlert.doc).clone()
            $("body").prepend(elem);
            $(elem).css({display: "flex", bottom: -200}).animate({bottom: 150}, 500, function () {
                $(this).show();
            });
            setTimeout(() => {
                cartAlert.close(elem);
            }, 2000)
        },
        close: (elem) => {
            $(elem).fadeOut().remove();
        },
    }
    $("body").on("click", ".open_cart", () => {
        modalCart.open();
    })
    $(".table_cnt").on("click", ".deleteItem", (e) => {
        let id = parseInt($(e.currentTarget).data("id"))
        cart.splice(id, 1)
        saveCart()
        reRenderCart();
    })
    $(".table_cnt").on("click", ".minus", (e) => {
        let id = parseInt($(e.currentTarget).data("id"))
        let c = cart[id].ctn;
        if (c > 1) {
            cart[id].ctn = c - 1;
            saveCart()
            reRenderCart();
        } else return;
    })
    $(".table_cnt").on("click", ".plus", (e) => {
        let id = parseInt($(e.currentTarget).data("id"))
        let item = {...cart[id]};
        item.ctn += 1;
        cart[id] = {...item};
        saveCart()
        reRenderCart();
    })
    $(".open_da").on("click",()=>{
        modalCart.close()
        modalForOrderCart.open();
    })
    $(".klkl").on("click",()=>{
        modalForOrderCart.close();
    })
    $('.cart_me').on("click", () => {
        cartAlert.open();
        cart.push(defProduct);
        saveCart();
        reRenderCart();
    })
    $(".order_me").on("click", () => {
        modalForOrder.open();
    })

    $(".close_modal").on("click", (e) => {
        let data = $(e.currentTarget).data("id")
        if (data === "order") {
            modalForOrder.close();
        } else {
            modalCart.close()
        }
    })
    $(".scroll_to").click(function(e) {
        let data = $(e.currentTarget).data("to")
        $([document.documentElement, document.body]).animate({
            scrollTop: $(`.${data}`).offset().top
        }, 1000);
    });
    $(".go_write").on("click",()=>{
        $("#order_mod_cart").css({display:"flex"})
    })
    setInterval(() => {
        if (modalForOrder.opened || modalCart.opened || modalForOrderCart.opened) {
            $("html,body").css({overflowY: "hidden"})
        } else {
            $("html,body").css({overflowY: "auto"})
        }
    }, 100)
})
const saveCart = () => {
    let json = JSON.stringify(cart)
    localStorage.setItem("cart", json)
}
const loadCart = () => {
    let json = localStorage.getItem("cart")
    if (json) {
        cart = JSON.parse(json);
    }
}
const reRenderCart = () => {
    let html = "";
    let cost = 0;
    cart.map((x, k) => {
        if(!x)
            return;
        if(x && x.hasOwnProperty("ctn") && x.ctn !== null)
        cost += x.ctn * x.cost;
        html += `
            <div class="product">
                        <div style="flex:.45;display: flex;justify-content: start;align-items: center;column-gap: 20px" class="pd_in">
                            <img style="width: 150px" src="imgs/Product_1.jpg"/>
                            <b>${x.name}</b>
                        </div>
                        <div style="width: 60px" class="pr_dic">0%</div>
                        <div style="width: 60px"   class="pr_cost">${x.cost} Р</div>
                        <div style="width: 60px"  class="count">
                        
                            <button  class="minus" data-id="${k}">-</button>
                            <span>${x['ctn']}</span>
                            <button  class="plus" data-id="${k}">+</button>
                        </div>
                        <div style="width: 60px"   class="f_cost">${x.ctn * x.cost} Р</div>
                        <div style="display: flex;flex-direction: column"  class="actions">
                            <span class="deleteItem" data-id="${k}" style="cursor:pointer;text-decoration: underline">Удалить</span>
                            <span class="deleteItem" data-id="${k}" style="cursor:pointer;text-decoration: underline">Отложить</span>
                        </div>
                    </div>
        `;

    })
    $(".table_cnt").html(html)
    $(".sum__").text(cost+" Р")
    $(".itogo.sum__").text("Итого: "+cost+" Р")
}
const gocourse = () => {
    location.href = "course.html"
}